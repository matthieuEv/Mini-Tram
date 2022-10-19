package UI;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class useJson {
    public int readJson(String type){
        int value = 0;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/file/settings.json"));
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            for(Map.Entry<?, ?> entry : map.entrySet()){
                if(entry.getKey().equals(type)){
                    value = (int)Math.round((double)entry.getValue());
                }
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    };

    public void writeJson(String type, int value){
        try {
            // create a map
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/file/settings.json"));
            Map<String, Integer> map = gson.fromJson(reader, Map.class);
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                if(entry.getKey().equals(type)){
                    map.put(type, value);
                }
            }

            // create a writer
            Writer writer = new FileWriter("src/file/settings.json");

            // convert map to JSON File
            new Gson().toJson(map, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
