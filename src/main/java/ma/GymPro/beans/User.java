package ma.GymPro.beans;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {
   @Id
   @GeneratedValue
   protected Long id;
   protected String email;
   @JsonIgnore
   protected  Boolean isBanned;
   protected String password;
   @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
   protected Profil profil;
   protected String role;
   @JsonIgnore
   protected Date dateCreation;



   @OneToMany
   protected List<Conversation> conversations;


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(this.role));
      return authorities;
   }




   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }



   public User(Long id, String email, Boolean isBanned, String password,
               Profil profil, String role, Date dateCreation,  List<Conversation> conversations) {
      this.id = id;
      this.email = email;
      this.isBanned = isBanned;
      this.password = password;
      this.profil = profil;
      this.role = role;
      this.dateCreation = dateCreation;
      this.conversations = conversations;
   }

   public User() {

   }

   public User(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation) {
      this.id = id;
      this.email = email;
      this.isBanned = isBanned;
      this.password = password;
      this.profil = profil;
      this.role = role;
      this.dateCreation = dateCreation;
   }
}
