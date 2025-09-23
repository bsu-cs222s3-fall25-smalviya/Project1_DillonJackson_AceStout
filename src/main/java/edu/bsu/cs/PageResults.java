package edu.bsu.cs;

import java.util.List;
import java.util.Optional;

public class PageResults {
    private final String requestedTitle;
    private final String resolvedTitle;
    private final Optional<String> redirectedFrom;
    private final boolean missing;
    private final List<Revision> revision;

    public PageResults(String requestedTitle, String resolvedTitle, Optional<String> redirectedFrom, boolean missing, List<Revision> revision) {
        this.requestedTitle = requestedTitle;
        this.resolvedTitle = resolvedTitle;
        this.redirectedFrom = redirectedFrom;
        this.missing = missing;
        this.revision = revision;
    }

    public static PageResults missing(String requestedTitle, String resolvedTitle, Optional<String> redirectedFrom){
        return new PageResults(requestedTitle, resolvedTitle,redirectedFrom, true, List.of());
    }

    public String requestedTitle() {
        return requestedTitle;
    }
    public String resolvedTitle() {
        return resolvedTitle;
    }
    public Optional<String> redirectedFrom(){
        return redirectedFrom;
    }
    public boolean missing(){
        return missing;
    }
    public List<Revision> revisionTests() {
        return revision;
    }

}
