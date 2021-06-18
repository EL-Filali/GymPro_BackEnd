package ma.GymPro.repositories;

import ma.GymPro.beans.Profil;
import ma.GymPro.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String Email);

    @Modifying
    @Query("update  User u set u.password=:password  where u.email=:email")
    public Integer updatePassword(String password , String email);

    @Modifying
    @Query("update User  u set u.profil=:p where u.email=:e")
    public Integer updateProfil(@Param(value = "p")Profil p,@Param(value = "e") String e);

}
