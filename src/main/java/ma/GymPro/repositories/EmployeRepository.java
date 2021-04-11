package ma.GymPro.repositories;

import ma.GymPro.beans.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {
    @Query("Update Employe set isBanned = true where id=:id")
    public void disableEmploye(Long id);
}
