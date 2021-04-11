package ma.GymPro.repositories;

import ma.GymPro.beans.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable,Long> {
}
