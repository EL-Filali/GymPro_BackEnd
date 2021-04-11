/***********************************************************************
 * Module:  Employe.java
 * Author:  DELL
 * Purpose: Defines the Class Employe
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.*;
import java.util.*;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Employe extends User {

   protected double salaire;

   public double getSalaire() {
      return salaire;
   }

   public void setSalaire(double salaire) {
      this.salaire = salaire;
   }

   public Employe(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation, double salaire) {
      super(id, email, isBanned, password, profil, role, dateCreation);
      this.salaire = salaire;
   }

   public Employe() {
   }
}
