package pro.sky.pitomnik.Repository.Dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Dog.ImageDog;

@Repository
public interface ImageDogRepository extends JpaRepository<ImageDog, Long> {
    
}
