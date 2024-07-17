package com.ilu.loan.apis.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ilu.loan.apis.erros.ApiError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> extends ResponseEntity<T> {
    private int code;
    private String message;
    private ApiError error;
    private T data;

    public ApiResponse(String message, T data, ApiError error, HttpStatus httpStatus) {
        super(data, httpStatus);
        this.code = httpStatus.value();
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> generate(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(message, data, null, httpStatus);
    }

    public static <T> ApiResponse<T> generate(HttpStatus httpStatus, String message, T data, ApiError error) {
        return new ApiResponse<>(message, data, error, httpStatus);
    }

    public static ApiResponseBuilder message(String message) {
        return new ApiResponseBuilder().message(message);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>("OK", data, null, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>("Created", data, null, HttpStatus.CREATED);
    }

    public static <T> ApiResponse<T> badRequest(ApiError error) {
        return new ApiResponse<>("Bad Request", null, error, HttpStatus.BAD_REQUEST);
    }

    public static <T> ApiResponse<T> notFound(ApiError error) {
        return new ApiResponse<>("Not Found", null, error, HttpStatus.NOT_FOUND);
    }

    public static <T> ApiResponse<T> internalServerError(ApiError error) {
        return new ApiResponse<>("Internal Server Error", null, error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ApiResponse<T> conflict(ApiError error) {
        return new ApiResponse<>("Conflict", null, error, HttpStatus.CONFLICT);
    }

    public static <T> ApiResponse<T> forbidden(ApiError error) {
        return new ApiResponse<>("Forbidden", null, error, HttpStatus.FORBIDDEN);
    }

    public static <T> ApiResponse<T> unauthorized(ApiError error) {
        return new ApiResponse<>("Unauthorized", null, error, HttpStatus.UNAUTHORIZED);
    }

    public static <T> ApiResponse<T> notAcceptable(ApiError error) {
        return new ApiResponse<>("Not Acceptable", null, error, HttpStatus.NOT_ACCEPTABLE);
    }

    public static <T> ApiResponse<T> unprocessableEntity(ApiError error) {
        return new ApiResponse<>("Unprocessable Entity", null, error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static <T> ApiResponse<T> preconditionFailed(ApiError error) {
        return new ApiResponse<>("Precondition Failed", null, error, HttpStatus.PRECONDITION_FAILED);
    }

    public static <T> ApiResponse<T> gone(ApiError error) {
        return new ApiResponse<>("Gone", null, error, HttpStatus.GONE);
    }

    public static <T> ApiResponse<T> notModified(ApiError error) {
        return new ApiResponse<>("Not Modified", null, error, HttpStatus.NOT_MODIFIED);
    }

    public static <T> ApiResponse<T> serviceUnavailable(ApiError error) {
        return new ApiResponse<>("Service Unavailable", null, error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public static <T> ApiResponse<T> notImplemented(ApiError error) {
        return new ApiResponse<>("Not Implemented", null, error, HttpStatus.NOT_IMPLEMENTED);
    }

    public static <T> ApiResponse<T> badGateway(ApiError error) {
        return new ApiResponse<>("Bad Gateway", null, error, HttpStatus.BAD_GATEWAY);
    }

    public static <T> ApiResponse<T> gatewayTimeout(ApiError error) {
        return new ApiResponse<>("Gateway Timeout", null, error, HttpStatus.GATEWAY_TIMEOUT);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public T getBody() {
        return (T) new ApiResponseBody<>(code, message, data, error);
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ApiResponseBody<T> {
        private int code;
        private String message;
        private T data;
        private ApiError error;

        public ApiResponseBody(int code, String message, T data, ApiError error) {
            this.code = code;
            this.message = message;
            this.data = data;
            this.error = error;
        }
    }

    public static class ApiResponseBuilder {
        private String message = "OK";

        private ApiResponseBuilder() {
        }

        public ApiResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public <T> ApiResponse<T> ok(T data) {
            return new ApiResponse<>(message != null ? message : "OK", data, null, HttpStatus.OK);
        }

        public <T> ApiResponse<T> created(T data) {
            return new ApiResponse<>(message != null ? message : "Created", data, null, HttpStatus.CREATED);
        }

        public <T> ApiResponse<T> badRequest(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Bad Request", null, error, HttpStatus.BAD_REQUEST);
        }

        public <T> ApiResponse<T> notFound(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Not Found", null, error, HttpStatus.NOT_FOUND);
        }

        public <T> ApiResponse<T> internalServerError(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Internal Server Error", null, error,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        public <T> ApiResponse<T> conflict(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Conflict", null, error, HttpStatus.CONFLICT);
        }

        public <T> ApiResponse<T> forbidden(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Forbidden", null, error, HttpStatus.FORBIDDEN);
        }

        public <T> ApiResponse<T> unauthorized(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Unauthorized", null, error, HttpStatus.UNAUTHORIZED);
        }

        public <T> ApiResponse<T> notAcceptable(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Not Acceptable", null, error,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        public <T> ApiResponse<T> unprocessableEntity(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Unprocessable Entity", null, error,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        public <T> ApiResponse<T> preconditionFailed(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Precondition Failed", null, error,
                    HttpStatus.PRECONDITION_FAILED);
        }

        public <T> ApiResponse<T> gone(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Gone", null, error, HttpStatus.GONE);
        }

        public <T> ApiResponse<T> notModified(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Not Modified", null, error, HttpStatus.NOT_MODIFIED);
        }

        public <T> ApiResponse<T> serviceUnavailable(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Service Unavailable", null, error,
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

        public <T> ApiResponse<T> notImplemented(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Not Implemented", null, error,
                    HttpStatus.NOT_IMPLEMENTED);
        }

        public <T> ApiResponse<T> badGateway(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Bad Gateway", null, error, HttpStatus.BAD_GATEWAY);
        }

        public <T> ApiResponse<T> gatewayTimeout(ApiError error) {
            return new ApiResponse<>(message != null ? message : "Gateway Timeout", null, error,
                    HttpStatus.GATEWAY_TIMEOUT);
        }
    }

}
