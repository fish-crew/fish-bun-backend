package fish.global.config;

import fish.global.dto.CustomException;
import fish.global.dto.ErrorCode;
import fish.global.dto.ResError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResError> customHandle(CustomException customEx) {
        ResError response;
        if(customEx.getResError() != null) {
            response = customEx.getResError();
        } else {
            response = new ResError(customEx.getErrorCode());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    /**<pre>
     * 1. 메소드명 : validErrorHandle
     * 2. 작성일 : 2022. 1. 28. 오후 12:38:53
     * 3. 작성자 : 남승식
     * 4. 설명 : @Validated 이용 시 검증 에러 및 타입 에러(VO 필드는 int인데 String 값 받는 경우) 발생 시 처리
     * </pre>
     * @param
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResError> validErrorHandle(BindException validEx) {
        validEx.printStackTrace();
        String message = "";
        FieldError fieldError = validEx.getFieldErrors().get(0);

        // TypeMismatch 같은 바인딩 자체 에러인 경우
        if (fieldError.isBindingFailure()) {
            if (fieldError.getCode().contains("typeMismatch")) {
                message = messageSource.getMessage("typeMismatch", null, LocaleContextHolder.getLocale());
            }
        }else {
            for (String code : fieldError.getCodes()) {
                try {
                    String findStr = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
                    if (!"".equals(findStr)) {
                        message = findStr;
                        break;
                    }
                } catch (NoSuchMessageException e) {
                    message = fieldError.getDefaultMessage();
                }
            }
        }

        ResError response = new ResError(message);
        response.setFieldErrors(validEx.getFieldErrors());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


//	@ExceptionHandler(NullPointerException.class)
//	public ResponseEntity<ResErrorVO> nullPointerHandle(NullPointerException nullEx) {
//		log.error("NullPointerException", nullEx);
//		ResErrorVO response = new ResErrorVO(ErrorCode.NULL_POINTER_ERROR);
//		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}



    /**<pre>
     * 1. 메소드명 : globalHandle
     * 2. 작성일 : 2022. 1. 21. 오후 4:41:00
     * 3. 작성자 : 최인혁
     * 4. 설명 : 의도하지 않는 모든 예외 공통 처리
     * </pre>
     * @param request
     * @param response
     * @param ex
     * @return ResponseEntity<ResErrorVO> OR response
     */
    @ExceptionHandler(Exception.class)
    public Object globalHandle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        String contentType = request.getHeader("Content-Type");
        Object result = null;

        if (contentType != null && request.getRequestURI().endsWith(".json") || "webclient".equals(request.getHeader("from"))) {
            //요청 타입이 JSON일경우 ResErrorVO 객체로 리턴,
            ResError response2 = new ResError(ErrorCode.INTER_SERVER_ERROR);
            result = new ResponseEntity<>(response2, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            // 그 외 에러발생 시 에러페이지 이동
            result = response;
        }

        return result;


    }
}
