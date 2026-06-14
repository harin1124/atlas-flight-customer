package org.atlas.flight.customer.domain.passenger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 고객 탑승자(회원 전용 · 방향성 링크).
 * (소유자, 탑승자) 복합키로 식별한다 — 같은 방향 중복 등록은 키 자체로 불가능하다.
 * 신원 정보(이름/성별/생일)는 보관하지 않고 {@code passengerCustomerId} 로 연결된
 * {@link Customer} 에서 조회한다. 발권 시점 신원은 예약 서비스에서 스냅샷으로 박제한다.
 */
@Getter
@Entity
@Table(name = "PASSENGER")
@IdClass(PassengerId.class)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Passenger {
	@Id
	@Column(name = "OWNER_CUSTOMER_ID", length = 30, nullable = false)
	private String ownerCustomerId;

	@Id
	@Column(name = "PASSENGER_CUSTOMER_ID", length = 30, nullable = false)
	private String passengerCustomerId;

	@Enumerated(EnumType.STRING)
	@Column(name = "REL_CD", length = 6, nullable = false)
	private RelCd relCd;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	private String delYn;

	@CreatedDate
	@Column(name = "REG_DT", nullable = false, updatable = false)
	private LocalDateTime regDt;

	@CreatedBy
	@Column(name = "RGTR_ID", length = 30)
	private String rgtrId;

	@LastModifiedDate
	@Column(name = "MDFCN_DT", nullable = false)
	private LocalDateTime mdfcnDt;

	@LastModifiedBy
	@Column(name = "MDFR_ID", length = 30)
	private String mdfrId;

	/**
	 * 가입 시 본인을 탑승자로 자동등록한다. (소유자 == 탑승자 본인, 관계 SELF)
	 */
	public static Passenger createSelf(Customer me) {
		Passenger p = new Passenger();
		p.ownerCustomerId = me.getCustomerId();
		p.passengerCustomerId = me.getCustomerId();
		p.relCd = RelCd.SELF;
		p.delYn = "N";
		return p;
	}

	/**
	 * 가족(회원)을 탑승자로 등록한다. 본인확인 통과 후 호출되어야 한다.
	 */
	public static Passenger createNew(
			String ownerCustomerId,
			String passengerCustomerId,
			RelCd relCd
	) {
		if (relCd == RelCd.SELF || ownerCustomerId.equals(passengerCustomerId)) {
			throw new IllegalArgumentException("본인은 SELF 자동등록만 허용됩니다.");
		}
		Passenger p = new Passenger();
		p.ownerCustomerId = ownerCustomerId;
		p.passengerCustomerId = passengerCustomerId;
		p.relCd = relCd;
		p.delYn = "N";
		return p;
	}

	/** 소프트 삭제. */
	public void markDeleted() {
		this.delYn = "Y";
	}

	/**
	 * 소프트 삭제된 행을 재등록(되살리기). 관계 코드를 갱신한다.
	 * 복합키가 (소유자, 탑승자)라 같은 쌍은 INSERT 대신 이 경로로 재사용한다.
	 */
	public void reactivate(RelCd relCd) {
		if (relCd == RelCd.SELF) {
			throw new IllegalArgumentException("본인은 SELF 자동등록만 허용됩니다.");
		}
		this.relCd = relCd;
		this.delYn = "N";
	}

	public boolean isSelf() {
		return relCd == RelCd.SELF;
	}
}
