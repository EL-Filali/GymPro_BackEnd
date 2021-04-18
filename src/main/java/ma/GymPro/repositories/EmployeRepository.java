package ma.GymPro.repositories;

import ma.GymPro.beans.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {
    @Modifying
    @Query("UPDATE Employe a " +
            "SET a.isBanned = " +
            "  CASE a.isBanned " +
            "    WHEN TRUE THEN FALSE" +
            "    ELSE TRUE END WHERE a.id=:id")
    public void disableEmploye(@Param("id") Long id);
}
