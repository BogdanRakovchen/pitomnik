package pro.sky.pitomnik.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ImageAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private byte[] imageAnimal;
    private String type;

    public ImageAnimal(Long chatId, byte[] imageAnimal, String type) {
        this.chatId = chatId;
        this.imageAnimal = imageAnimal;
        this.type = type;
    }
}
