package pro.sky.pitomnik.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.NewUserConsultationModel;

@Repository
public interface NewUserConsultationRepository extends JpaRepository<NewUserConsultationModel, Long> {
    @Query(value = "SELECT about_pitomnik FROM new_user_consultation", nativeQuery = true)
    String getAboutPitomnik();
    @Query(value = "SELECT place_shelter FROM new_user_consultation", nativeQuery = true)
    String getPlaceShelter();
    @Query(value = "SELECT working_hours FROM new_user_consultation", nativeQuery = true)
    String getworkingHours();
    @Query(value = "SELECT regulations_pass FROM new_user_consultation", nativeQuery = true)
    String getRegulationsPass();
    @Query(value = "SELECT regulations_being_inside FROM new_user_consultation", nativeQuery = true)
    String getRegulationsBeingInside();
    @Query(value = "SELECT regulations_communication_with_dogs FROM new_user_consultation", nativeQuery = true)
    String getRegulationsCommunicationWithDogs();
    
    
}
