package fish.global.dto;

import lombok.Getter;



/**<pre>
 * 1. 패키지명 : kr.or.seoulphil.cms.global.common.dto
 * 2. 타입명 : CustomException.java
 * 2. 작성일 : 2022. 1. 21. 오후 4:39:08
 * 3. 작성자 : 최인혁
 * 4. 설명 : 의도된 예외 공통 처리 클래스
 * </pre>
 */
@Getter
public class CustomException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 9133327471672305810L;
	private ErrorCode errorCode;
	private ResError resError;


	/**
	 *
	 * message : 디버깅 참고용 메시지 ( stackTrace )
	 * errorCode : 화면으로 전달할 에러 정보
	 *
	 * EX) throw new CustomException(ErrorCode.NOT_AUTH_USER);
	 *
	 * 화면 : {"statusCode":400,"errorCode":"40001","errorMessage":"EMAIL DUPLICATED"}
	 *
	 * */
	public CustomException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
	public CustomException(String message){
		super(message);
		this.resError = new ResError(message);

	}
}
