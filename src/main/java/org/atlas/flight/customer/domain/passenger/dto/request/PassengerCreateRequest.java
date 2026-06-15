package org.atlas.flight.customer.domain.passenger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.atlas.flight.customer.domain.passenger.entity.RelCd;
import org.atlas.flight.customer.util.FormatValidation;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * [요청] 탑승자(가족) 등록.
 * 회원번호로 대상 회원을 찾고, 이름/생년월일이 일치할 때만 본인확인을 통과시킨다.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "[요청] 탑승자 등록 (PassengerCreateRequest)")
public class PassengerCreateRequest {
	@NotBlank
	@Size(min = 1, max = 12)
	@Schema(description = "탑승자 회원번호", minLength = 1, maxLength = 12, example = "000000000007")
	private String customerNumber;

	@NotBlank
	@Size(min = 1, max = 30)
	@Pattern(regexp = FormatValidation.KOREAN_ONLY_REGEXP, message = FormatValidation.KOREAN_ONLY)
	@Schema(description = "탑승자 한글 이름(본인확인)", minLength = 1, maxLength = 30, example = "에다드")
	private String korFirstName;

	@NotBlank
	@Size(min = 1, max = 30)
	@Pattern(regexp = FormatValidation.KOREAN_ONLY_REGEXP, message = FormatValidation.KOREAN_ONLY)
	@Schema(description = "탑승자 한글 성(본인확인)", minLength = 1, maxLength = 30, example = "스타크")
	private String korLastName;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Schema(description = "탑승자 생년월일(본인확인)")
	private LocalDate birthday;

	@NotNull
	@Schema(description = "관계 코드(SELF 제외)", example = "PARENT")
	private RelCd relationCd;
}
