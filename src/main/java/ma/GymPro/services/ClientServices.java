package ma.GymPro.services;

import ma.GymPro.beans.*;
import ma.GymPro.config.FactureCreator;
import ma.GymPro.repositories.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServices {
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AchatRepository achatRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private FactureCreator factureCreator;

    public Page<Achat> getFactures(int pageNo, int pageSize, String sortBy, String email){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return achatRepository.findByIsPaidAndClient_Email(true,email,paging);
    }

    public Achat getFacture(Long id,String clientEmail) throws Exception {

        Achat facture= achatRepository.findByIsPaidAndIdAndClient_Email(true,id,clientEmail);

        if(facture==null){
            throw new Exception("Aucune Facture avec cet ID");

        }
        return facture;
    }


    public void createCart(Achat achat, String clientEmail){
        //List<ma.GymPro.beans.Service> services=achat.getServices();
        Client client=clientRepository.findByEmail(clientEmail);
        List<Achat> achats=client.getAchat();
        achats.add(achat);
        client.setAchat(achats);
        achatRepository.deleteAncienCart(client);
        clientRepository.save(client);
        achat.setClient(client);
        achatRepository.save(achat);
       /* //achat.setServices(new ArrayList<>());

        System.out.println("\n 1 \n");


        System.out.println("\n 2 \n");
        //achat.setServices(services);
       // achatRepository.save(achat);
        System.out.println("\n 3 \n");


        ;*/

    }
    public void addToCart(Long id, String clientEmail){
        Achat achat=new Achat();
        achat.setClient(clientRepository.findByEmail(clientEmail));
        achatRepository.save(achat);
        achat.setServices(new ArrayList<>());
        achat.getServices().add(serviceRepository.findById(id).get());
        achatRepository.save(achat);

    }

    public byte[] reglerAchat(Long idCoupon,String clientEmail) throws Exception {
        Achat achat=achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
        Client client=clientRepository.findByEmail(clientEmail);
        Optional<Coupon> optionalCoupon=couponRepository.findById(idCoupon);
        if(optionalCoupon.isPresent())
            achat.payerAchat(optionalCoupon.get());
        else
            achat.payerAchat(optionalCoupon.get());

        achatRepository.save(achat);

        return factureCreator.createInvoice(achat);
    }
    public byte[] reglerAchat(String clientEmail) throws Exception {
        Achat achat=achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
        if(achat!=null)
            achat.payerAchat();
        else
            throw new Exception("Aucune carte deja");
        achatRepository.save(achat);

        return factureCreator.createInvoice(achat);
    }

    public List<Abonnement> GetAbonnements(){

        return abonnementRepository.findAll();
    }

    public  Abonnement getAbonnement(Long id) throws Exception {
        Optional<Abonnement> optionalAbonnement= abonnementRepository.findById(id);
        if(optionalAbonnement.isPresent())
            return optionalAbonnement.get();
        else
            throw new Exception("Aucun Service Avec cet id");
    }

    public byte[] creePDFFacture(Long id, String email) throws DocumentException, com.itextpdf.text.DocumentException, IOException {
        Achat achat=achatRepository.findByIsPaidAndIdAndClient_Email(true,id,email);
        return factureCreator.createInvoice(achat);
    }

}
