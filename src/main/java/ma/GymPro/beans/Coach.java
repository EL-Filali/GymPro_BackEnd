
package ma.GymPro.beans;

import javax.persistence.*;
import java.util.*;
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
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getNbSeance() {
      return nbSeance;
   }

   public void setNbSeance(int nbSeance) {
      this.nbSeance = nbSeance;
   }

   public int getMaxNbSeance() {
      return maxNbSeance;
   }

   public void setMaxNbSeance(int maxNbSeance) {
      this.maxNbSeance = maxNbSeance;
   }

   public List<Seance> getSeances() {
      return seances;
   }

   public void setSeances(List<Seance> seances) {
      this.seances = seances;
   }
}
