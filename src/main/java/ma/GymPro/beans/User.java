package ma.GymPro.beans; /***********************************************************************
 * Module:  User.java
 * Author:  DELL
 * Purpose: Defines the Class User
 ***********************************************************************/

import ma.GymPro.beans.Profil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.*;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {
   @Id
   @GeneratedValue
   protected Long id;
   protected String email;
   protected  Boolean isBanned=false;
   protected String password;
   @OneToOne
   protected Profil profil;
   protected String role;
   protected Date dateCreation;
   @OneToOne
   protected NfcTag tag;
   @OneToMany
   protected List<Conversation> conversations;
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }


   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return null;
   }

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }

   @Override
   public boolean isAccountNonLocked() {
      return false;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }

   @Override
   public boolean isEnabled() {
      return isBanned;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Profil getProfil() {
      return profil;
   }

   public void setProfil(Profil profil) {
      this.profil = profil;
   }

   public Boolean getBanned() {
      return isBanned;
   }

   public void setBanned(Boolean banned) {
      isBanned = banned;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }
   public NfcTag getTag() {
      return tag;
   }

   public void setTag(NfcTag tag) {
      this.tag = tag;
   }
   public List<Conversation> getConversations() {
      return conversations;
   }

   public void setConversations(List<Conversation> conversations) {
      this.conversations = conversations;
   }
   public Date getDateCreation() {
      return dateCreation;
   }

   public void setDateCreation(Date dateCreation) {
      this.dateCreation = dateCreation;
   }

   public User(Long id, String email, Boolean isBanned, String password, Profil profil, String role, Date dateCreation, NfcTag tag, List<Conversation> conversations) {
      this.id = id;
      this.email = email;
      this.isBanned = isBanned;
      this.password = password;
      this.profil = profil;
      this.role = role;
      this.dateCreation = dateCreation;
      this.tag = tag;
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
