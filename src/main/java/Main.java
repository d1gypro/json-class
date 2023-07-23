import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String json = readString("json/data.json");

        List<Employee> list = jsonToList(json);
        list.stream().forEach(x -> System.out.println(x));


    }

    public static String readString(String jsonFileName) {
        JSONParser parser = new JSONParser();
        String json = null;
        try {
            Object obj = parser.parse(new BufferedReader(new FileReader(jsonFileName), 1024));
            JSONArray array = (JSONArray) obj;
            json = array.toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static List<Employee> jsonToList(String json) {

        List<Employee> list = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray array = (JSONArray) obj;

            for (int i = 0; i < array.size(); i++) {
                Gson gson = new GsonBuilder().create();
                list.add(gson.fromJson(array.get(i).toString(), Employee.class));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
