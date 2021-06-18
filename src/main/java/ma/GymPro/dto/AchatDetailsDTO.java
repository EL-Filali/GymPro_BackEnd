package ma.GymPro.dto;

import lombok.Data;
import ma.GymPro.beans.Service;
@Data
public class AchatDetailsDTO {
    private Service service;
    private Integer qte;
}
