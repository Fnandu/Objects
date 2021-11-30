package Objects.utils;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Utils {
    private static MessageDigest md = null;

    public String ConvertToMd5(String password) {
        try {
            if (md==null){
                md = MessageDigest.getInstance("SHA-256");
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }


        try {
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            byte[] thedigest = md.digest(bytesOfMessage);
            return new String(thedigest);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}