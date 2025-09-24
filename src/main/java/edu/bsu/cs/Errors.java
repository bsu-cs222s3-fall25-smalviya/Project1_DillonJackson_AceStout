package edu.bsu.cs;

public final class Errors {
    public Errors() {
    }

    public static class Network extends RuntimeException {
        private String message;

        public Network() {
            this.message = null;
        }

        public Network(Throwable cause) {
            this.message = null;
            initCause(cause);
        }

        public Network(String message) {
            this.message = message;
        }

        public Network(String message, Throwable cause) {
            this.message = message;
            initCause(cause);
        }

        @Override
        public String getMessage() {
            return message;
        }

        public static class BadRequest extends RuntimeException {
            private String message;

            public BadRequest() {
                this.message = null;
            }

            public BadRequest(String message) {
                this.message = message;
            }

            @Override
            public String getMessage() {
                return message;
            }
        }
    }
}
