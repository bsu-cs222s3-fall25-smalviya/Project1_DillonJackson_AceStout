package edu.bsu.cs;

import java.util.List;
import java.util.Optional;

public class PageResults {
    private final String requestedTitle;
    private final String resolvedTitle;
    private final Optional<String> redirectedFrom;
    private final boolean missing;
    private final List<Revision> revisions;

    public PageResults(String requestedTitle, String resolvedTitle, Optional<String> redirectedFrom, boolean missing, List<Revision> revisions) {
        this.requestedTitle = requestedTitle;
        this.resolvedTitle = resolvedTitle;
        this.redirectedFrom = redirectedFrom;
        this.missing = missing;
        this.revisions = revisions;
    }

    public static PageResults missing(String requestedTitle){
        return new PageResults(requestedTitle, requestedTitle,Optional.empty(), true, List.of());
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
        return revisions;
    }

}
