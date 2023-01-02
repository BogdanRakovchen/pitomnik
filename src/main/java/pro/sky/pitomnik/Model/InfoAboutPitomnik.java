package pro.sky.pitomnik.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class InfoAboutPitomnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String aboutPitomnik;
    private String locationPitomnik; 
    private String shedulePitomnik;
    private String preventionOfAccidents;

}
