package nice.com.solution.ecxeptionHandler;

import nice.com.solution.model.ApiError;

public class AppException extends Exception{

    private ApiError apiError;

    public AppException(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
