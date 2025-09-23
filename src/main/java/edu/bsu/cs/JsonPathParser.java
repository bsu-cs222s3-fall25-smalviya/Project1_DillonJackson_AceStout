package edu.bsu.cs;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class JsonPathParser {

    public PageResults parse (String requestedTitle, String json) {
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(json);

        boolean missing = false;
        try{
            Boolean miss = JsonPath.read(doc, "$.query.pages[0].missing");
            missing = (miss != null && miss);
        }
        catch (PathNotFoundException ignore){ }
        if (missing ) {
            return PageResults.missing(requestedTitle);
        }

        String resolvedTitle = requestedTitle;
        try {
            String title = JsonPath.read(doc, "$.query.pages[0].title");
            if (title != null && !title.isBlank()) resolvedTitle = title;
        } catch (PathNotFoundException ignore){ }

        Optional<String> redirectedFrom = Optional.empty();
        try{
            String from = JsonPath.read(doc, "$.query.redirects[0].from");
            if (from != null && !from.isBlank()){
                redirectedFrom = Optional.of(from);
            }
        } catch (PathNotFoundException ignore) { }
        if (redirectedFrom.isEmpty() && !resolvedTitle.equals(requestedTitle)){
            redirectedFrom = Optional.of(requestedTitle);
        }

        List <Revision> revisions = new ArrayList<>();
        try{
            List<Map<String, Object>> wikiRevs = JsonPath.read(doc, "$.query.pages[0].revisions[*]");
            for (Map<String, Object> results : wikiRevs) {
                Object timestamp = results.get("timestamp");
                Object userobject = results.get("user");
                if (timestamp instanceof String tsString) {
                    String user = (userobject instanceof  String u && !u.isBlank()) ? u : "unknown";
                    revisions.add(new Revision(Instant.parse(tsString), user));
                }
            }
        } catch (PathNotFoundException ignore){ }

        return new PageResults(requestedTitle, resolvedTitle, redirectedFrom, false, revisions);





    }
}
