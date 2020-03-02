package edu.byu.cs.client.net.response;

public class SignUpResponse {

    private String message;
    private boolean success;

    public SignUpResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setError(boolean success) {
        this.success = success;
    }
}
