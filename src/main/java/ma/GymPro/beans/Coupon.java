/***********************************************************************
 * Module:  Coupon.java
 * Author:  DELL
 * Purpose: Defines the Class Coupon
 ***********************************************************************/
package ma.GymPro.beans;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;
@Entity
@Getter @Setter
public class Coupon {
   @Id
   @GeneratedValue
   private Long id;
   private float remise;
   private String reference;
   private boolean expired;




}
