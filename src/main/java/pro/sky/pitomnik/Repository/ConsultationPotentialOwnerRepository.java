package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.ConsultationPotentialOwner;

@Repository
public interface ConsultationPotentialOwnerRepository extends JpaRepository<ConsultationPotentialOwner, Long> {
    @Query(value = "SELECT dog_dating_rules FROM consultation_potention_owner", nativeQuery = true)
    String getDogDatingRules();
    @Query(value = "SELECT list_of_documents FROM consultation_potention_owner", nativeQuery = true)
    String getListOfDocuments();
    @Query(value = "SELECT list_of_recommendations_for_transporting_an_animal FROM consultation_potention_owner", nativeQuery = true)
    String getListOfRecommendationsForTransportingAnAnimal();
    @Query(value = "SELECT list_of_recommendations_for_home_improvement_for_puppy FROM nconsultation_potention_owner", nativeQuery = true)
    String getListOfRecommendationsForHomeImprovementForAPuppy();
    @Query(value = "SELECT list_of_recommendations_for_home_improvement_for_an_adult_dog FROM consultation_potention_owner", nativeQuery = true)
    String getListOfRecommendationsForHomeImprovementForAnAdultDog();
    @Query(value = "SELECT home_improvement_list_for_dogs_with_disabilities_dogs FROM consultation_potention_owner", nativeQuery = true)
    String getHomeImprovementListForDogsWithDisabilities();
    @Query(value = "SELECT cynologist_advice_on_initial_communication_with_a_dog FROM consultation_potention_owner", nativeQuery = true)
    String getCynologistAdviceOnInitialCommunicationWithADog();
    @Query(value = "SELECT recommendations_for_proven_cynologists FROM consultation_potention_owner", nativeQuery = true)
    String getRecommendationsForProvenCynologists();
    @Query(value = "SELECT list_of_reasons_refuse FROM consultation_potention_owner", nativeQuery = true)
    String getListOfReasonsRefuse();
}
