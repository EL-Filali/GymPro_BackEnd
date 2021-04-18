package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.*;
@Entity
public class Admin extends User {


    @PrePersist
    private void onPresiste(){

        this.dateCreation=new Date();
        this.isBanned=false;
        super.role="admin";
    }

    public Admin(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation) {
        super(id, email, isBanned, password, profil, role, dateCreation);
    }

    public Admin() {
    }
}
