package ma.GymPro.dto.Analytics;

import lombok.Data;
import lombok.NoArgsConstructor;
import ma.GymPro.beans.Client;

@Data
@NoArgsConstructor
public class ClientAnalyticsDTO {
    private Long id;
    private String cin;
    private String name;
    private String email;

    public ClientAnalyticsDTO(Client client){
        id= client.getId();
        cin=client.getProfil().getCin();
        name=client.getProfil().getPrenom()+" "+client.getProfil().getPrenom();
        email= client.getEmail();
    }
}
