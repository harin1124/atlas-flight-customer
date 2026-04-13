package org.atlas.flight.customer.util;

public class FormatValidation {
	public static final String KOREAN_ONLY_REGEXP = "^[가-힣]+$";
	public static final String KOREAN_ONLY = "한글만 입력 가능합니다.";

	public static final String ENGLISH_ONLY_REGEXP = "^[\\p{Script=Latin}\\s\\-'.]+$";
	public static final String ENGLISH_ONLY = "영문만(라틴 포함) 입력 가능합니다.";

	public static final String LOCALDATE_ONLY_REGEXP = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
	public static final String LOCALDATE_ONLY = "YYYY-MM-DD 만 입력 가능합니다.";

	public static final String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
	public static final String EMAIL = "이메일 주소만 입력 가능합니다.";
}
