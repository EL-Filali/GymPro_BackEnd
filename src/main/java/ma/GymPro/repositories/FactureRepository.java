package ma.GymPro.repositories;

import ma.GymPro.beans.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {
    @Query("select sum(f.montant) from Facture f")
    Float sumMontantTotalFacture();
    @Query("select  sum(f.montant) from Facture f where f.date between :date1 and :date2")
    Float getMontantBetween(Date date1, Date date2);
    @Query("select count(f) from Facture f ")
    Integer countFacture();

}
