package pro.sky.pitomnik.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long chatId;
    private String contacts;
    private String dietOfAnimal;
    private String wellBeing;
    private String changeBehavior;

    @OneToMany(mappedBy = "user")
    private Set<Picture> picture;


    public User(
        Long chatId, 
        String contacts, 
        String dietOfAnimal, 
        String wellBeing,
        String changeBehavior) {
            this.chatId = chatId;
            this.contacts = contacts;
            this.dietOfAnimal = dietOfAnimal;
            this.wellBeing = wellBeing;
            this.changeBehavior = changeBehavior;
    }

    public User() {
        
    }

}