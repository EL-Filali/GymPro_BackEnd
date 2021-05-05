package ma.GymPro.controller;

import ma.GymPro.beans.Coach;
import ma.GymPro.beans.Responsable;
import ma.GymPro.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {


    @Autowired
    AdminServices adminServices;


    @GetMapping("employes")
    public ResponseEntity<?> getEmployes(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy){
        try{
            return new ResponseEntity(adminServices.getAllEmployes(pageNo,pageSize,sortBy), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity( e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("clients")
    public ResponseEntity<?> getClients(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy){
        try{
            return new ResponseEntity(adminServices.getAllClients(pageNo,pageSize,sortBy), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id) throws Exception {
        try{
            return new ResponseEntity(adminServices.getClientById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("employes/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
        try{
            return new ResponseEntity(adminServices.getEmployeById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("employes/{id}")
    public ResponseEntity<?> disableOrEnableEmploye(@PathVariable Long id){
        try{
            adminServices.disableOrEnableEmploye(id);
            return new ResponseEntity( HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("clients/{id}")
    public ResponseEntity<?> disableOrEnableClient(@PathVariable Long id){
        try{
            adminServices.disableOrEnableClient(id);
            return new ResponseEntity( HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("responsable")
    public ResponseEntity<?> postResponsable(@RequestBody Responsable responsable){
        try{
            adminServices.addEmploye(responsable);
            return new ResponseEntity( HttpStatus.OK);
        }catch(Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("coach")
    public ResponseEntity<?> postCoach(@RequestBody Coach coach){
        try{
            adminServices.addEmploye(coach);
            return new ResponseEntity( HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/factures")
    ResponseEntity<?> getAllFactures(@RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy, Principal principal){
        try{
            return new ResponseEntity( adminServices.getFactures(pageNo,pageSize,sortBy), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/factures/{id}")
    ResponseEntity<?> getFactures(@PathVariable Long id){
        try{
            return new ResponseEntity( adminServices.getFacture(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("coachs")
    ResponseEntity<?> getCoachs(@RequestParam(defaultValue = "0") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "id") String sortBy, Principal principal){
        try{
            return new ResponseEntity( adminServices.getCoachs(pageNo,pageSize,sortBy), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("responsables")
    ResponseEntity<?> getCoach(@RequestParam(defaultValue = "0") Integer pageNo,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "id") String sortBy){
        try{
            return new ResponseEntity( adminServices.getResponsables(pageNo,pageSize,sortBy), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
}
