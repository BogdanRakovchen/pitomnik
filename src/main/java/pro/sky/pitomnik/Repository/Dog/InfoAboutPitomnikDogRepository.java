package pro.sky.pitomnik.Repository.Dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Dog.InfoAboutPitomnikDog;

@Repository
public interface InfoAboutPitomnikDogRepository extends JpaRepository<InfoAboutPitomnikDog, Long> {
    
    @Query(value =  "SELECT about_pitomnik FROM info_about_pitomnik LIMIT 1", nativeQuery = true)
    String getAboutPitomnik();

    @Query(value =  "SELECT location_pitomnik FROM info_about_pitomnik LIMIT 1", nativeQuery = true)
    String getLocationPitomnik();

    @Query(value =  "SELECT shedule_pitomnik FROM info_about_pitomnik LIMIT 1", nativeQuery = true)
    String getShedulePitomnik();

    @Query(value =  "SELECT prevention_of_accidents FROM info_about_pitomnik LIMIT 1", nativeQuery = true)
    String getPreventionOfAccidents();

    @Query(value =  "SELECT security_contact FROM info_about_pitomnik LIMIT 1", nativeQuery = true)
    String getSecurityContact();

}
