
package ma.GymPro.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class Profil {
   @Id
   @GeneratedValue
   private Long id;
   private String nom;
   private String prenom;
   private String cin;
   private String  imgFileName;
   private Date dateNaissance;
   private String genre;
   private String telephone;
   private Boolean connected;

   public Profil(Long id, String nom, String prenom, String cin, String imgFileName, Date dateNaissance, String genre, String telephone) {
      this.id = id;
      this.nom = nom;
      this.prenom = prenom;
      this.cin = cin;
      this.imgFileName = imgFileName;
      this.dateNaissance = dateNaissance;
      this.genre = genre;
      this.telephone = telephone;
   }

}
