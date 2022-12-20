package pro.sky.pitomnik.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "new_user_consultation")
@Data
@AllArgsConstructor
public class NewUserConsultationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aboutPitomnik;
    private String placeShelter;
    private String workingHours;
    private String regulationsPass;
    private String regulationsBeingInside;
    private String regulationsCommunicationWithDogs;

}
