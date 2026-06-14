package org.atlas.flight.customer.domain.passenger.entity;

/**
 * 탑승자 관계 코드.
 * SELF 는 가입 시 자동등록되는 본인 행 전용이며, 나머지는 등록 가능한 가족 관계다.
 */
public enum RelCd {
	SELF,    // 본인
	SPOUSE,  // 배우자
	CHILD,   // 자녀
	PARENT,  // 부모
	SIBL,    // 형제·자매
	ETC       // 기타
}
