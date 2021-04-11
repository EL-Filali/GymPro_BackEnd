package ma.GymPro.repositories;

import ma.GymPro.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("Update Client set isBanned = true where id=:id")
    public void disableClient(Long id);
}
