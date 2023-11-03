package com.pet.ledger.interception;

import com.pet.ledger.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IsExpiredSessionInterceptor extends HandlerInterceptorAdapter {

    private final RequestValidator requestValidator;

    @Autowired
    public IsExpiredSessionInterceptor(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws MyInterceptorException {
        if (!requestValidator.isValid(request)) {
            throw new MyInterceptorException(MessageConstant.EXPIRED);
        }
        return true;
    }

    @Bean
    @Autowired
    public MappedInterceptor getMappedInterceptor(IsExpiredSessionInterceptor isExpiredSessionInterceptor) {
        return new MappedInterceptor(new String[]{"/**"}, new String[]{"/v1/sessions","/v1/sessions/{session_id}", "/test/**"}
        , isExpiredSessionInterceptor);
    }
}
