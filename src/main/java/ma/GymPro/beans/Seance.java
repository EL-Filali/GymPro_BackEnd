
package ma.GymPro.beans;
import javax.persistence.*;

@Entity
public class Seance {
   @Id
   @GeneratedValue
   private int id;
   private int heureDebut;
   private int heureFin;
   @ManyToOne
   private Cours cours;
   @ManyToOne
   private Jour jour;
   @ManyToOne
   private Coach coach;

   public void affecterCoach(Coach coach) throws Exception {

         if(coach.getNbSeance()==coach.getNbSeance()){
            throw new Exception("") ;
         }else{
            coach.setNbSeance(coach.getNbSeance()+1);
            this.setCoach(coach);

      }
   }
   public void supprimerSceance(){
      this.coach.setNbSeance(coach.getNbSeance()-1);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getHeureDebut() {
      return heureDebut;
   }

   public void setHeureDebut(int heureDebut) {
      this.heureDebut = heureDebut;
   }

   public int getHeureFin() {
      return heureFin;
   }

   public void setHeureFin(int heureFin) {
      this.heureFin = heureFin;
   }

   public Cours getCours() {
      return cours;
   }

   public void setCours(Cours cours) {
      this.cours = cours;
   }

   public Jour getJour() {
      return jour;
   }

   public void setJour(Jour jour) {
      this.jour = jour;
   }

   public Coach getCoach() {
      return coach;
   }

   public void setCoach(Coach coach) {
      this.coach = coach;
   }
}
