package ma.GymPro.beans;
/***********************************************************************
 * Module:  Facture.java
 * Author:  DELL
 * Purpose: Defines the Class Facture
 ***********************************************************************/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.*;
@Entity
@Getter @Setter @NoArgsConstructor
public class Facture {
   @Id
   @GeneratedValue
   private Long id;
   private float montant;
   private Date date;
   @OneToOne @JsonIgnore
   private Achat achat;


}
