package requestBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Base64ImageConverter {
    public static String convertImageToBase64(File imageFile) {
        byte[] byteContent;
        String encodedString = null;
        try {
            byteContent = FileUtils.readFileToByteArray(imageFile);
            encodedString = Base64.getEncoder().encodeToString(byteContent);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }
}
