package requestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaceMatchService {
    public static Map<String, Object> generatePayload(List<String> params) {
        Map<String, Object> payload = new HashMap<>();
        if (params.size() == 2) {
            String probe = params.get(0);
            String candidate = params.get(1);
            payload.put("probe", probe);
            payload.put("candidate", candidate);
        }
        return payload;
    }
    public static Map<String, Object> request(Map<String, Object> jsonMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = null;
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert jsonPayload != null;
        StringEntity entity = new StringEntity(jsonPayload);
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("http://34.248.17.132:8080/api/verify");
            request.setEntity(entity);
            request.setHeader("Content-Type", "application/json");
            response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Map<String, Object>>() {}));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
