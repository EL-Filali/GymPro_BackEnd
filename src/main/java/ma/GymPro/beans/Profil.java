/***********************************************************************
 * Module:  Profil.java
 * Author:  DELL
 * Purpose: Defines the Class Profil
 ***********************************************************************/
package ma.GymPro.beans;
import ma.GymPro.beans.Conversation;
import ma.GymPro.beans.NfcTag;

import javax.persistence.*;
import java.util.*;
@Entity

public  class Profil {
   @Id
   @GeneratedValue
   private Long id;
   private String nom;
   private String prenom;
   private String cin;
   private String  imgFileName;
   private Date dateNaissance;
   private String genre;
   private String telephone;

   public Profil(Long id, String nom, String prenom, String cin, Date dateNaissance, String genre, String telephone) {
      this.id = id;
      this.nom = nom;
      this.prenom = prenom;
      this.cin = cin;
      this.dateNaissance = dateNaissance;
      this.genre = genre;
      this.telephone = telephone;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public String getPrenom() {
      return prenom;
   }

   public void setPrenom(String prenom) {
      this.prenom = prenom;
   }

   public String getCin() {
      return cin;
   }

   public void setCin(String cin) {
      this.cin = cin;
   }

   public Date getDateNaissance() {
      return dateNaissance;
   }

   public void setDateNaissance(Date dateNaissance) {
      this.dateNaissance = dateNaissance;
   }

   public String getGenre() {
      return genre;
   }

   public void setGenre(String genre) {
      this.genre = genre;
   }

   public String getTelephone() {
      return telephone;
   }

   public void setTelephone(String telephone) {
      this.telephone = telephone;
   }

   public String getImgFileName() {
      return imgFileName;
   }

   public void setImgFileName(String imgFileName) {
      this.imgFileName = imgFileName;
   }
}
