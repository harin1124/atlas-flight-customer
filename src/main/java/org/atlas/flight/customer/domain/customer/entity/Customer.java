package org.atlas.flight.customer.domain.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Customer {
	@Id
	@Column(name = "CUSTOMER_ID", length = 30, nullable = false)
	private String customerId;
	
	@Column(name = "CUSTOMER_NUMBER", length = 12, nullable = false)
	private String customerNumber;
	
	@Column(name = "KOR_FIRST_NAME", length = 30, nullable = false)
	private String korFirstName;
	
	@Column(name = "KOR_LAST_NAME", length = 30, nullable = false)
	private String korLastName;
	
	@Column(name = "ENG_FIRST_NAME", length = 30, nullable = false)
	private String engFirstName;
	
	@Column(name = "ENG_LAST_NAME", length = 30, nullable = false)
	private String engLastName;
	
	@Column(name = "BIRTHDAY", nullable = false)
	private LocalDate birthday;
	
	@Column(name = "GENDER_CD", length = 6, nullable = false)
	private String genderCd;
	
	@Column(name = "PHONE_COUNTRY_CD", length = 6, nullable = false)
	private String phoneCountryCd;
	
	@Column(name = "PHONE_NUMBER", length = 100, nullable = false)
	private String phoneNumber;
	
	@Column(name = "EMAIL", length = 100, nullable = false)
	private String email;
	
	@Column(name = "PREFERRED_LANG_CD", length = 6)
	private String preferredLangCd;
	
	@Column(name = "DORMANT_YN", length = 1, nullable = false)
	private String dormantYn;
	
	@Column(name = "WITHDRAWAL_YN", length = 1, nullable = false)
	private String withdrawalYn;
	
	@Column(name = "DEL_YN", length = 1, nullable = false)
	private String delYn;
	
	@Column(name = "REG_DT")
	private LocalDateTime regDt;
	
	@Column(name = "RGTR_ID", length = 30)
	private String rgtrId;
	
	@Column(name = "MDFCN_DT")
	private LocalDateTime mdfcnDt;
	
	@Column(name = "MDFR_ID", length = 30)
	private String mdfrId;
}
