package ma.GymPro.controller;

import com.sun.mail.iap.Response;
import ma.GymPro.beans.Client;
import ma.GymPro.services.ResponsableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/responsable/")
public class ResponsableController {
    @Autowired
    ResponsableServices responsableServices;

    @PostMapping("clients")
    public ResponseEntity<?> ajouterClient(Client client  ){
        try{
            responsableServices.saveClient(client);
          return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
