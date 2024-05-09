package simulations;


import requestBuilder.FaceMatchService;
import requestBuilder.GenerateResult;
import requestBuilder.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MainSimulator {
    private static final int ABOVE_THRESHOLD = 0;
    private static final int BELOW_THRESHOLD = 1;
    private static final int TREJECT = 2;
    public static void main(String[] args) {
        ArrayList<String> imageName = new ArrayList<>();
        Map<String, List<String>> payloads = ImageLoader.readImageDataSet(TREJECT);
        imageName.addAll(payloads.keySet());
        for (int i = 0; imageName.size() > i; i++) {
            String imageIndex = imageName.get(i);
            List<String> base64List= payloads.get(imageIndex);
            Map<String, Object> payload = FaceMatchService.generatePayload(base64List);
            if(!payload.isEmpty()) {
                Map<String, Object> response = FaceMatchService.request(payload);
                String description = response.get("description").toString();
                String score = String.valueOf(response.get("score"));
                String[] values = {imageIndex, score, description};
                String pathname = "C:\\Users\\Chinedu\\Downloads\\images_3k\\match accuracy result\\accuracyMatchResultRejctThreshold.csv";
                GenerateResult.writeToCSVFile(pathname, values);
            }
        }
    }
}
