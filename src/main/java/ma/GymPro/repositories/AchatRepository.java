package ma.GymPro.repositories;

import ma.GymPro.beans.Achat;
import ma.GymPro.beans.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public interface AchatRepository extends JpaRepository<Achat,Long> {

     Page<Achat> findByIsPaidAndClient_Email(boolean paid, String email, Pageable pageable);

     Achat findByIsPaidAndIdAndClient_Email(boolean paid, Long id, String clientEmail);

     Achat findByIsPaidAndClient_Email(boolean paid, String clientEmail);
    @Transactional
    @Modifying
    @Query("delete from Achat a where a.isPaid<>true and a.client =:clientEmail ")
     void deleteAncienCart( Client clientEmail);


}
