package api.petstore.helpers;

import lombok.Getter;

@Getter
public enum StatusCode {

    OK("200"),
    BAD_REQUEST("400"),
    NOT_FOUND("404"),
    NOT_ALLOWED("405");

    final private String statusCode;

    StatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
