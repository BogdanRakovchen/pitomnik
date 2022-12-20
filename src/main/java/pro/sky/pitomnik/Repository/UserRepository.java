package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
}
