/***********************************************************************
 * Module:  Client.java
 * Author:  DELL
 * Purpose: Defines the Class Client
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.*;
import java.util.*;
@Entity
public class Client extends User {

   private Date dateFinAbonnement;
   private Boolean suspendu;
   @OneToOne
   private StatusClient statusClient;
   @OneToMany
   private List<Achat> achat;
   @PrePersist
   private void onPresiste(){
      this.statusClient=new Suspendu();
      this.dateCreation=new Date();
      this.suspendu=true;
      super.role="client";
   }



   public Date getDateFinAbonnement() {
      return dateFinAbonnement;
   }

   public void setDateFinAbonnement(Date dateFinAbonnement) {
      this.dateFinAbonnement = dateFinAbonnement;
   }

   public List<Achat> getAchat() {
      return achat;
   }

   public void setAchat(List<Achat> achat) {
      this.achat = achat;
   }

   public StatusClient getStatusClient() {
      return statusClient;
   }

   public void setStatusClient(StatusClient statusClient) {
      this.statusClient = statusClient;
   }
   public void achatAbonnement(Abonnement abonnement) throws Exception {
      this.statusClient.achatAbonnement(this,abonnement);
   }



   public Boolean getSuspendu() {
      return suspendu;
   }

   public void setSuspendu(Boolean suspendu) {
      this.suspendu = suspendu;
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

   public Client() {
      super();
   }



}
