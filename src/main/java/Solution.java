import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Solution {
    private static String BASE_URL = "https://challenges.hackajob.co/swapi/api/people/?format=json&search=";

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    private static URL getUrl(String character) {
        try {
            return new URI(BASE_URL + encodeValue(character)).toURL();
        } catch (MalformedURLException | URISyntaxException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static int run(String character) {
        int numberOfFilms = 0;

        try (InputStream reader = getUrl(character).openConnection().getInputStream()) {
            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));


            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            JsonElement rootElement = JsonParser.parseString(result.toString());

            JsonArray films = rootElement
                    .getAsJsonObject()
                    .getAsJsonArray("results")
                    .get(0).getAsJsonObject().getAsJsonArray("films");


            numberOfFilms = films.size();
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        return numberOfFilms;
    }

}
