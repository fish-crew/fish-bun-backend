package fish.global.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**<pre>
 * 1. 패키지명 : kr.or.seoulphil.cms.global.common.code
 * 2. 타입명 : ErrorCode.java
 * 2. 작성일 : 2022. 1. 21. 오후 4:42:37
 * 3. 작성자 : 최인혁
 * 4. 설명 : 예외 메시지 Enum Class
 * </pre>
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {


	DATA_NOT_FOUND(404,"40402","데이터가 없습니다."),
	INTER_SERVER_ERROR(500,"50000","내부 오류가 발생하였습니다."),
	NULL_POINTER_ERROR(500,"50001","내부 오류가 발생하였습니다."),
	AUTH_FAIL(500,"50002","접근할 수 없는 권한입니다."),
	AUTH_NOT_FOUND(500,"50002","권한정보를 찾을 수 없습니다."),

	NOT_AUTH_USER(400,"40001","값이 없습니다."),
	NOT_ALLOWED_EXT(400,"40002","허용된 확장자가 아닙니다."),
	EXIST_DATA(400,"40003","이미 등록되어 있습니다."),
	NO_FILE(400,"40004","파일이 없습니다."),
	NOT_MOVED(400,"40006","파일을 옮길 수 없습니다."),
	CAR_FAIL_APPROVE(400,"40007","정기차량 승인에 실패하였습니다."),
	CAR_NONE_APPROVE(400,"40008","방문차량 승인에 실패하였습니다."),
	SAVE_FAIL(400,"40009","저장에 실패하였습니다."),
	DELETE_FAIL(400,"40010","삭제에 실패하였습니다."),
	NOT_SAME_USR(400, "40011", "본인만 수정할 수 있습니다."),
	API_ERROR(400,"40012","API 연결에 실패 하였습니다."),

	//DYI 코드
	DYI_CODE_1(400,"1","API 요청에 실패 하였습니다."),
	DYI_CODE_2(400,"2","등록 되어있는 차량입니다."),
	DYI_CODE_3(400,"3","해당 차량으로 등록된 내역이 없습니다."),
	DYI_CODE_4(400,"4","API 요청에 실패 하였습니다."),
	DYI_CODE_9(400,"9","API 요청에 실패 하였습니다."),

	//K골프 코드
	K_GOLF_CODE_5073(400,"5073","해당시간에 예약이 존재 합니다."),
	K_GOLF_CODE_5074(400,"5074","예약등록시스템 오류가 발생하였습니다."),
	K_GOLF_CODE_5075(400,"5075","존재하지 않는 예약 번호입니다."),
	K_GOLF_CODE_5076(400,"5076","예약 취소 권한이 없습니다."),
	K_GOLF_CODE_5077(400,"5077","예약취소시스템 오류가 발생하였습니다.")
	;

	private int statusCode;
	private String errorCode;
	private String errorMessage;
}
