
package ma.GymPro.beans;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seance {
   @Id
   @GeneratedValue
   private Long  id;
   private int heureDebut;
   private int heureFin;
   @ManyToOne
   private Cours cours;
   @ManyToOne
   private Jour jour;
   @ManyToOne
   private Coach coach;

   public void affecterCoach(Coach coach) throws Exception {

         if(coach.getNbSeance()==coach.getMaxNbSeance()){
            throw new Exception("Nombre des sceances max atteint") ;
         }else{
            coach.setNbSeance(coach.getNbSeance()+1);
            this.setCoach(coach);

      }
   }
   public void supprimerSceance(){
      this.coach.setNbSeance(coach.getNbSeance()-1);
   }

}
