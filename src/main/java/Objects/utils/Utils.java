package Objects.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Utils {
    private static MessageDigest md = null;

    public String ConvertToSHA256(String password) {
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

    public String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println(sb);
        //return complete hash
        return sb.toString();
    }

    public String convertToGB(long size) {
        long kilo = 1024;
         long mega = kilo * kilo;
         long giga = mega * kilo;
         long tera = giga * kilo;

        String s = "";
        double kb = (double)size / kilo;
        double mb = kb / kilo;
        double gb = mb / kilo;
        double tb = gb / kilo;
        if(size < kilo) {
            s = size + " Bytes";
        } else if(size >= kilo && size < mega) {
            s =  String.format("%.2f", kb) + " KB";
        } else if(size >= mega && size < giga) {
            s = String.format("%.2f", mb) + " MB";
        } else if(size >= giga && size < tera) {
            s = String.format("%.2f", gb) + " GB";
        } else if(size >= tera) {
            s = String.format("%.2f", tb) + " TB";
        }
        return s;
    }
}