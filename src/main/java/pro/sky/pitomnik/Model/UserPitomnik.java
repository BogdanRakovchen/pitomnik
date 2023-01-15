package pro.sky.pitomnik.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserPitomnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long chatId;
    /**
     * количество пропусков по сдаче отчетов от пользователя
    */
    private long countPasses = 0L;
    /**
     * окончание периода испытания пользователем
    */
    private Boolean passedTest = false;
    /**
    * выбор приюта собак или кошек
    */
    private Boolean select_dog;
    private Boolean select_cat;
    // private Boolean selectCat;

    public UserPitomnik(
        Long chatId, 
        Long countPasses, 
        Boolean passedTest,
        Boolean select_dog,
        Boolean select_cat) {
        this.chatId = chatId;
        this.countPasses = countPasses;
        this.passedTest = passedTest;
        this.select_dog = select_dog;
        this.select_cat = select_cat;
    }

    public UserPitomnik() {

    }

    public UserPitomnik(
        Long chatId, 
        Long countPasses, 
        Boolean passedTest) {
        this.chatId = chatId;
        this.countPasses = countPasses;
        this.passedTest = passedTest;
    }
    

    public void setCountPasses(Long count) {
        this.countPasses += count;
    }

    // public void setId(Integer id) {
    //     this.id += 1;
    // }
}
