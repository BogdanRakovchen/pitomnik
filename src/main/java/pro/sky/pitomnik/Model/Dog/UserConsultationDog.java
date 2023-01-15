package pro.sky.pitomnik.Model.Dog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pro.sky.pitomnik.Abstract.UserConsultation;

@Entity
@Table(name = "user_consultation")
public class UserConsultationDog extends UserConsultation {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
}
