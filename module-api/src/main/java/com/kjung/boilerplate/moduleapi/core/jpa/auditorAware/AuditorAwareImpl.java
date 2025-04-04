package com.kjung.boilerplate.moduleapi.core.jpa.auditorAware;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: 실제 로그인 사용자 정보에서 아이디 추출해서 넣기
        // 예: SecurityContextHolder.getContext().getAuthentication().getName()
        return Optional.of("system"); // 기본 테스트용 값
    }
}
