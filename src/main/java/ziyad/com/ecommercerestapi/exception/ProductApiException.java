package ziyad.com.ecommercerestapi.exception;

import org.springframework.http.HttpStatus;

public class ProductApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ProductApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ProductApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
