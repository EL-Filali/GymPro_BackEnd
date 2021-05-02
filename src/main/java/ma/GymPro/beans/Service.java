/***********************************************************************
 * Module:  Service.java
 * Author:  DELL
 * Purpose: Defines the Class Service
 ***********************************************************************/
package ma.GymPro.beans;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.*;

@Entity @Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@JsonSubTypes({
                @JsonSubTypes.Type(value = Abonnement.class, name = "Abonnement"),
        })
@JsonDeserialize(as = Abonnement.class)
public abstract class Service {
   @Id
   @GeneratedValue
   protected Long id;

   protected float prix;

   protected String description;

   public Service(Long id, float prix, String description) {
      this.id = id;
      this.prix = prix;
      this.description = description;
   }

   public Service() {
   }
}
