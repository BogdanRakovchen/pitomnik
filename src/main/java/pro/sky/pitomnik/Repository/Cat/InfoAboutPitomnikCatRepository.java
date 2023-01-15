package pro.sky.pitomnik.Repository.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Cat.InfoAboutPitomnikCat;

@Repository
public interface InfoAboutPitomnikCatRepository extends JpaRepository<InfoAboutPitomnikCat, Long>{
    
    @Query(value =  "SELECT about_pitomnik FROM info_about_pitomnik_cat LIMIT 1", nativeQuery = true)
    String getAboutPitomnikCat();

    @Query(value =  "SELECT location_pitomnik FROM info_about_pitomnik_cat LIMIT 1", nativeQuery = true)
    String getLocationPitomnikCat();

    @Query(value =  "SELECT shedule_pitomnik FROM info_about_pitomnik_cat LIMIT 1", nativeQuery = true)
    String getShedulePitomnikCat();

    @Query(value =  "SELECT prevention_of_accidents FROM info_about_pitomnik_cat LIMIT 1", nativeQuery = true)
    String getPreventionOfAccidentsCat();

    @Query(value =  "SELECT security_contact FROM info_about_pitomnik_cat LIMIT 1", nativeQuery = true)
    String getSecurityContactCat();
}
