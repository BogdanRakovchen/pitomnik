package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.UserPitomnik;

@Repository
public interface UserPitomnikRepository extends JpaRepository<UserPitomnik, Integer> {
    
    // @Query(value = "SELECT select_dog OR select_cat FROM user_pitomnik WHERE chat_id = ?1 AND user_pitomnik.select_dog = true OR user_pitomnik.select_cat = true ORDER BY user_pitomnik.select_dog DESC LIMIT 1", nativeQuery = true)
    // Boolean getValueSelectAnumalOfUser(Long chatId);
   
}
