package Objects.utils;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class Utils {
    public String getHash(String s){

        String sha256hex = Hashing.sha256()
                .hashString(s, StandardCharsets.UTF_8)
                .toString();

        return sha256hex;
    }
}
