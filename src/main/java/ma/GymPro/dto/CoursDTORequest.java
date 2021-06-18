package ma.GymPro.dto;

import lombok.Data;
import ma.GymPro.beans.Cours;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoursDTORequest {

    private Cours cours;
    private MultipartFile img;
}
