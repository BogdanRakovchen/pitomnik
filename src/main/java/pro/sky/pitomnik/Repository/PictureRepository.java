package pro.sky.pitomnik.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    
}
