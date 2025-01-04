package org.digivisions.utils;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class EntityAuditAware implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.empty();
	}
}
