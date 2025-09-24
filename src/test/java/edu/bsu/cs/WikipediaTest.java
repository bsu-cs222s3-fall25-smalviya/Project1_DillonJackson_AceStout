
package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaTest {

    @Test
    void blankInputTest() {
        String pageTitle = "";
        boolean noPageRequested = (pageTitle == null || pageTitle.isBlank());
        assertTrue(noPageRequested);
    }



@Test
void noPageFoundTest() {
    String out = new Formatter().format(PageResults.missing("kkkkkk"));
    assertTrue(out.contains("No page found for \"kkkkkk\"."));
}

@Test
void redirectTest() {
    PageResults results = new PageResults(
            "United States",
            "United States",
            Optional.empty(),
            false,
            List.of(new Revision(Instant.parse("2025-09-20T12:01:02Z"), "UserX"))
    );

    String out = new Formatter().format(results);
    assertFalse(out.contains("(Redirected to: "));
}

}