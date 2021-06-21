package ma.GymPro.dto;

import com.amazonaws.services.s3.AmazonS3;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.GymPro.beans.Achat;
import ma.GymPro.beans.Service;

import java.io.IOException;
import java.util.*;

@Data
@NoArgsConstructor
public class CarteDTOResponse {
    private List<AchatDetailsDTOResponse> achatDetails;

    public  CarteDTOResponse(Achat achat, AmazonS3 s3, String bucketName) throws IOException {
        achatDetails=new ArrayList<>();
        HashMap<Service,Integer> details=new HashMap<>();
        for (Service service:achat.getServices()) {

            if(details.containsKey(service)){
                int c=details.get(service);
                c=c+1;
                details.put(service,c);
            }else{
                details.put(service,1);
            }
        }
        Iterator it = details.entrySet().iterator();
        AchatDetailsDTOResponse achatDetailsDTOResponse;
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            achatDetailsDTOResponse = new AchatDetailsDTOResponse();
            achatDetailsDTOResponse.setService(new ServiceDTOResponse((Service) pair.getKey(),s3,bucketName));
            achatDetailsDTOResponse.setQte((Integer) pair.getValue());
            this.achatDetails.add(achatDetailsDTOResponse);
            it.remove();
        }
    }
}
