package org.atlas.flight.customer.domain.customer.issuance;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * MariaDB 시퀀스({@code CUSTOMER_NUMBER_SEQ})에서
 * 다음 값을 조회해 고객번호 문자열로 변환한다.
 */
@Component
@RequiredArgsConstructor
public class JdbcCustomerNumberIssuer implements CustomerNumberIssuer {
	private static final String NEXT_VALUE_SQL = "SELECT NEXT VALUE FOR CUSTOMER_NUMBER_SEQ";
	private static final int CUSTOMER_NUMBER_LENGTH = 12;

	private final JdbcTemplate jdbcTemplate;

	@Override
	public String issueNext() {
		Long next = jdbcTemplate.queryForObject(NEXT_VALUE_SQL, Long.class);
		if (next == null) {
			throw new IllegalStateException("CUSTOMER_NUMBER_SEQ returned no value");
		}
		return String.format("%0" + CUSTOMER_NUMBER_LENGTH + "d", next);
	}
}
