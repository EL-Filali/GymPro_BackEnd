/***********************************************************************
 * Module:  Achat.java
 * Author:  DELL
 * Purpose: Defines the Class Achat
 ***********************************************************************/
package ma.GymPro.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.GymPro.dto.AchatDetailsDTO;
import ma.GymPro.dto.CartDTO;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Achat(CartDTO panier) {
       services=new ArrayList<>();
       for (AchatDetailsDTO detailsDTO:panier.getAchatDetails()) {
          System.out.println(detailsDTO.getQte()+"==="+detailsDTO.getService().id);
          for(int i=0;i<detailsDTO.getQte();i++)
             services.add(detailsDTO.getService());
       }
    }

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
