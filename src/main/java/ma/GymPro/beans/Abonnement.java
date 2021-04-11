/***********************************************************************
 * Module:  Abonnement.java
 * Author:  DELL
 * Purpose: Defines the Class Abonnement
 ***********************************************************************/
package ma.GymPro.beans;
import ma.GymPro.beans.Service;
import javax.persistence.Entity;

@Entity
public class Abonnement extends Service {


   private String image;

   private int durée;



   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public int getDuree() {
      return durée;
   }

   public void setDurée(int durée) {
      this.durée = durée;
   }
}
