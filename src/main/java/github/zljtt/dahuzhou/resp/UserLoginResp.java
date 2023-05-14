package github.zljtt.dahuzhou.resp;

public class UserLoginResp {

    String username;
    String message;
    String token;

    boolean success;

    public String getUsername() {
        return username;
    }

    public UserLoginResp setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UserLoginResp setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserLoginResp setToken(String token) {
        this.token = token;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public UserLoginResp setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public String toString() {
        return "UserLoginResp{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", success=" + success +
                '}';
    }
}
