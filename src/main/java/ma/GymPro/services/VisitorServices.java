package ma.GymPro.services;

import com.amazonaws.services.s3.AmazonS3;
import ma.GymPro.beans.*;
import ma.GymPro.config.security.JwtTokenProvider;
import ma.GymPro.dto.connexion.ConnexionRequest;
import ma.GymPro.dto.connexion.ConnexionResponse;
import ma.GymPro.dto.cours.CoursDTOResponse;
import ma.GymPro.dto.service.ServiceDTOResponse;
import ma.GymPro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorServices {


    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    AbonnementRepository abonnementRepository;

    @Autowired
    UserRepository clientRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${aws.bucket.service}")
    private String bucketName;

    @Autowired
    AuthenticationManager   authenticationManager;

    @Value("${security.token_prefix}")
    private String TOKEN_PREFIX;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    CoursRepository coursRepository;
    @Autowired
    SeanceRepository seanceRepository;

    public List<Seance> getAllSeance(){
        return seanceRepository.findAll();
    }

    public ConnexionResponse  connexion(ConnexionRequest request){
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
        User userConnected = (User) authentication.getPrincipal();
        ConnexionResponse connexionResponse =new ConnexionResponse(jwt, userConnected);
        return connexionResponse;
    }


    public List<CoursDTOResponse> getAllCourse() throws IOException {
        List<CoursDTOResponse> coursDTOS=new ArrayList<>();
        for(Cours cours:coursRepository.findAll()){
            CoursDTOResponse coursDTO =new CoursDTOResponse(cours, amazonS3, bucketName);
            coursDTOS.add(coursDTO);
        }
        return coursDTOS;
    }


    public List<ServiceDTOResponse> GetAbonnements() throws IOException {
        List<ServiceDTOResponse> serviceDTOS=new ArrayList<>();
        for(Abonnement abonnement:abonnementRepository.findAll()){
            ServiceDTOResponse serviceDTO =new ServiceDTOResponse(abonnement, amazonS3, bucketName);
            serviceDTOS.add(serviceDTO);
        }
        return serviceDTOS;
    }


    public  Abonnement getAbonnement(Long id) throws Exception {
        Optional<Abonnement> optionalAbonnement= abonnementRepository.findById(id);
        if(optionalAbonnement.isPresent())
            return optionalAbonnement.get();
        else
            throw new Exception("Aucun Service Avec cet id");
    }
    public List<ServiceDTOResponse> getAllServices() throws IOException {
        List<ServiceDTOResponse> serviceDTOS=new ArrayList<>();
        for(Abonnement abonnement:abonnementRepository.findAll()){
            ServiceDTOResponse serviceDTO =new ServiceDTOResponse(abonnement, amazonS3, bucketName);
            serviceDTOS.add(serviceDTO);
        }
        return serviceDTOS;
    }
    public ServiceDTOResponse getService(Long id) throws Exception {
        Optional<ma.GymPro.beans.Service> optional=serviceRepository.findById(id);
        if(optional.isPresent()){
            ServiceDTOResponse serviceDTO =new ServiceDTOResponse(optional.get(), amazonS3, bucketName);
            return serviceDTO;
        }else
            throw new Exception("Aucun Service Avec cet id");
    }
}
