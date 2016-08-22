package com.lockscreen.services;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import com.lockscreen.configuration.StatusCodes;
import com.lockscreen.dao.TokenDAO;
import com.lockscreen.errorHandling.ServiceException;
import com.lockscreen.models.Token;
import com.lockscreen.models.User;

public class AuthService
{
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    @Inject
    private UserService itsUserService;
    @Inject
    private TokenDAO itsTokenDAO;

    public final String signUp(final User aUser)
            throws ServiceException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        LOGGER.info(getClass().getName() + " - signUp");

        if (itsUserService.getUserByLogin(aUser.getLogin()) != null)
        {
            throw new ServiceException(Status.BAD_REQUEST, StatusCodes.USER_EXIST);
        }

        String passwd = generateStorngPasswordHash(aUser.getPassword());
        aUser.setPassword(passwd);

        int userId = itsUserService.addUser(aUser);

        return produceToken(userId);
    }

    public final String signIn(final User aUser)
            throws ServiceException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        LOGGER.info(getClass().getName() + " - signIn");

        User user = itsUserService.getUserByLogin(aUser.getLogin());

        if (null == user || !validatePassword(aUser.getPassword(), user.getPassword()))
        {
            throw new ServiceException(Status.BAD_REQUEST, StatusCodes.USERNAME_AND_PASSWORD_NOT_MATCHED);
        }

        return produceToken(user.getId());
    }

    public final void signOut(final String aToken) throws ServiceException
    {
        LOGGER.info(getClass().getName() + " - signOut");

        itsTokenDAO.deleteByValue(aToken);
    }

    private final String produceToken(final int anId)
    {
        String tokenString = UUID.randomUUID().toString() + "_" + anId;
        Token token = new Token();
        token.setToken(tokenString);
        token.setUserId(anId);

        itsTokenDAO.add(token);

        return tokenString;
    }

    private final String generateStorngPasswordHash(final String aPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = aPassword.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private final boolean validatePassword(final String anOriginalPassword, final String aStoredPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = aStoredPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(anOriginalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private final byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private final String toHex(final byte[] anArray) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, anArray);
        String hex = bi.toString(16);
        int paddingLength = (anArray.length * 2) - hex.length();
        if (paddingLength > 0)
        {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        else
        {
            return hex;
        }
    }

    private final byte[] fromHex(final String aHex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[aHex.length() / 2];
        for (int i = 0; i < bytes.length; i++)
        {
            bytes[i] = (byte) Integer.parseInt(aHex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
