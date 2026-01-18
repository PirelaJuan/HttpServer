import com.fasterxml.jackson.databind.JsonNode;
import com.juan.httpserver.config.Configuration;
import com.juan.httpserver.util.Json;

import java.io.IOException;

public class MainTest {

    public static void main(String[] args) throws IOException {
        String json = "{ \"port\": 8080, \"webroot\": \"www\" }";
        JsonNode node = Json.parse(json);
        Configuration cfg = Json.fromJson(node, Configuration.class);
        System.out.println(cfg.getPort());

    }
}
