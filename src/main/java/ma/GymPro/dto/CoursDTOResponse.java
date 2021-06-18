package ma.GymPro.dto;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.Data;
import ma.GymPro.beans.Cours;

import java.io.IOException;

@Data
public class CoursDTOResponse {


    private Cours cours;
    private byte[] imgBytes;


    public CoursDTOResponse(Cours cours, AmazonS3 s3, String bucketName) throws IOException {
        S3Object s3object = s3.getObject(bucketName,  cours.getImgPath());
        imgBytes = IOUtils.toByteArray(s3object.getObjectContent());
        this.cours =cours;
    }
}
