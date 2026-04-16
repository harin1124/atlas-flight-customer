package org.atlas.flight.customer.domain.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.atlas.flight.core.util.encrypt.CryptoConverter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
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

	@Convert(converter = CryptoConverter.class)
	@Column(name = "PHONE_NUMBER", length = 100, nullable = false)
	private String phoneNumber;
	
	@Convert(converter = CryptoConverter.class)
	@Column(name = "EMAIL", length = 255, nullable = false)
	private String email;
	
	@Column(name = "PREFERRED_LANG_CD", length = 6)
	private String preferredLangCd;
	
	@Column(name = "DORMANT_YN", length = 1, nullable = false)
	private String dormantYn;
	
	@Column(name = "WITHDRAWAL_YN", length = 1, nullable = false)
	private String withdrawalYn;
	
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

	public static Customer createNew(
			String customerId,
			String customerNumber,
			String korFirstName,
			String korLastName,
			String engFirstName,
			String engLastName,
			LocalDate birthday,
			String genderCd,
			String phoneCountryCd,
			String phoneNumber,
			String email,
			String preferredLangCd
	) {
		Customer c = new Customer();
		c.customerId = customerId;
		c.customerNumber = customerNumber;
		c.korFirstName = korFirstName;
		c.korLastName = korLastName;
		c.engFirstName = engFirstName;
		c.engLastName = engLastName;
		c.birthday = birthday;
		c.genderCd = genderCd;
		c.phoneCountryCd = phoneCountryCd;
		c.phoneNumber = phoneNumber;
		c.email = email;
		c.preferredLangCd = preferredLangCd;
		c.dormantYn = "N";
		c.withdrawalYn = "N";
		c.delYn = "N";
		return c;
	}
}
