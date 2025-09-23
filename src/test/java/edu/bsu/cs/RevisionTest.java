package edu.bsu.cs;

import java.time.Instant;

public class RevisionTest {
    private final Instant timestampUtc;
    private final String user;

    public RevisionTest(Instant timestampUtc, String user) {
        this.timestampUtc = timestampUtc;
        this.user = (user == null || user.isBlank()) ? "unknown" : user;
    }

    public Instant timestampUtc() {
        return timestampUtc;
    }
    public String user() {
        return user;
    }
}
