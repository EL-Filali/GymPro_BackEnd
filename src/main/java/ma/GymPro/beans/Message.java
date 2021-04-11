package ma.GymPro.beans; /***********************************************************************
 * Module:  Message.java
 * Author:  DELL
 * Purpose: Defines the Class Message
 ***********************************************************************/

import ma.GymPro.beans.Conversation;
import ma.GymPro.beans.Profil;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.*;
@Entity
public class Message {
   @Id
   @GeneratedValue
   private int id;
   private String message;
   private Date dateEnvoi;
   @ManyToOne
   private Profil expediteur;
   @ManyToOne
   public Conversation conversation;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Date getDateEnvoi() {
      return dateEnvoi;
   }

   public void setDateEnvoi(Date dateEnvoi) {
      this.dateEnvoi = dateEnvoi;
   }

   public Profil getExpediteur() {
      return expediteur;
   }

   public void setExpediteur(Profil expediteur) {
      this.expediteur = expediteur;
   }

   public Conversation getConversation() {
      return conversation;
   }

   public void setConversation(Conversation conversation) {
      this.conversation = conversation;
   }
}
