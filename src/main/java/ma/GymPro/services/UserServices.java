package ma.GymPro.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import ma.GymPro.beans.Profil;
import ma.GymPro.beans.Seance;
import ma.GymPro.beans.User;
import ma.GymPro.repositories.ProfilRepository;
import ma.GymPro.repositories.SeanceRepository;
import ma.GymPro.repositories.ServiceRepository;
import ma.GymPro.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class UserServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServices.class);


    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    ServiceRepository serviceRepository;
    @Value("${aws.bucket}")
    private String bucketName;
    @Autowired
    ProfilRepository profilRepository;

    @Autowired
    SeanceRepository seanceRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public List<Seance> getAllSeance(){
        return seanceRepository.findAll();
    }

    public void updateProfile(Profil profil,String email){
        User    user =userRepository.findByEmail(email);
        user.setProfil(profil);
         userRepository.save(user);
    }

    public void updateMotDePasse(String motDePasse,String email){
        System.out.println(motDePasse);
        String encodedPass=bCryptPasswordEncoder.encode(motDePasse);
        userRepository.updatePassword(encodedPass,email );
    System.out.println(bCryptPasswordEncoder.matches(encodedPass,motDePasse));
    }

    public Profil getProfil(String name) {
        User user=userRepository.findByEmail(name);
        return user.getProfil();
    }




    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

   public void saveImg(String name,MultipartFile file){
        User user = userRepository.findByEmail(name);
        String path;
        File file1=convertMultiPartFileToFile(file);
       Profil profil =user.getProfil();
       path=user.getProfil().getCin()+"_"+file1.getName()+"_"+new Date().toString();
       profil.setImgFileName(path);
       user.setProfil(profil);
       userRepository.save(user);
       amazonS3.putObject(
               bucketName, user.getProfil().getImgFileName(),file1);
   }
   //
   public byte[] getImg(String name) throws IOException {
       User user = userRepository.findByEmail(name);

       S3Object s3object = amazonS3.getObject(bucketName,  user.getProfil().getImgFileName());

       byte[] byteArray = IOUtils.toByteArray(s3object.getObjectContent());
    return byteArray;
   }

   public void deleteImg(String name){
       User user = userRepository.findByEmail(name);
       amazonS3.deleteObject(bucketName,user.getProfil().getImgFileName());
       Profil profil= user.getProfil();
       user.getProfil().setImgFileName(null);
       userRepository.save(user);
   }




}

