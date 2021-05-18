/***********************************************************************
 * Module:  Cours.java
 * Author:  DELL
 * Purpose: Defines the Class Cours
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Cours {
   @Id
   @GeneratedValue
   private int id;
   private String nomCours;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNomCours() {
      return nomCours;
   }

   public void setNomCours(String nomCours) {
      this.nomCours = nomCours;
   }
}
