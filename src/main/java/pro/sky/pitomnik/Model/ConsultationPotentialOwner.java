package pro.sky.pitomnik.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "consultation_potention_owner")
@Data
@AllArgsConstructor
public class ConsultationPotentialOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dogDatingRules;
    private String listOfDocuments;
    private String listOfRecommendationsForTransportingAnAnimal;
    @Column(name = "list_of_recommendations_for_home_improvement_for_puppy")
    private String listOfRecommendationsForHomeImprovementForAPuppy;
    private String listOfRecommendationsForHomeImprovementForAnAdultDog;
    private String homeImprovementListForDogsWithDisabilities;
    @Column(name = "cynologist_advice_on_initial_communication_with_a_dog")
    private String cynologistAdviceOnInitialCommunicationWithADog;
    private String recommendationsForProvenCynologists;
    private String listOfReasonsRefuse;

}
