package ma.GymPro.services;

import ma.GymPro.beans.*;
import ma.GymPro.config.FactureCreator;
import ma.GymPro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsableServices {
    @Autowired
    SeanceRepository    seanceRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AbonnementRepository abonnementRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    AchatRepository achatRepository;
    @Autowired
    FactureCreator factureCreator;

    public Client saveClient(Client client ){
        client.setPassword(bCryptPasswordEncoder.encode(client.getProfil().getCin()+"@2021"));
        return clientRepository.save(client);
    }
    public void createAbonnement(Abonnement abonnement){
        abonnementRepository.save(abonnement);
    }

    public void createCoupon(Coupon coupon){
        couponRepository.save(coupon);
    }
    public void deleteCoupon(Long id){
        couponRepository.deleteById(id);
    }

    public void expirerCoupon(Long id){

    }

    public Page<Coupon> getAllCoupons(int pageNo, int pageSize, String sortBy ){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return couponRepository.findAll( paging);
    }

    public byte[] payerAchat(Long idClient , Achat achat) throws Exception {

        Optional<Client> optionalClient =clientRepository.findById(idClient);
        if(!optionalClient.isPresent())
            throw  new Exception("Aucun Client avec cet id");
        else{
            Client client=optionalClient.get();
            List<Achat> achatList=client.getAchat();
            achatList.add(achat);
            client.setAchat(achatList);
            clientRepository.save(client);
            achat.setClient(client);
            achatRepository.save(achat);

            return factureCreator.createInvoice(achat);
        }


    }

    public void saveSceance(Seance seance){
        seanceRepository.save(seance);
    }
    public void deleteSceance(Long id){
        seanceRepository.deleteById(id);
    }
}
