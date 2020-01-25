package com.rebuslop.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


/**
 * The Rebuslop class specifies contents and methods for a treasure hunt game. When initialized, it reads data from an
 * external JSON-file and store the game information in its applicable fields. The class also provide methods for
 * accessing data from the Rebuslop instance.
 */
public class Rebuslop {

    private final Logger LOGGER = LoggerFactory.getLogger(Rebuslop.class);

    private final String huntId;
    private final ArrayList<JsonElement> tasks;


    /**
     * Constructor
     */
    public Rebuslop(final String gamePin) throws IOException {
        huntId = gamePin;
        tasks = new ArrayList<>();
        initialize();
    }


    /**
     * The method initialize reads data from an external JSON-file containing applicable information about the treasure
     * hunt connected to the existing huntId. Upon successful data load, the method returns a reference to the
     * Rebuslop instance.
     */
    private void initialize() throws IOException {

        // Construct url string for applicable treasure hunt json-file.
        final String urlPath = "https://www.rebuslop.com/treasureHuntId_" + huntId + ".json";

        LOGGER.info("Game loaded from: {}", urlPath);

        // Open a new connection to the json file, construct a BufferedReader and read the whole file into a String.
        final URLConnection conn = new URL(urlPath).openConnection();
        final BufferedReader jsonFileReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        final String jsonString = jsonFileReader.lines()
                                                .collect(StringBuilder::new, StringBuilder::append,
                                                         StringBuilder::append)
                                                .toString();

        LOGGER.info("\"Game configuration before parsing: {}\"", jsonString);

        // From the json string, generate a JSON object.
        final JsonObject treasureHuntObject = new JsonParser().parse(jsonString).getAsJsonObject();

        // From the JsonObject extract the array of tasks as a JSONArray and add to class attribute tasks.
        final JsonArray taskList = treasureHuntObject.getAsJsonArray("tasks");
        taskList.iterator().forEachRemaining(tasks::add);

        LOGGER.info("Game configuration after parsing: {}", tasks);
    }

    /**
     * Get JSON string representation of task index i. If index is negative, the method will return the string
     * representation of an empty JSON-object.
     */
    public String getTask(final int i) {
        return i >= 0 ? tasks.get(i).toString() : "{}";
    }

    /**
     * From each task JSONObject in tasks, extract the array "latlon_coordinates" and the value "acceptanceRadius"
     * and generate a double[] of length 3.
     * Collect all double[] containing the lat/lon coordinates and acceptance radia into an ArrayList<double[]> and
     * return.
     */
    public ArrayList<double[]> getTaskCoordinatesAndRadius() {
        return tasks.stream().map(JsonElement::getAsJsonObject)
                    .map(task -> new double[]{
                            task.getAsJsonArray("latlon_coordinates").get(0).getAsDouble(),
                            task.getAsJsonArray("latlon_coordinates").get(1).getAsDouble(),
                            task.get("acceptanceRadius").getAsDouble()
                    })
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }


}
