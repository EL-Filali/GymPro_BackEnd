package ma.GymPro.repositories;

import ma.GymPro.beans.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfcTagRepository extends JpaRepository<Carte,Long> {
}
