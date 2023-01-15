package pro.sky.pitomnik.Model.Dog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pro.sky.pitomnik.Abstract.InfoAboutPitomnik;

@Entity
@Table(name = "info_about_pitomnik")
public class InfoAboutPitomnikDog extends InfoAboutPitomnik {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
