package org.atlas.flight.customer.domain.customer.issuance;

/**
 * DB 시퀀스 기반 고객번호(12자리 숫자 문자열) 발급.
 */
public interface CustomerNumberIssuer {
	/**
	 * 다음 고객번호를 발급한다. 형식은 선행 0 패딩 12자리 십진 문자열이다.
	 */
	String issueNext();
}
