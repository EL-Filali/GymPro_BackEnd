package ma.GymPro.repositories;

import ma.GymPro.beans.Actif;
import ma.GymPro.beans.Client;
import ma.GymPro.beans.StatusClient;
import ma.GymPro.beans.Suspendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Modifying
    @Query("Update Client a SET a.isBanned = " +
            "  CASE a.isBanned " +
                     "    WHEN TRUE THEN FALSE" +
            "    ELSE TRUE END WHERE a.id=:id")
    public void disableClient(@Param("id") Long id);

    Client findByEmail(String clientEmail);
    @Modifying
    @Query("update  Client  c set c.statusClient=:suspendu,c.suspendu=true  where  current_date >c.dateFinAbonnement ")
    void VerificationAbonnement(Suspendu suspendu);

    Integer countClientByStatusClient(StatusClient actif);

}
