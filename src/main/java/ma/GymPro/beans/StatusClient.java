/***********************************************************************
 * Module:  StatusClient.java
 * Author:  DELL
 * Purpose: Defines the Class StatusClient
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class StatusClient {
    @Id
    protected String nomStatus= this.getClass().getSimpleName();

    public void achatAbonnement(Client client,Abonnement abonnement) throws Exception {
   }

    public String getNomStatus() {
        return nomStatus;
    }
}
