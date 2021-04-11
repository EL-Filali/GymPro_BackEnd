/***********************************************************************
 * Module:  Jour.java
 * Author:  DELL
 * Purpose: Defines the Class Jour
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.*;
@Entity
public class Jour {
   @Id
   @GeneratedValue
   private Long id;
   private String nomJour;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNomJour() {
      return nomJour;
   }

   public void setNomJour(String nomJour) {
      this.nomJour = nomJour;
   }
}
