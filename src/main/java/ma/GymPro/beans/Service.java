/***********************************************************************
 * Module:  Service.java
 * Author:  DELL
 * Purpose: Defines the Class Service
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Service {
   @Id
   @GeneratedValue
   protected Long id;

   protected float prix;

   protected int description;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public float getPrix() {
      return prix;
   }

   public void setPrix(float prix) {
      this.prix = prix;
   }

   public int getDescription() {
      return description;
   }

   public void setDescription(int description) {
      this.description = description;
   }
}
