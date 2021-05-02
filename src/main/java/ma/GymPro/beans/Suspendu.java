
package ma.GymPro.beans;

import javax.persistence.Entity;
import java.util.*;
@Entity
public class Suspendu extends StatusClient {

   public Suspendu(){
     super.nomStatus = "suspendu";
   }

    @Override
    public void achatAbonnement(Client client,Abonnement abonnement ){
        Date date=new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, abonnement.getDuree());
        date = calendar.getTime();

        client.setSuspendu(false);
        client.setStatusClient(new Actif());
        client.setDateFinAbonnement(date);
    }
    /*public void reglerAchat(Client client ,Achat achat){
        List<Service> services =achat.getServices();
        for (Service service: services
        ) {
            service instanceof Abonnement ? ((Abonnement) service) : null;

        }

    }*/






}
