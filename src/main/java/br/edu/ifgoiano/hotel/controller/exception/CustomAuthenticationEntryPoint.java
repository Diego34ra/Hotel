package br.edu.ifgoiano.hotel.controller.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Date;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Autowired
    private HttpServletRequest request;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json; charset=UTF-8");
        res.setStatus(403);

        HttpStatus status = HttpStatus.FORBIDDEN;

        ObjectMapper mapper = new ObjectMapper();
        ErrorDetails errorDetails = new ErrorDetails(new Date(),status.value(),"Acesso negado.",getRequestPath());
        res.getWriter().write(mapper.writeValueAsString(errorDetails)
        );
    }

    private String getRequestPath() {
        return request.getRequestURI();
    }
}
