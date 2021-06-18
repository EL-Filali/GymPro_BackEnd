/***********************************************************************
 * Module:  NfcTag.java
 * Author:  DELL
 * Purpose: Defines the Class NfcTag
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Carte {
   private String tag;
   @Id
   @GeneratedValue
   private Long id;

   public String getTag() {
      return tag;
   }

   public void setTag(String tag) {
      this.tag = tag;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
