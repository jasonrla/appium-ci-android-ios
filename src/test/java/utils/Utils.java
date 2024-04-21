package utils;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    public static JSONObject readCapabilities(String platform) throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources/capabilities/"+platform+"_capabilities.json";
        String content = new String(Files.readAllBytes(Paths.get(path)));
        return new JSONObject(content);
    }

    public static String getPlatformVersion(String apiLevel) {
        switch (apiLevel) {
            case "21":
                return "5.0";
            case "22":
                return "5.1";
            case "23":
                return "6.0";
            case "24":
                return "7.0";
            case "25":
                return "7.1";
            case "26":
                return "8.0";
            case "27":
                return "8.1";
            case "28":
                return "9.0";
            case "29":
                return "10.0";
            case "30":
                return "11.0";
            case "31":
                return "12.0";
            case "32":
                return "12L";
            case "33":
                return "13.0";
            case "34":
                return "14.0";
            case "35":
                return "15.0";
            default:
                throw new IllegalArgumentException("API Level " + apiLevel + " is not supported");
        }
    }
}
