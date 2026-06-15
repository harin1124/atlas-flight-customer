package org.atlas.flight.customer.domain.passenger.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.atlas.flight.customer.domain.passenger.entity.RelCd;

import java.time.LocalDate;

/**
 * [응답] 탑승자.
 * 신원 정보는 연결된 회원({@code Customer})에서 조인해 채운다.
 */
@Getter
@Builder
@Schema(name = "[응답] 탑승자 (PassengerResponse)")
public class PassengerResponse {
	@Schema(description = "관계 코드")
	private RelCd relationCd;

	@Schema(description = "탑승자 회원번호")
	private String customerNumber;

	@Schema(description = "한글 이름")
	private String korFirstName;

	@Schema(description = "한글 성")
	private String korLastName;

	@Schema(description = "영문 이름")
	private String engFirstName;

	@Schema(description = "영문 성")
	private String engLastName;

	@Schema(description = "성별 코드")
	private String genderCd;

	@Schema(description = "생년월일")
	private LocalDate birthday;
}
