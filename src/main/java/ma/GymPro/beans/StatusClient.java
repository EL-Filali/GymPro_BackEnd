
package ma.GymPro.beans;
import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class StatusClient {
    @Id
    protected String nomStatus;

    public void achatAbonnement(Client client,Abonnement abonnement) throws Exception {
   }

    public String getNomStatus() {
        return nomStatus;
    }
}
