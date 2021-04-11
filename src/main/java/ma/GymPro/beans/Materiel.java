/***********************************************************************
 * Module:  Materiel.java
 * Author:  DELL
 * Purpose: Defines the Class Materiel
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;
@Entity
public class Materiel {
   @Id
   @GeneratedValue
   private Long id;
   private String libelle;
   private int qte;
   private Date dateAchat;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getLibelle() {
      return libelle;
   }

   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   public int getQte() {
      return qte;
   }

   public void setQte(int qte) {
      this.qte = qte;
   }

   public Date getDateAchat() {
      return dateAchat;
   }

   public void setDateAchat(Date dateAchat) {
      this.dateAchat = dateAchat;
   }
}
