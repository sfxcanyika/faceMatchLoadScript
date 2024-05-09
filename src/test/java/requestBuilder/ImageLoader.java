package requestBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageLoader {
    public static Map<String, List<String>> readImageDataSet(int index) {
        Map<String, List<String>> base64Obj = new HashMap<>();
        String[] imageDir = {"Above_Threshold", "Below_Threshold", "treject"};
        String filePath = "C:/Users/Chinedu/Downloads/images_3k/" + imageDir[index];
        File[] folderName = new File(filePath).listFiles((dir, name) -> dir.isDirectory());
        if(folderName != null) {
            for (int i = 0; folderName.length > i; i++) {
                String newFilePath = filePath + "/" + folderName[i].getName();
                File[] files = new File(newFilePath).listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
                List<String> imageBase64String = new ArrayList<>();
                if(files != null && files.length == 2) {
                    for (int z = 0; 2 > z; z++) {
                        String image1 = Base64ImageConverter.convertImageToBase64(files[z]);
                        imageBase64String.add(image1);
                    }
                }
                base64Obj.put(newFilePath, imageBase64String);
            }
        }
        return base64Obj;
    }
}
