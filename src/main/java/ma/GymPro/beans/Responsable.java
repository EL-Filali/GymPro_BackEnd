/***********************************************************************
 * Module:  Responsable.java
 * Author:  DELL
 * Purpose: Defines the Class Responsable
 ***********************************************************************/
package ma.GymPro.beans;
import ma.GymPro.beans.Employe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.*;
@Entity
public class Responsable extends Employe  {
   @PrePersist
   private void onPresiste(){

      this.dateCreation=new Date();
      super.role="responsable";
   }

   public Responsable(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation, double salaire) {
      super(id, email, isBanned, password, profil, role, dateCreation, salaire);
   }

   public Responsable() {
   }

   @Override
   protected Object clone() throws CloneNotSupportedException {
      return super.clone();
   }
}
