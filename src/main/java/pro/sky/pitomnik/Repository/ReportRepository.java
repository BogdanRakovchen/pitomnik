package pro.sky.pitomnik.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.sky.pitomnik.Model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{


    @Query(value = "SELECT date FROM report LIMIT 1", nativeQuery = true)
    LocalDate getPrimaryDate();
    
}
