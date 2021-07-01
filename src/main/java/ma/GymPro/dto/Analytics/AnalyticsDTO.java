package ma.GymPro.dto.Analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsDTO {
    private List<ClientAnalyticsDTO> last5Clients;
    private Integer nombreFactures;
    private Float revenue;
    private  Integer nombreClient;
    private DataSetsDTO datasets;

}
