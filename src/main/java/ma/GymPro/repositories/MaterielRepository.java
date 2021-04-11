package ma.GymPro.repositories;

import ma.GymPro.beans.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel,Long> {
}
