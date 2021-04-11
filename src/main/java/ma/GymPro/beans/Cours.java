/***********************************************************************
 * Module:  Cours.java
 * Author:  DELL
 * Purpose: Defines the Class Cours
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;
@Entity
public class Cours {
   @Id
   @GeneratedValue
   private int id;
   private int nomCours;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getNomCours() {
      return nomCours;
   }

   public void setNomCours(int nomCours) {
      this.nomCours = nomCours;
   }
}
