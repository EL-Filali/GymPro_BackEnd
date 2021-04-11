package ma.GymPro.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import ma.GymPro.beans.Profil;
import ma.GymPro.beans.Seance;
import ma.GymPro.beans.User;
import ma.GymPro.repositories.ProfilRepository;
import ma.GymPro.repositories.SeanceRepository;
import ma.GymPro.repositories.UserRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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

import java.util.List;

@Service
public class UserServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServices.class);
    @Autowired
    private AmazonS3 amazonS3;

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
         userRepository.updateProfil(profil,email);
    }

    public void updateMotDePasse(String motDePasse,String email){
        userRepository.updatePassword(bCryptPasswordEncoder.encode(motDePasse),email );
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

        File file1=convertMultiPartFileToFile(file);
       user.getProfil().setImgFileName(user.getProfil().getCin()+"_"+file1.getName());
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
   }

}

