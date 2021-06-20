package ma.GymPro.controller;

import ma.GymPro.beans.Client;
import ma.GymPro.beans.Coupon;
import ma.GymPro.beans.Seance;
import ma.GymPro.beans.Service;
import ma.GymPro.dto.CoursDTORequest;
import ma.GymPro.services.ResponsableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

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

    @RequestMapping(value = "/abonnement", method = RequestMethod.POST)
    public ResponseEntity<?> createAbo(@RequestParam("service") Service service, @RequestParam("img") MultipartFile file){
        responsableServices.createAbonnement(service,file);
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
    @GetMapping("coachs")
    ResponseEntity<?> getCoachs(@RequestParam(defaultValue = "0") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "id") String sortBy, Principal principal){
        try{
            return new ResponseEntity( responsableServices.getCoachs(pageNo,pageSize,sortBy), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }


}
