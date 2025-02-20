package fish.global.dto;

import fish.global.config.ApplicationContextProvider;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<pre>
 * 1. 패키지명 : kr.or.seoulphil.cms.global.common.dto
 * 2. 타입명 : ResErrorDTO.java
 * 2. 작성일 : 2022. 1. 21. 오후 4:38:59
 * 3. 작성자 : 최인혁
 * 4. 설명 : Ajax 통신 시 Error 발생 후 전달 객체
 * </pre>
 */
@Data
public class ResError {

	private int statusCode;
	private String errorCode;
	private String errorMessage;
	private List<Map<String, String>> fieldErrors;

	public ResError(ErrorCode errorCode){
        this.statusCode = errorCode.getStatusCode();
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

	public ResError(String errorMessage){
		this.statusCode = 500;
		this.errorCode = "99999";
		this.errorMessage = errorMessage;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = new ArrayList<Map<String, String>>();
		MessageSource messageSource = ApplicationContextProvider.getApplicationContext().getBean("messageSource", MessageSource.class);
		for(FieldError fieldError:fieldErrors) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("field", fieldError.getField());
			String message = null;
			try {
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
			} catch (NoSuchMessageException e) {
				e.printStackTrace();
				message = fieldError.getDefaultMessage();
			}
			if(message == null || "".equals(message)) {
				message = fieldError.getDefaultMessage();
			}
			map.put("message", message);
			this.fieldErrors.add(map);
		}
	}
}
