/* File Header */
import java.util.*;

/* Class Header */
public class EncryptionBot
{
    private String simplePass;
    private String encryptedPass;
    private String encryptionKey;

    public EncryptionBot(String simplePass)
    {
        this.simplePass = simplePass;
        encryptionKey = selectEncryptionScheme();
        encryptedPass = encryptedPassword(simplePass, encryptionKey);
    }

    public EncryptionBot(String simplePass, String encKey)
    {
        this.simplePass = simplePass;
        encryptionKey = encKey;
        encryptedPass = encryptedPassword(simplePass, encryptionKey);
    }

    // Selects encryption scheme at random
    public String selectEncryptionScheme()
    {
        return "";
    }

    // Performs encryption, returns encrypted password.
    public String encryptedPassword(String simplePass, String encKey)
    {
        return "test";
    }

    public String getEncryptedPassword()
    {
        return encryptedPass;
    }
}