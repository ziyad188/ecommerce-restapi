package ziyad.com.ecommercerestapi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

public class ErrorDetails {
    @Schema(description = "Date when the error occurred")
    private Date date;

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "Details about the error")
    private String details;

    public ErrorDetails(Date date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
