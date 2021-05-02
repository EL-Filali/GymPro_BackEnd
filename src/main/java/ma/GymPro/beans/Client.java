
package ma.GymPro.beans;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.*;
@Entity
@Getter @Setter @NoArgsConstructor
public class Client extends User {
   private Date dateFinAbonnement;
   private Boolean suspendu;
   @ManyToOne
   private StatusClient statusClient;
   @OneToMany(cascade ={CascadeType.ALL},mappedBy = "client")
   private List<Achat> achat;

   @PrePersist
   private void onPresiste(){
      this.isBanned=false;
      this.statusClient=new Suspendu();
      this.dateCreation=new Date();
      this.suspendu=true;
      super.role="client";
   }








   public Client(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation, NfcTag tag, List<Conversation> conversations, Date dateFinAbonnement, Boolean suspendu, StatusClient statusClient, List<Achat> achat) {
      super(id, email, isBanned, password, profil, role, dateCreation, tag, conversations);
      this.dateFinAbonnement = dateFinAbonnement;
      this.suspendu = suspendu;
      this.statusClient = statusClient;
      this.achat = achat;
   }

   public Client(Date dateFinAbonnement, Boolean suspendu, StatusClient statusClient, List<Achat> achat) {
      this.dateFinAbonnement = dateFinAbonnement;
      this.suspendu = suspendu;
      this.statusClient = statusClient;
      this.achat = achat;
   }

   public void achatAbonnement(Abonnement abonnement) throws Exception {
      this.statusClient.achatAbonnement(this,abonnement);
   }








}
