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
       Date date= client.getDateFinAbonnement();
       int mounths =date.getMonth()+abonnement.getDuree();
       date.setMonth(mounths);
       client.setDateFinAbonnement(date);
    }


}
