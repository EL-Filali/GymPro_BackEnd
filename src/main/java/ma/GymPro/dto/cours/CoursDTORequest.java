package ma.GymPro.dto.cours;

import lombok.Data;
import ma.GymPro.beans.Cours;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoursDTORequest {

    private Cours cours;
    private MultipartFile img;
}
