package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.ImageAnimal;

@Repository
public interface ImageAnimalRepository extends JpaRepository<ImageAnimal, Long> {
    
}
