package edu.bsu.cs;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class ResultsTest {
    private final String requestedTitle;
    private final String resolvedTitle;
    private final Optional<String> redirectedFrom;
    private final boolean missing;
    private final List<RevisionTest> revisionTests;

    public ResultsTest(String requestedTitle, String resolvedTitle, Optional<String> redirectedFrom, boolean missing, List<RevisionTest> revisionTests) {
        this.requestedTitle = requestedTitle;
        this.resolvedTitle = resolvedTitle;
        this.redirectedFrom = redirectedFrom;
        this.missing = missing;
        this.revisionTests = revisionTests;
    }

    public static ResultsTest missing(String requestedTitle, String resolvedTitle, Optional<String> redirectedFrom){
        return new ResultsTest(requestedTitle, resolvedTitle,redirectedFrom, true, List.of());
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
    public List<RevisionTest> revisionTests() {
        return revisionTests;
    }

}
