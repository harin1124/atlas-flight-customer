package org.atlas.flight.customer.config.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorProvider implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		// SecurityContext에서 로그인 ID 등
		// TODO : 임의 조치로, 추후 JWT로 로그인할 경우 수정 필요
		return Optional.of("sysadmin");
	}
}
