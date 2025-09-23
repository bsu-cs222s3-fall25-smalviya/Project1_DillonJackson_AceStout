package edu.bsu.cs;

public final class ErrorsTest {
        public ErrorsTest() {}

        public static class Network extends RuntimeException {
        public Network(Throwable cause) { super(cause);}
    }

    public static class BadRequest extends RuntimeException {
        public BadRequest(String message) {super(message); }
    }
}
