package edu.bsu.cs;

import java.time.Instant;

public class Revision {
    private final Instant timestampUtc;
    private final String user;

    public Revision(Instant timestampUtc, String user) {
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
