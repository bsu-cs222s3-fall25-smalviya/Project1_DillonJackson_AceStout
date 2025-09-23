package edu.bsu.cs;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

interface OutputFormatter{
    String format(ResultsTest resultsTest);
}

public class FormatterTest implements OutputFormatter {

    private final ZoneId zone;
    private final DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    public FormatterTest(ZoneId zone) {
        this.zone = zone;
    }

    @Override
    public String format(ResultsTest result){
        if (result.missing()){
            return "No page found for \"" + result.requestedTitle() + "\".\n";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Title: ").append(result.resolvedTitle());
        result.redirectedFrom().ifPresent(from ->
                stringBuilder.append(" (redirected from \"").append(from).append("\")"));
        stringBuilder.append('\n');

        if(result.revisionTests().isEmpty()){
            stringBuilder.append("No recent changes returned.\n");
            return stringBuilder.toString();
        }

        stringBuilder.append("Recent changes(").append(result.revisionTests().size()).append("):\n");
        stringBuilder.append(String.format("%-25s | %s%n", "Local Date/Time", "Username"));
        stringBuilder.append("----------------------------------------------\n");
        result.revisionTests().forEach(revise -> {
            String local = fmt.format(revise.timestampUtc().atZone(zone));
            stringBuilder.append(String.format("%-25s | %s%n", local, revise.user()));
        });
        return stringBuilder.toString();
    }
}
