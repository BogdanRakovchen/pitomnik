package pro.sky.pitomnik.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserOfPitomnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactsOfUser;

    public UserOfPitomnik(String contactsOfUser) {
        this.contactsOfUser = contactsOfUser;
    }
}
