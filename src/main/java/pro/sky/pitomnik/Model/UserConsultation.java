package pro.sky.pitomnik.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserConsultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dogDatingRules;
    private String listOfDocuments;
    private String shippingRecommendationList;
    private String homeImprovementListForPuppy;
    private String homeImprovementListForAdultDog;
    private String homeImprovementListForDogWithDisabilities;
    private String cynologistAdvice;
    private String recommendationsProvenCynologists;
    private String listReasonsOfRefusal;
    
}
