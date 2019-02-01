package me.lenycer.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a1100440 on 15/01/2019.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        ObjectMapper om = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", exception.getMessage());

        String jsonString = om.writeValueAsString(map);

        try (OutputStream out = response.getOutputStream()) {
            out.write(jsonString.getBytes());
        }

    }
}
