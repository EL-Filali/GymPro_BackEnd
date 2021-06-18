/***********************************************************************
 * Module:  Cours.java
 * Author:  DELL
 * Purpose: Defines the Class Cours
 ***********************************************************************/
package ma.GymPro.beans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
public class Cours {
   @Id
   @GeneratedValue
   private int id;
   private String nomCours;
   private String description;
   @JsonIgnore
   private String imgPath;

}
