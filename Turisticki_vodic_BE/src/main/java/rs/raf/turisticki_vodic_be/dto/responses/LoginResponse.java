package rs.raf.turisticki_vodic_be.dto.responses;


public class LoginResponse {
    private String jwt;
    private int statusCode;
    private String message;

    public LoginResponse(String jwt, int statusCode, String message) {
        this.jwt = jwt;
        this.statusCode = statusCode;
        this.message = message;
    }

    public LoginResponse(){


    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
