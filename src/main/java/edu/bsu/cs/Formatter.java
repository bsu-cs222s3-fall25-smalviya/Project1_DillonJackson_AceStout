package edu.bsu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Formatter {

    public String format(PageResults results) {
        if (results.missing()){
            return "No page found for \""+ results.requestedTitle() + "\".\n";
        }

        List<Revision> sorted = new ArrayList<>(results.revisions());
        Collections.sort(sorted, new Comparator<Revision>() {
            @Override public int compare(Revision a, Revision b){
                return b.timestampUtc().compareTo(a.timestampUtc());
            }
        });

        if (sorted.isEmpty()) {
            return "No recent changes returned. \n";
        }

        StringBuilder stringBuilder = new StringBuilder();

        boolean redirected = results.redirectedFrom().isPresent() ||!results.requestedTitle().equals(results.resolvedTitle());
        if (redirected) {
            stringBuilder.append("(Redirected to: \"")
                    .append(results.resolvedTitle())
                    .append("\")\n");
        }
        int limit = Math.min(15, sorted.size());
        for(int i = 0; i < limit; i++){
            Revision revision = sorted.get(i);
            stringBuilder.append(i +1)
                    .append(".) Time: ")
                    .append(revision.timestampUtc().toString())
                    .append(" Username: ")
                    .append(revision.user())
                    .append('\n');
        }
        return stringBuilder.toString();
    }
}
