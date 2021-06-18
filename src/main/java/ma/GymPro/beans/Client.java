
package ma.GymPro.beans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter @Setter @NoArgsConstructor
public class Client extends User {
   private Date dateFinAbonnement;
   private Boolean suspendu;
   @ManyToOne
   private StatusClient statusClient;
   @OneToMany(cascade ={CascadeType.ALL},mappedBy = "client")
   @JsonIgnore
   private List<Achat> achat;
   @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
   protected Carte tag;

   @PrePersist
   private void onPresiste(){
      this.isBanned=false;
      this.statusClient=new Suspendu();
      this.dateCreation=new Date();
      this.suspendu=true;
      super.role="client";
   }








   public Client(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation, Carte tag, List<Conversation> conversations, Date dateFinAbonnement, Boolean suspendu, StatusClient statusClient, List<Achat> achat) {
      super(id, email, isBanned, password, profil, role, dateCreation, conversations);
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
