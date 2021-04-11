package ma.GymPro.services;

import ma.GymPro.beans.Client;
import ma.GymPro.beans.Coach;
import ma.GymPro.beans.Employe;
import ma.GymPro.beans.Responsable;
import ma.GymPro.repositories.ClientRepository;
import ma.GymPro.repositories.CoachRepository;
import ma.GymPro.repositories.EmployeRepository;
import ma.GymPro.repositories.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    ClientRepository clientRepository;


    @Autowired
    ResponsableRepository responsableRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CoachRepository coachRepository;

    public Employe          getEmployeById  (Long id) throws Exception {
        Optional<Employe> opt =employeRepository.findById(id);
        if(opt.isPresent())
            return opt.get();
        else
            throw new Exception("Aucun Employe avec cet id");
    }
    public Client           getClientById   (Long id) throws Exception {
        Optional<Client> opt = clientRepository.findById(id);

        if(opt.isPresent())
            return opt.get();
        else
            throw new Exception("Aucun Client avec cet id");
    }
    public Page<Client>     getAllClients   (int pageNo, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Client> result  =clientRepository.findAll(paging);
        return result;
    }
    public Page<Employe>    getAllEmployes  (int pageNo, int pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employe> result  =employeRepository.findAll(paging);
        return result;
    }
    public void             disableOrEnableClient   (Long id){
        clientRepository.disableClient(id);
    }
    public void             disableOrEnableEmploye  (Long id){
        employeRepository.disableEmploye(id);
    }
    public Responsable addEmploye(Responsable employe){
        employe.setPassword(bCryptPasswordEncoder.encode(employe.getProfil().getCin()+"@2021"));
        return responsableRepository.save(employe);
    }
    public Coach addEmploye(Coach employe){
        employe.setPassword(bCryptPasswordEncoder.encode(employe.getProfil().getCin()+"@2021"));
        return coachRepository.save(employe);
    }
}
