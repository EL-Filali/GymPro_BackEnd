package ma.GymPro.repositories;

import ma.GymPro.beans.Profil;
import ma.GymPro.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String Email);

    @Query("update  User set password=:password  where email=:email")
    public Boolean updatePassword(String password , String email);

    @Query("update User set profil=:profil where email=:email")
    public Boolean updateProfil(Profil profil,String email);
}
