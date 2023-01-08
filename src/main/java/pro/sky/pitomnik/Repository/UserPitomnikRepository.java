package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.UserPitomnik;

@Repository
public interface UserPitomnikRepository extends JpaRepository<UserPitomnik, Long> {
    
}
