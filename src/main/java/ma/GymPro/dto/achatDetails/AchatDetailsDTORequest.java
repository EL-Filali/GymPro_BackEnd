package ma.GymPro.dto.achatDetails;

import lombok.Data;
import ma.GymPro.beans.Service;
@Data
public class AchatDetailsDTORequest {
    private Service service;
    private Integer qte;
}
