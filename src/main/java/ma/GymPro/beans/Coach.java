
package ma.GymPro.beans;

import lombok.Data;

import javax.persistence.*;
import java.util.*;
@Data
@Entity
public class Coach extends Employe {

   private int nbSeance;
   private int maxNbSeance;
   @OneToMany(cascade = {CascadeType.REMOVE})
   private List<Seance> seances;

   @PrePersist
   private void onPresiste(){
      this.nbSeance=0;
      this.dateCreation=new Date();
      super.role="coach";
      isBanned=false;
      maxNbSeance=10;
   }


}
