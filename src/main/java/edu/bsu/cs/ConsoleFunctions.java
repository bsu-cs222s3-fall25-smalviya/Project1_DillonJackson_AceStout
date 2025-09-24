package edu.bsu.cs;

import java.util.Scanner;

public class ConsoleFunctions {
    private static final int DEFAULT_LIMIT = 15;

    private WikipediaSearcher searcher = new WikipediaSearcher();
    private JsonPathParser parser = new JsonPathParser();
    private final Formatter formatter = new Formatter();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Wikipedia search: ");
        String pageTitle = scanner.nextLine();

        if (pageTitle == null || pageTitle.isBlank()) {
            System.out.println("Error, No Page Found.");
            return;
        }

        try {
            String json = searcher.getPageRevisions(pageTitle, DEFAULT_LIMIT);

            if (json.contains("\"missing\":true") || json.contains("\"missing\":true")) {
                System.out.println("No page found for \"" + pageTitle + "\".");
                return;
            }
            PageResults results = parser.parse(pageTitle, json);
            System.out.print(formatter.format(results));
        } catch (Errors.Network error) {
            System.out.println("Error, NETWORK ERROR while contacting Wikipedia");
        } catch (Errors.BadRequest error2) {
            System.out.println("Error, " + error2.getMessage());
        }
    }
}


