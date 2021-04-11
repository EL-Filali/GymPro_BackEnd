package ma.GymPro.repositories;

import ma.GymPro.beans.NfcTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfcTagRepository extends JpaRepository<NfcTag,Long> {
}
