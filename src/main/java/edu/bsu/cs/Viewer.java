package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;

import static edu.bsu.cs.APIFunctionality.connectToWikipedia;
import static edu.bsu.cs.APIFunctionality.readJsonAsStringFrom;

public class Viewer {
    public static void main(String[] args) throws IOException, URISyntaxException {
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonAsStringFrom(connection);
        printRawJson(jsonData);
    }
}
