package ma.GymPro.controller;

import ma.GymPro.beans.Admin;
import ma.GymPro.beans.Client;
import ma.GymPro.beans.Profil;
import ma.GymPro.dto.ConnexionRequest;
import ma.GymPro.dto.ConnexionResponse;
import ma.GymPro.dto.CoursDTOResponse;
import ma.GymPro.repositories.AdminRepository;
import ma.GymPro.services.VisitorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class VisitorController {

    @Autowired
    VisitorServices visitorServices;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("login")
    public ResponseEntity<?> connexion(@RequestBody ConnexionRequest request){
            System.out.println(request.getEmail() + request.getPassword());
            ConnexionResponse response=visitorServices.connexion(request);
            return new ResponseEntity(response,HttpStatus.OK);

    }


    @PostMapping("register")
    public ResponseEntity<?> inscription(@RequestBody Client client ){

            ConnexionResponse response=  visitorServices.register(client);
            return new ResponseEntity(response,HttpStatus.OK);

    }
    @GetMapping("cours")
    public ResponseEntity<?> getAllCours(){
        try{
            List<CoursDTOResponse> coursList=  visitorServices.getAllCourse();
            return new ResponseEntity(coursList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("a")
    public void saveAdmin(){
        Admin a=new Admin(null ,"admin@gympro.ma",false,null,new Profil(null,"Ahmed","allaoui","JK34159",new Date(),"Homme","0689123121")
                ,null,null);
        a.setPassword(bCryptPasswordEncoder.encode("A1234"));
        adminRepository.save(a);
    }


    @GetMapping("/abonnements")
    public ResponseEntity<?> getAbonnement(){
        try {

            return new ResponseEntity(visitorServices.GetAbonnements(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/abonnements/{id}")
    public ResponseEntity<?> getAbonnement(Long id){
        try {

            return new ResponseEntity(visitorServices.getAbonnement(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("services")
    public ResponseEntity getServices(){
        try {

            return new ResponseEntity(visitorServices.getAllServices(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("services/{id}")
    public ResponseEntity getService(@PathVariable Long  id){
        try {

            return new ResponseEntity(visitorServices.getService(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
