package org.atlas.flight.customer.config.resolver;

import org.atlas.flight.core.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * {@code @CurrentUser} 어노테이션이 붙은 파라미터에 현재 사용자 아이디를 주입하는 리졸버.
 * Gateway가 JWT 검증 후 설정한 X-User-Id 헤더에서 userId를 꺼낸다.
 */
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_USER_ID = "X-User-Id";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)
                && parameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String userId = webRequest.getHeader(HEADER_USER_ID);
        if (userId == null) {
            throw new IllegalStateException("X-User-Id 헤더가 없습니다. Gateway를 통해 요청하세요.");
        }
        return userId;
    }
}
