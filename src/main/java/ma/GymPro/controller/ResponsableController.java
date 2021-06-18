package ma.GymPro.controller;

import ma.GymPro.beans.*;
import ma.GymPro.dto.CoursDTORequest;
import ma.GymPro.dto.ServiceDTORequest;
import ma.GymPro.services.ResponsableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/responsable/")
public class ResponsableController {
    @Autowired
    ResponsableServices responsableServices;

    @PostMapping("clients")
    public ResponseEntity<?> ajouterClient(@RequestBody Client client  ){
        try{
            responsableServices.saveClient(client);
          return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("coupons")
    public ResponseEntity<?>  createCoupons(@RequestBody Coupon coupon){
        responsableServices.createCoupon(coupon);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("coupons")
    public  ResponseEntity<?>  getAllCoupons(int pageNo, int pageSize, String sortBy){
        return new ResponseEntity(responsableServices.getAllCoupons(pageNo,pageSize,sortBy),HttpStatus.OK);
    }
    @PostMapping("/abonnement")
    public ResponseEntity<?> createAbo(@RequestBody ServiceDTORequest serviceDTORequest){
        responsableServices.createAbonnement(serviceDTORequest);
        return new ResponseEntity(HttpStatus.OK);

    }

   @PostMapping("/seances")
    public ResponseEntity<?> createSceance(Seance seance){
        try{
            responsableServices.saveSceance(seance);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/seances/{id}")
    public ResponseEntity<?> deleteSceance(@PathVariable Long id){
        try{
            responsableServices.deleteSceance(id);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/seances/{id}")
    public ResponseEntity<?> updateSceance(@PathVariable Seance seance){
        try{
            responsableServices.saveSceance(seance);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cours")
    public ResponseEntity<?> createCours(@RequestBody CoursDTORequest coursDTORequest){
        try{
            responsableServices.saveCours(coursDTORequest);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

}
