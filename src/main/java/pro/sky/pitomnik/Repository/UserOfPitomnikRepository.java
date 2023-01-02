package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.UserOfPitomnik;

@Repository
public interface UserOfPitomnikRepository extends JpaRepository<UserOfPitomnik, Long> {
        
}
