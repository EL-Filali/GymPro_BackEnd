package ma.GymPro.repositories;

import ma.GymPro.beans.StatusClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStateRepository extends JpaRepository<StatusClient,String> {
}
