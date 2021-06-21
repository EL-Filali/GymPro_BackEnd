package ma.GymPro.controller;

import ma.GymPro.beans.Coupon;
import ma.GymPro.dto.CartDTO;
import ma.GymPro.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/client/")
public class ClientController {
    @Autowired
    ClientServices  clientServices;
    @Value("${aws.bucket.service}")
    private String bucketName;
    @GetMapping("/factures")
    ResponseEntity<?> getAllFactures(@RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy, Principal principal){
        try{
            return new ResponseEntity( clientServices.getFactures(pageNo,pageSize,sortBy,principal.getName()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/cart/services/{id}")
    ResponseEntity<?> deleteFromCart(@PathVariable Long id ,Principal principal){
        try {
            clientServices.deleteFromCart(id,principal.getName());
            return new ResponseEntity(
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(
                    HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/factures/{id}")
    ResponseEntity<?> getFactures(@PathVariable Long id,Principal principal){
        try{
            return new ResponseEntity( clientServices.getFacture(id,principal.getName()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cart")
    ResponseEntity<?> createCarte(@RequestBody CartDTO achat, Principal principal){
        try{
            clientServices.createCart(achat,principal.getName());
            return new ResponseEntity( HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/factures/{idCoupon}")
    ResponseEntity<?> reglerAchat(@PathVariable Long idCoupon,Principal principal){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity( clientServices.reglerAchat(idCoupon, principal.getName()),headers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/factures/")
    ResponseEntity<?> reglerAchat(Principal principal){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity( clientServices.reglerAchat( principal.getName()),headers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/cart")
    ResponseEntity<?> getCart(Principal principal){
        try{

            return new ResponseEntity( clientServices.getCart(principal.getName()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/factures/{id}/pdf")
    ResponseEntity<?> getFacturePDF(@PathVariable Long id,Principal principal){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity( clientServices.creePDFFacture(id,principal.getName()),headers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("coupons")
    ResponseEntity<?> checkCoupon(@RequestBody Coupon coupon){
        try {
            return new ResponseEntity<>(clientServices.checkCoupon(coupon.getReference()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/config/paypal")
    public String getClientPaypalId(){
        return "AQywOLNACD40AwxTZckMChsnk6QsKRbZsbSfXmtG97xGTNzoAX7Sl5b8VKpGzdprUxupq9TscMkx66Aw";
    }
}
