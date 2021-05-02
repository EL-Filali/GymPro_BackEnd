/***********************************************************************
 * Module:  Achat.java
 * Author:  DELL
 * Purpose: Defines the Class Achat
 ***********************************************************************/
package ma.GymPro.beans;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.GymPro.beans.Client;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Achat {
   @Id
   @GeneratedValue
   private Long id;

   private boolean isPaid;
   @OneToOne(cascade={CascadeType.PERSIST})
   private Facture facture;
   @JsonIgnore
   @ManyToOne

   private Client client;
   @ManyToMany(mappedBy = "")
   private List<Service> services;
   @PrePersist
   protected  void prePersist(){
      isPaid=false;
   }
   public void payerAchat(Coupon coupon) throws Exception {

      Facture facture=new Facture();
      float somme = 0;
      for (Service service:services) {
         somme=+service.getPrix();
         if(service instanceof Abonnement){
            Abonnement abonnement= (Abonnement) service;
            this.client.achatAbonnement(abonnement);
         }
      }
      isPaid=true;
      if(coupon.isExpired()&&coupon==null)
         facture.setMontant(somme);
      else
         facture.setMontant(somme*coupon.getRemise());
      facture.setDate(new Date());
      this.facture=facture;

   }
   public void payerAchat() throws Exception {

      Facture facture=new Facture();
      float somme = 0;
      for (Service service:services) {
         System.out.println("Somme="+somme+"service .prix="+service.getPrix());
         somme=somme+service.getPrix();
         if(service instanceof Abonnement){
            Abonnement abonnement= (Abonnement) service;
            this.client.achatAbonnement(abonnement);
         }
      }

      isPaid=true;
      facture.setMontant(somme);
      facture.setDate(new Date());
      this.facture=facture;

   }
}
