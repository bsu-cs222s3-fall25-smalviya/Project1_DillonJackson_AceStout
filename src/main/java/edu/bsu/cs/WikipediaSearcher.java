package edu.bsu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaSearcher {

    public String getPageRevisions(String title, int limit) {
        if (limit < 1 || limit > 50){
            throw new Errors.BadRequest("Change limit must be between 1 and 15.");
        }
        try {
            String base = "https://en.wikipedia.org/w/api.php";
            String query = "action=query"
                    + "&format=json"
                    +"&formatversion=2"
                    +"&prop=revisions"
                    +"&titles=" + URLEncoder.encode(title, Charset.defaultCharset())
                    + "&rvprop=timestamp%7Cuser"
                    +"&rvlimit=" + limit
                    + "&redirects=1";

            URI uri = new URI(base + "?"+ query);
            URLConnection connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "BSU-CS-WikiSearch/1.0 (student project)");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            byte [] bytes = connection.getInputStream().readAllBytes();
            return new String(bytes, Charset.defaultCharset());
        }
        catch (IOException | URISyntaxException error) {
            throw new Errors.Network(error);
        }
    }
}