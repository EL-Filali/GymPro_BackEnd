package ma.GymPro.dto;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.Data;
import ma.GymPro.beans.Service;

import java.io.IOException;

@Data
public class ServiceDTOResponse {
    private Service service;
    private byte[] imgBytes;


    public ServiceDTOResponse(Service service, AmazonS3 s3, String bucketName) throws IOException {
        S3Object s3object = s3.getObject(bucketName,  service.getImgPath());
        imgBytes = IOUtils.toByteArray(s3object.getObjectContent());
        this.service =service;
    }

}
