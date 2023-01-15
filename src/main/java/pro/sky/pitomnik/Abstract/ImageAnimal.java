package pro.sky.pitomnik.Abstract;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
abstract public class ImageAnimal {

    private Long chatId;
    /**
     * изображение животного от пользователя
    */
    
    private byte[] imageAnimal;
    private String type;
    
    public ImageAnimal(Long chatId, byte[] imageAnimal, String type) {
       this.chatId = chatId;
       this.imageAnimal = imageAnimal;
       this.type = type;
     }
}
