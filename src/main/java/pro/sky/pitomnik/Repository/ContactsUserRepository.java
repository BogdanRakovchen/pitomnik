package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.ContactsUser;

@Repository
public interface ContactsUserRepository extends JpaRepository<ContactsUser, Long> {
   
}
