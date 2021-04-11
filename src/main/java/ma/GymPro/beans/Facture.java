package ma.GymPro.beans;
/***********************************************************************
 * Module:  Facture.java
 * Author:  DELL
 * Purpose: Defines the Class Facture
 ***********************************************************************/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.*;
@Entity
public class Facture {
   @Id
   @GeneratedValue
   private Long id;
   private float montant;
   private Date date;
   @OneToOne
   private Achat achat;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public float getMontant() {
      return montant;
   }

   public void setMontant(float montant) {
      this.montant = montant;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public Achat getAchat() {
      return achat;
   }

   public void setAchat(Achat achat) {
      this.achat = achat;
   }
}
