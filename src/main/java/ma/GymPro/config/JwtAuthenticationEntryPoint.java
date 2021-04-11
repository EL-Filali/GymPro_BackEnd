package ma.GymPro.config;

import com.google.gson.Gson;

import ma.GymPro.responses.ConnexionRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        ConnexionRequest loginRequest = new ConnexionRequest();
        String jsonLoginResponse = new Gson().toJson(loginRequest);


        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(jsonLoginResponse);
        httpServletResponse.getWriter().print("Email ou mot de passe  sont incorrects ");
        System.out.println("Email ou mot de passe  sont incorrects ");

    }
}
