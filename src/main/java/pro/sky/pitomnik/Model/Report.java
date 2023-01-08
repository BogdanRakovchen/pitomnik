package pro.sky.pitomnik.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private byte[] reportAnimal;
    private String type; 
    private LocalDate date;

    public Report(Long chatId, byte[] reportAnimal, String type, LocalDate date) {
        this.chatId = chatId;
        this.reportAnimal = reportAnimal;
        this.type = type;
        this.date = date;
    }

}
