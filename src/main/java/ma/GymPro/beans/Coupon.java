/***********************************************************************
 * Module:  Coupon.java
 * Author:  DELL
 * Purpose: Defines the Class Coupon
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;
@Entity
public class Coupon {
   @Id
   @GeneratedValue
   private Long id;
   private float remise;
   private String reference;


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public float getRemise() {
      return remise;
   }

   public void setRemise(float remise) {
      this.remise = remise;
   }

   public String getReference() {
      return reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

}
