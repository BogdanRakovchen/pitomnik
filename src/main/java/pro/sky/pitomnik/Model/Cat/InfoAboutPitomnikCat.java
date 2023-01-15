package pro.sky.pitomnik.Model.Cat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pro.sky.pitomnik.Abstract.InfoAboutPitomnik;

@Entity
public class InfoAboutPitomnikCat extends InfoAboutPitomnik {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
