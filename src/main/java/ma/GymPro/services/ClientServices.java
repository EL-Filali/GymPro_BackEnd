package ma.GymPro.services;

import com.amazonaws.services.s3.AmazonS3;
import ma.GymPro.beans.Achat;
import ma.GymPro.beans.Client;
import ma.GymPro.beans.Coupon;
import ma.GymPro.config.ocr.FactureCreator;
import ma.GymPro.dto.cart.CartDTORequest;
import ma.GymPro.dto.cart.CarteDTOResponse;
import ma.GymPro.repositories.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    private AmazonS3 amazonS3;
    @Value("${aws.bucket.service}")
    private String bucketName;
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


    public void createCart(CartDTORequest panier, String clientEmail){
        Achat achat=new Achat(panier);
        System.out.println( "BEFORE Size="+achat.getServices().size());
                Client client=clientRepository.findByEmail(clientEmail);
        List<Achat> achats=client.getAchat();
        achats.add(achat);
        client.setAchat(achats);
        Achat achatIfExist=achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
        if(achatIfExist!=null){
            List<ma.GymPro.beans.Service> services =achat.getServices();
            List<ma.GymPro.beans.Service> serviceListIf=achatIfExist.getServices();
            System.out.println( "Size="+serviceListIf.size() +"SIZE NEW =" +services.size());
            services.addAll(serviceListIf);
            achat.setServices(services);
        }
        achatRepository.deleteAncienCart(client);
        clientRepository.save(client);
        achat.setClient(client);
        achatRepository.save(achat);
    }
    public CarteDTOResponse getCart(String clientEmail) throws Exception {
        Achat achat =achatRepository.findByIsPaidAndClient_Email(false,clientEmail);

        if(achat==null)
            throw new Exception("ce client n a aucun panier ");
        else
            return new CarteDTOResponse(achat,amazonS3,bucketName);
    }
    public void deleteFromCart(Long id, String clientEmail){
        Achat achat =achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
                achat.getServices().removeIf(item -> item.getId().equals(id));
                achatRepository.save(achat);
    }
    public void addToAChat(Long id ,String clientEmail) throws Exception {
        Achat achat = achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
        Optional<ma.GymPro.beans.Service> optionalService=serviceRepository.findById(id);
        if(!optionalService.isPresent())
            throw new Exception("Aucun service avec cet ID");
        List<ma.GymPro.beans.Service> services=achat.getServices();
        services.add(optionalService.get());
        achat.setServices(services);
        achatRepository.save(achat);
    }

    public byte[] reglerAchatAvecCoupon(Long idCoupon,String clientEmail) throws Exception {
        Achat achat=achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
        Optional<Coupon> optionalCoupon=couponRepository.findById(idCoupon);
        if(optionalCoupon.isPresent())
            achat.payerAchat(optionalCoupon.get());
        else
            throw  new Exception("Coupon invalid");

        achatRepository.save(achat);

        return factureCreator.createInvoice(achat);
    }
    public byte[] reglerAchatSansCoupon(String clientEmail) throws Exception {
        Achat achat=achatRepository.findByIsPaidAndClient_Email(false,clientEmail);
        if(achat!=null)
            achat.payerAchat();
        else
            throw new Exception("Aucune carte deja");
        achatRepository.save(achat);
        return factureCreator.createInvoice(achat);
    }


    public byte[] reglerAchat(Long idCoupon,String clientEmail) throws Exception {
       if(idCoupon==null){
          return reglerAchatSansCoupon(clientEmail);
       }else{
          return reglerAchatAvecCoupon(idCoupon,clientEmail);
       }
    }





    public byte[] creePDFFacture(Long id, String email) throws DocumentException, com.itextpdf.text.DocumentException, IOException {
        Achat achat=achatRepository.findByIsPaidAndIdAndClient_Email(true,id,email);
        return factureCreator.createInvoice(achat);
    }
    public Coupon checkCoupon(String reference) throws Exception {
        Coupon coupon=couponRepository.findByReference(reference);
        if(coupon==null)
            throw new Exception("n'exist pas !");
        else if(coupon.isExpired()==true)
                throw new Exception("Coupon expiré");
            else return coupon;
    }
}
