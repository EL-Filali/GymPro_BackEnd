package ma.GymPro.controller;

import ma.GymPro.beans.Client;
import ma.GymPro.beans.Cours;
import ma.GymPro.responses.ConnexionRequest;
import ma.GymPro.responses.ConnexionResponse;
import ma.GymPro.services.VisitorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/")
public class VisitorController {

    @Autowired
    VisitorServices visitorServices;

    @PostMapping("login")
    public ResponseEntity<?> connexion(ConnexionRequest request){
        try{
            ConnexionResponse response=visitorServices.connexion(request);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("register")
    public ResponseEntity<?> inscription(Client client ){
        try{
            ConnexionResponse response=  visitorServices.register(client);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("cours")
    public ResponseEntity<?> getAllCours(){
        try{
            List<Cours> coursList=  visitorServices.getAllCours();
            return new ResponseEntity(coursList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
