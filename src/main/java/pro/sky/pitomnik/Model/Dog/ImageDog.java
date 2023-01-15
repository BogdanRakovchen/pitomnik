package pro.sky.pitomnik.Model.Dog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import pro.sky.pitomnik.Abstract.ImageAnimal;

@Entity
@Table(name = "image_animal")
public class ImageDog extends ImageAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ImageDog(Long chatId, byte[] imageAnimal, String type) {
       super(chatId, imageAnimal, type);
    }
}
