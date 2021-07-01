package ma.GymPro.controller;


import ma.GymPro.beans.Profil;
import ma.GymPro.dto.Message.MessageDTORequest;
import ma.GymPro.dto.connexion.ConnexionRequest;
import ma.GymPro.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    UserServices userServices;

    @PutMapping("profil")
    public ResponseEntity<?> updateProfil(@RequestBody Profil profil, Principal principal){
        try{
            userServices.updateProfile(profil,principal.getName());
            return new ResponseEntity( HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("password")

    public ResponseEntity<?> changerMotDePasse(@RequestBody ConnexionRequest connexionRequest, Principal principal){
        try{
            userServices.updateMotDePasse(connexionRequest.getPassword(),principal.getName());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("profil")
    public ResponseEntity<?> getProfil( Principal principal){
        try{
            Profil profil =userServices.getProfil(principal.getName());
            return new ResponseEntity(profil,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public ResponseEntity<?> saveImage(@RequestParam("file") MultipartFile file, Principal principal) {
        try {


           userServices.saveImg(principal.getName(),file );
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<?> getImage( Principal principal) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity(userServices.getImg(principal.getName()),headers,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/image", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteImage( Principal principal) {
        try {
            userServices.deleteImg(principal.getName());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/topic/message")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTORequest messageDTORequest, Principal principal) throws IOException {
        try {
            userServices.sendMessage(messageDTORequest, principal.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }


    @GetMapping("/seances/{id}")
    public ResponseEntity<?> getSeance(@PathVariable Long id){
        try{
            return new ResponseEntity(userServices.getSceance(id),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

   @GetMapping("/image/{imgpath}")
    public ResponseEntity<?> getImage( @PathVariable String imgpath,Principal principal) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity(userServices.getImg(principal.getName()),headers,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
