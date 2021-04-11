/***********************************************************************
 * Module:  Achat.java
 * Author:  DELL
 * Purpose: Defines the Class Achat
 ***********************************************************************/
package ma.GymPro.beans;
import ma.GymPro.beans.Client;

import javax.persistence.*;
import java.util.*;

@Entity
public class Achat {
   @Id
   @GeneratedValue
   private Long id;

   private boolean isPaid;
   @OneToOne
   private Facture facture;
   @ManyToOne
   private Client client;
   @OneToMany
   private List<Service> services;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public boolean isPaid() {
      return isPaid;
   }

   public void setPaid(boolean paid) {
      isPaid = paid;
   }

   public Facture getFacture() {
      return facture;
   }

   public void setFacture(Facture facture) {
      this.facture = facture;
   }

   public Client getClient() {
      return client;
   }

   public void setClient(Client client) {
      this.client = client;
   }

   public List<Service> getServices() {
      return services;
   }

   public void setServices(List<Service> services) {
      this.services = services;
   }
}
