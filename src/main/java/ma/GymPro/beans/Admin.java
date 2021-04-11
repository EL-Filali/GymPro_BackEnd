/***********************************************************************
 * Module:  Admin.java
 * Author:  DELL
 * Purpose: Defines the Class Admin
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.*;
@Entity
public class Admin extends User {


    @PrePersist
    private void onPresiste(){

        this.dateCreation=new Date();

        super.role="Admin";
    }


}
