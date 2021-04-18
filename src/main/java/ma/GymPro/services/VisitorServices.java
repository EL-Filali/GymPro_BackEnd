package ma.GymPro.services;

import antlr.BaseAST;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.GymPro.beans.Client;
import ma.GymPro.beans.Cours;
import ma.GymPro.beans.Suspendu;
import ma.GymPro.beans.User;
import ma.GymPro.config.JwtTokenProvider;
import ma.GymPro.repositories.CoursRepository;
import ma.GymPro.repositories.UserRepository;
import ma.GymPro.responses.ConnexionRequest;
import ma.GymPro.responses.ConnexionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class VisitorServices {
    @Autowired
    UserRepository clientRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthenticationManager   authenticationManager;
    @Value("${security.token_prefix}")
    private String TOKEN_PREFIX;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    CoursRepository coursRepository;

    public ConnexionResponse  connexion( ConnexionRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        ConnexionResponse connexionResponse =new ConnexionResponse(jwt, user);
        return connexionResponse;
    }


    public ConnexionResponse register(Client user){
        String password=new String(user.getPassword()) ;
        String email=new String(user.getEmail());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        clientRepository.save(user);
        System.out.print(password);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        User userConnected = (User) authentication.getPrincipal();
        ConnexionResponse connexionResponse =new ConnexionResponse(jwt, userConnected);
        return connexionResponse;
    }

    public List<Cours> getAllCourse(){
        return  coursRepository.findAll();
    }

}
