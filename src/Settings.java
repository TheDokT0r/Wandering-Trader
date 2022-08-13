import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Settings {

    public Settings() {
    }

    private String getSettings(String key) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./data/game.options"));
        JSONObject jsonObject = (JSONObject)obj;

        return jsonObject.get(key).toString();
    }


    public int[] getRes() throws IOException, ParseException {
        String[] resStr = getSettings("resolution").toLowerCase(Locale.ROOT).split("x");
        return new int[]{Integer.parseInt(resStr[0]), Integer.parseInt(resStr[1])};
    }


    public boolean fullscreen() throws Exception {
        String isFullScreen = getSettings("fullscreen").toString().toLowerCase(Locale.ROOT);
        return isFullScreen.equals("true");
    }
}
