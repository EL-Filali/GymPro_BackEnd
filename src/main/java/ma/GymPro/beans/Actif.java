package ma.GymPro.beans; /***********************************************************************
 * Module:  Actif.java
 * Author:  DELL
 * Purpose: Defines the Class Actif
 ***********************************************************************/

import ma.GymPro.beans.StatusClient;

import javax.persistence.Entity;
import java.util.*;

@Entity
public class Actif extends StatusClient {

  public Actif(){
     this.nomStatus="actif";
  }

    @Override
    public void achatAbonnement(Client client, Abonnement abonnement) throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(client.getDateFinAbonnement());
        calendar.add(Calendar.MONTH, abonnement.getDuree());

       client.setDateFinAbonnement(calendar.getTime());
    }


}
