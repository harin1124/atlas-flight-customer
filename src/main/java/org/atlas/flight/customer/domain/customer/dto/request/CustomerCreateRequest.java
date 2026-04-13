package org.atlas.flight.customer.domain.customer.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.atlas.flight.customer.util.FormatValidation;

import java.time.LocalDate;

/**
 * [요청] 고객 등록
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "[요청] 고객 등록 (CustomerCreateRequest)")
public class CustomerCreateRequest {
	@NotBlank
	@Size(min = 1, max = 30)
	@Schema(description = "고객 아이디", minLength = 1, maxLength = 30)
	private String customerId;

	@NotBlank
	@Size(min = 1, max = 30)
	@Pattern(regexp = FormatValidation.KOREAN_ONLY_REGEXP, message = FormatValidation.KOREAN_ONLY)
	@Schema(description = "한글 이름", minLength = 1, maxLength = 30)
	private String korFirstName;

	@NotBlank
	@Size(min = 1, max = 30)
	@Pattern(regexp = FormatValidation.KOREAN_ONLY_REGEXP, message = FormatValidation.KOREAN_ONLY)
	@Schema(description = "한글 성", minLength = 1, maxLength = 30)
	private String korLastName;

	@NotBlank
	@Size(min = 1, max = 30)
	@Pattern(regexp = FormatValidation.ENGLISH_ONLY_REGEXP, message = FormatValidation.ENGLISH_ONLY)
	@Schema(description = "영문 이름", minLength = 1, maxLength = 30)
	private String engFirstName;

	@NotBlank
	@Size(min = 1, max = 30)
	@Pattern(regexp = FormatValidation.ENGLISH_ONLY_REGEXP, message = FormatValidation.ENGLISH_ONLY)
	@Schema(description = "영문 성", minLength = 1, maxLength = 30)
	private String engLastName;

	@NotBlank
	@Pattern(regexp = FormatValidation.LOCALDATE_ONLY_REGEXP, message = FormatValidation.LOCALDATE_ONLY)
	@Schema(description = "생년월일")
	private LocalDate birthday;

	@NotBlank
	@Schema(description = "성별 코드", example = "MALE | FEMALE")
	private LocalDate genderCd;

	@NotBlank
	@Size(min = 1)
	@Schema(description = "휴대폰 국가 코드", example = "82")
	private String phoneCountryCd;

	@NotBlank
	@Size(min = 3)
	@Schema(description = "휴대폰 번호", example = "01012345678")
	private String phoneNumber;

	@NotBlank
	@Pattern(regexp = FormatValidation.EMAIL_REGEXP, message = FormatValidation.EMAIL)
	@Schema(description = "이메일", example = "test@gmail.com")
	private String email;
}
