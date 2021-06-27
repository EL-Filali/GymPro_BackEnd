package ma.GymPro.dto.service;

import lombok.Data;
import ma.GymPro.beans.Service;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ServiceDTORequest {
    private Service service;
    private MultipartFile img;
}
