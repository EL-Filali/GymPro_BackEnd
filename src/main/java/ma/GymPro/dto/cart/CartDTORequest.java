package ma.GymPro.dto.cart;

import lombok.Data;
import ma.GymPro.beans.Achat;
import ma.GymPro.beans.Service;
import ma.GymPro.dto.achatDetails.AchatDetailsDTORequest;

import java.util.*;

@Data
public class CartDTORequest {
     private List<AchatDetailsDTORequest> achatDetails;
    CartDTORequest(){

    }
    public CartDTORequest(Achat achat){
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
        AchatDetailsDTORequest achatDetailsDTO;
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            achatDetailsDTO = new AchatDetailsDTORequest();
            achatDetailsDTO.setService((Service) pair.getKey());
            System.out.println("Added "+pair.getKey());
            achatDetailsDTO.setQte((Integer) pair.getValue());
            System.out.println("Added "+pair.getValue());
            this.achatDetails.add(achatDetailsDTO);
            it.remove();
        }
    }
}
