package ma.GymPro.services;

import com.amazonaws.services.s3.AmazonS3;
import ma.GymPro.beans.*;
import ma.GymPro.config.FactureCreator;
import ma.GymPro.dto.CoursDTORequest;
import ma.GymPro.dto.ServiceDTORequest;
import ma.GymPro.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsableServices {
    @Autowired
    SeanceRepository    seanceRepository;
    @Autowired
    CoursRepository    coursRepository;
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
    @Value("${aws.bucket.service}")
    private String bucketName;
    @Autowired
    private AmazonS3 amazonS3;

    public Client saveClient(Client client ){
        client.setPassword(bCryptPasswordEncoder.encode(client.getProfil().getCin()+"@2021"));
        return clientRepository.save(client);
    }
    public void createAbonnement(ServiceDTORequest serviceDTORequest){
        File file1=convertMultiPartFileToFile(serviceDTORequest.getImg());
        String path = serviceDTORequest.getService().getId() + "_" + file1.getName() + "_" + new Date().toString();
        serviceDTORequest.getService().setImgPath(path);
        amazonS3.putObject(bucketName, serviceDTORequest.getService().getImgPath(),file1);
        abonnementRepository.save((Abonnement) serviceDTORequest.getService());
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
    public void saveCours(CoursDTORequest coursDTORequest){
        File file1=convertMultiPartFileToFile(coursDTORequest.getImg());
        String path = coursDTORequest.getCours().getId() + "_" + file1.getName() + "_" + new Date().toString();
        coursDTORequest.getCours().setImgPath(path);
        amazonS3.putObject(bucketName, coursDTORequest.getCours().getImgPath(),file1);
        coursRepository.save(coursDTORequest.getCours());
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {

        }
        return file;
    }
}
