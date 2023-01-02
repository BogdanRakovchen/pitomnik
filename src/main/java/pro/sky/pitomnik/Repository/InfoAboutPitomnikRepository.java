package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.InfoAboutPitomnik;

@Repository
public interface InfoAboutPitomnikRepository extends JpaRepository<InfoAboutPitomnik, Long> {
    
    @Query(value =  "SELECT info.about_pitomnik FROM info_about_pitomnik AS info", nativeQuery = true)
    String getAboutPitomnik();

    @Query(value =  "SELECT location_pitomnik FROM info_about_pitomnik", nativeQuery = true)
    String getLocationPitomnik();

    @Query(value =  "SELECT shedule_pitomnik FROM info_about_pitomnik", nativeQuery = true)
    String getShedulePitomnik();

    @Query(value =  "SELECT prevention_of_accidents FROM info_about_pitomnik", nativeQuery = true)
    String getPreventionOfAccidents();

}
