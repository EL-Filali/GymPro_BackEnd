package ma.GymPro.services;

import ma.GymPro.beans.*;
import ma.GymPro.dto.Analytics.AnalyticsDTO;
import ma.GymPro.dto.Analytics.DataSetsDTO;
import ma.GymPro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

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


    @Autowired
    AchatRepository achatRepository;

    @Autowired
    FactureRepository factureRepository;

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
        Page<Client> result=clientRepository.findAll(paging);
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

    public Page<Achat> getFactures(int pageNo, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return achatRepository.findAll(paging);
    }

    public Achat getFacture(Long id) throws Exception {

        Optional<Achat> achatOptional= achatRepository.findById(id);

        if(!achatOptional.isPresent()){
            throw new Exception("Aucune Facture avec cet ID");

        }else
            return achatOptional.get();
    }

    public Page<Coach> getCoachs(int pageNo, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return coachRepository.findAll(paging);
    }
    public Page<Responsable> getResponsables(int pageNo, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return responsableRepository.findAll(paging);
    }
    public AnalyticsDTO getAnalytics(){
        DateFormatSymbols french_dfs = new DateFormatSymbols(Locale.FRENCH);
        String[] shortMonths = french_dfs.getShortMonths();
        List<String> label=new ArrayList<>();
        List<Float> data=new ArrayList<>();
        for (int i=0;i<4;i++) {
            Float profit=factureRepository.getMontantBetween(getFirstDateOfLastMount(i),getLastDateOfLastMount(i));
            if(profit==null)
                profit=0.00F;
            data.add(profit);
            label.add(Arrays.asList(shortMonths).get(getFirstDateOfLastMount(i).getMonth()));
        }
        DataSetsDTO dataSets=new DataSetsDTO();
        dataSets.setData(data);
        dataSets.setLabel(label);
        AnalyticsDTO analytics=new AnalyticsDTO(factureRepository.countFacture(),factureRepository.sumMontantTotalFacture()-employeRepository.sumSalaireEmploye(),clientRepository.countClientByStatusClient(new Actif()),dataSets);

       return analytics;

    }
    public Date getFirstDateOfLastMount(Integer last){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -last);
        System.out.println(cal.get(Calendar.MONTH));
        LocalDate initial = LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
                LocalDate start = initial.withDayOfMonth(1);
        return Date.from(start.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }


    public Date getLastDateOfLastMount(Integer last){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -last);
        LocalDate initial = LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        return Date.from(end.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
