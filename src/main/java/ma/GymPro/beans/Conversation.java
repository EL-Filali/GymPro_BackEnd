/***********************************************************************
 * Module:  Conversation.java
 * Author:  DELL
 * Purpose: Defines the Class Conversation
 ***********************************************************************/
package ma.GymPro.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;
import ma.GymPro.beans.Message;
@Entity
public class Conversation {
   @Id
   @GeneratedValue
   private int id;
   private Date date;
   @OneToMany
   private List<Message> messages;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public List<Message> getMessages() {
      return messages;
   }

   public void setMessages(List<Message> messages) {
      this.messages = messages;
   }
}
