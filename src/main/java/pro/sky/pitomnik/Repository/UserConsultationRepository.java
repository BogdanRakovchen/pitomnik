package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.UserConsultation;

@Repository
public interface UserConsultationRepository extends JpaRepository<UserConsultation, Long>{
    
    @Query(value = "SELECT dog_dating_rules FROM user_consultation", nativeQuery = true)
    String getDogDatingRules();

    @Query(value = "SELECT list_of_documents FROM user_consultation", nativeQuery = true)
    String getListOfDocuments();

    @Query(value = "SELECT shipping_recommendation_list FROM user_consultation", nativeQuery = true)
    String getShippingRecommendationList();

    @Query(value = "SELECT home_improvement_list_for_puppy FROM user_consultation", nativeQuery = true)
    String getHomeImprovementListForPuppy();

    @Query(value = "SELECT home_improvement_list_for_adult_dog FROM user_consultation", nativeQuery = true)
    String getHomeImprovementListForAdultDog();

    @Query(value = "SELECT home_improvement_list_for_dog_with_disabilities FROM user_consultation", nativeQuery = true)
    String getHomeImprovementListForDogWithDisabilities();

    @Query(value = "SELECT cynologist_advice FROM user_consultation", nativeQuery = true)
    String getCynologistAdvice();

    @Query(value = "SELECT recommendations_proven_cynologists FROM user_consultation", nativeQuery = true)
    String getRecommendationsProvenCynologists();

    @Query(value = "SELECT list_reasons_of_refusal FROM user_consultation", nativeQuery = true)
    String getListReasonsOfRefusal();
}
