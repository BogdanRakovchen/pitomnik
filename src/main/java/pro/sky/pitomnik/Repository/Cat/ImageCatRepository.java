package pro.sky.pitomnik.Repository.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Cat.ImageCat;

@Repository
public interface ImageCatRepository extends JpaRepository<ImageCat, Long> {
    
}
