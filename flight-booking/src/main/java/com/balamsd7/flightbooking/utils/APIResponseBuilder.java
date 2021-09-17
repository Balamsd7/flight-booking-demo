package com.balamsd7.flightbooking.utils;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
public class APIResponseBuilder {
    private static final Logger log = LoggerFactory.getLogger(APIResponseBuilder.class);

    /**
     * Builds the error response. * * @param message the message * @param errorMessages the error messages * @param statusCode the status code * @return the response entity
     */
    public static ResponseEntity<ResponseDataDto> buildErrorResponse(final String message, final Map<String, String> errorMessages, final HttpStatus statusCode) {
        final ResponseDataDto errorDto = APIResponseBuilder.buildResponse(message);
        errorDto.setResult(errorMessages);
        return new ResponseEntity<>(errorDto, statusCode);
    }


    /**
     * Builds the response. * * @param message the message * @return the response data DTO
     */
    public static ResponseDataDto buildResponse(final String message) {
        final ResponseDataDto dataDto = new ResponseDataDto();
        dataDto.setMessage(message);
        return dataDto;
    }

    /**
     * Builds the success response. * * @param message the message * @param result the result * @return the response entity
     */
    public static ResponseEntity<ResponseDataDto> buildSuccessResponse(final String message, final Object result) {
        final ResponseDataDto successDto = new ResponseDataDto();
        successDto.setMessage(message);
        successDto.setResult(result);
        return new ResponseEntity<>(successDto, HttpStatus.OK);
    }

    /**
     * Builds the success response. * * @param * @param ResponseDataDto the responseObj * @return the response entity * @author Kalidass.K
     */
    public static ResponseEntity<ResponseDataDto> buildResponseFromDto(ResponseDataDto responseDataDto) {
        if (CommonConstants.SUCCESS.equalsIgnoreCase(responseDataDto.getMessage()))
            return new ResponseEntity<>(responseDataDto, HttpStatus.OK);
        else if (CommonConstants.FAILURE.equalsIgnoreCase(responseDataDto.getMessage())) {
            return APIResponseBuilder.buildErrorResponse(CommonConstants.INTERNAL_SERVER_ERROR_DESC, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return APIResponseBuilder.buildErrorResponse(responseDataDto.getMessage(), null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Instantiates a new API response builder.
     */
    private APIResponseBuilder() {
        // Private Constructor }}
    }
}