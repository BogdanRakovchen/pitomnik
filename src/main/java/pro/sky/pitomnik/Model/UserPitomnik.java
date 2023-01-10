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
    private long id;
    private long chatId;
    /**
     * количество пропусков по сдаче отчетов от пользователя
    */
    private long countPasses = 0L;
    /**
     * окончание периода испытания пользователем
    */
    private Boolean passedTest = false;

    public UserPitomnik(Long chatId, Long countPasses, Boolean passedTest) {
        this.chatId = chatId;
        this.countPasses = countPasses;
        this.passedTest = passedTest;
    }

    public UserPitomnik() {

    }

    public void setCountPasses(Long count) {
        this.countPasses += count;
    }
}
