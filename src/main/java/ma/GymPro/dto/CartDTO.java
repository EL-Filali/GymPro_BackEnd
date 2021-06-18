package ma.GymPro.dto;

import lombok.Data;
import ma.GymPro.beans.Achat;
import ma.GymPro.beans.Service;

import java.util.*;

@Data
public class CartDTO {
     private List<AchatDetailsDTO> achatDetails;
    CartDTO(){

    }
    public CartDTO(Achat achat){
        achatDetails=new ArrayList<AchatDetailsDTO>();
        HashMap<Service,Integer> details=new HashMap<Service,Integer>();
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
        AchatDetailsDTO achatDetailsDTO;
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            achatDetailsDTO = new AchatDetailsDTO();
            achatDetailsDTO.setService((Service) pair.getKey());
            System.out.println("Added "+pair.getKey());
            achatDetailsDTO.setQte((Integer) pair.getValue());
            System.out.println("Added "+pair.getValue());
            this.achatDetails.add(achatDetailsDTO);
            it.remove();
        }
    }
}
