package ma.GymPro.services;

import ma.GymPro.beans.Abonnement;
import ma.GymPro.beans.Client;
import ma.GymPro.beans.Cours;
import ma.GymPro.beans.User;
import ma.GymPro.config.JwtTokenProvider;
import ma.GymPro.repositories.AbonnementRepository;
import ma.GymPro.repositories.CoursRepository;
import ma.GymPro.repositories.UserRepository;
import ma.GymPro.responses.ConnexionRequest;
import ma.GymPro.responses.ConnexionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorServices {
    @Autowired
    AbonnementRepository abonnementRepository;
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
    public List<Abonnement> GetAbonnements(){

        return abonnementRepository.findAll();
    }

    public  Abonnement getAbonnement(Long id) throws Exception {
        Optional<Abonnement> optionalAbonnement= abonnementRepository.findById(id);
        if(optionalAbonnement.isPresent())
            return optionalAbonnement.get();
        else
            throw new Exception("Aucun Service Avec cet id");
    }
}
