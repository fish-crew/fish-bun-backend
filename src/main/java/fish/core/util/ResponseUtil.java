package fish.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseUtil<T> {
    private static final String MESSAGE_SUCCESS = "success";
    private static final String CODE_SUCCESS = "200";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private String result;
    private String statusCode;


    public static <T> ResponseUtil<T> success(T data) {
        return new ResponseUtil<>(data, MESSAGE_SUCCESS, CODE_SUCCESS);
    }

    public static <T> ResponseUtil<T> success() {
        return new ResponseUtil<>(null, MESSAGE_SUCCESS, CODE_SUCCESS);
    }

    public static <T> ResponseUtil<T> fail(String errorMessage, String errorCode) {
        return new ResponseUtil<>(null, errorMessage, errorCode);
    }
}

