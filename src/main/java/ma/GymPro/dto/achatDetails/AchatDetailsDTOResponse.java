package ma.GymPro.dto.achatDetails;

import lombok.Data;
import ma.GymPro.dto.service.ServiceDTOResponse;

@Data
public class AchatDetailsDTOResponse {
    private ServiceDTOResponse service;
    private Integer qte;
}
