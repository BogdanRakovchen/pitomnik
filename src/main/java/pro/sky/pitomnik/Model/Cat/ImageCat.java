package pro.sky.pitomnik.Model.Cat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pro.sky.pitomnik.Abstract.ImageAnimal;

@Entity
public class ImageCat extends ImageAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public ImageCat(Long chatId, byte[] imageAnimal, String type) {
        super(chatId, imageAnimal, type);
     }
}
