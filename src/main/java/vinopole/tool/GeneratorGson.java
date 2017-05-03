package vinopole.tool;

import com.google.gson.Gson;
import vinopole.model.Serie;

import java.io.*;
import java.util.List;

/**
 * Created by Kévin MICHAUX on 29/11/2016.
 */
public class GeneratorGson {
    /**
     * Store list of series in one file
     * @param series
     * @param fileName
     */
    public static void getGson(List<Serie> series, String fileName) {
        Gson gson = new Gson();
        String s = gson.toJson(series);
        try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(new File(fileName + ".json")), "UTF-8")) {
            fileWriter.append(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
