package edu.bsu.cs;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ConsoleFunctions {
    private static final int DEFAULT_LIMIT = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your search query: ");
        String pageTitle = scanner.nextLine();

        if(pageTitle == null || pageTitle.isBlank()) {
            System.out.println("Error: No Page Requested.");
            return;
        }

        try {
            String base = "https://en.wikipedia.org/w/api.php";
            String query = "action=query"
                    +"&format=json"
                    +"&prop=revisions"
                    +"&titles=" + URLEncoder.encode(pageTitle, Charset.defaultCharset())
                    + "&rvprop=timestamp%7Cuser"
                    +"&rvlimit=" + DEFAULT_LIMIT
                    + "&redirects=1";

            URI uri = new URI(base + "?" +query);
            URLConnection connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "BSU-CS-WikiSearch/1.0 (student project)");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            String json = new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());

            if (json.contains("\"missing\":true") || json.contains("\"missing\":true")){
                System.out.println("No page found for \"" + pageTitle+ "\".");
            }
            else{
                System.out.println(json);
            }
        }
        catch (IOException | URISyntaxException e){
            System.out.println("Error: Network error while attempting to contact Wikipedia");
        }
    }
}


