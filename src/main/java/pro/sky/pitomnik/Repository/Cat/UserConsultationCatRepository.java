package pro.sky.pitomnik.Repository.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Cat.UserConsultationCat;

@Repository
public interface UserConsultationCatRepository extends JpaRepository<UserConsultationCat, Long>{

    @Query(value = "SELECT dog_dating_rules FROM user_consultation_cat", nativeQuery = true)
    String getCatDatingRules();

    @Query(value = "SELECT list_of_documents FROM user_consultation_cat", nativeQuery = true)
    String getListOfDocumentsCat();

    @Query(value = "SELECT shipping_recommendation_list FROM user_consultation_cat", nativeQuery = true)
    String getShippingRecommendationListCat();

    @Query(value = "SELECT home_improvement_list_for_puppy FROM user_consultation_cat", nativeQuery = true)
    String getHomeImprovementListForCat();

    @Query(value = "SELECT home_improvement_list_for_adult_dog FROM user_consultation_cat", nativeQuery = true)
    String getHomeImprovementListForAdultCat();

    @Query(value = "SELECT home_improvement_list_for_dog_with_disabilities FROM user_consultation_cat", nativeQuery = true)
    String getHomeImprovementListForCatWithDisabilities();

    @Query(value = "SELECT cynologist_advice FROM user_consultation_cat", nativeQuery = true)
    String getCynologistAdviceCat();

    @Query(value = "SELECT recommendations_proven_cynologists FROM user_consultation_cat", nativeQuery = true)
    String getRecommendationsProvenCynologistsCat();

    @Query(value = "SELECT list_reasons_of_refusal FROM user_consultation_cat", nativeQuery = true)
    String getListReasonsOfRefusalCat();
}
