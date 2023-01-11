package pro.sky.pitomnik.Service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pro.sky.pitomnik.Model.UserPitomnik;
import pro.sky.pitomnik.Repository.ReportRepository;

@Service
public class CheckReportOfUser {

    private final ReportRepository reportRepository;

    public CheckReportOfUser(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
        
    }

    private final Logger logger = LoggerFactory.getLogger(CheckReportOfUser.class);
    
    @Scheduled(fixedDelay = 86400)
    /**
     * метод - высчитывает начальную дату обращения пользователя в приют и конечную дату
     * окончания периода испытания 
     * @return метод ничего не возвращает     
    */
    public void checkReport() {
        // первый день, с которого начнается 30 дневнй срок отчета
        if(reportRepository.getPrimaryDate() != null) {
            LocalDate primaryDay = reportRepository.getPrimaryDate();
            // последюущие дни отчетов 
            LocalDate nextDate = reportRepository.getPrimaryDate();
            // плюсуем, 30 дней к первому дню отчета и получаем финальную дату
            LocalDate finishDate = primaryDay.plusDays(30L);
            
            for(int i=0; i < 30; i++) {
                primaryDay = primaryDay.plusDays(1);
            }

            if(nextDate != null && nextDate.equals(finishDate)) {
                // по достижении предельного времени испытание считается оконченным
                new UserPitomnik().setPassedTest(true);
                logger.info("испытательный срок закончен");
            } else if(nextDate != null & !nextDate.equals(primaryDay)) {
                // подсчитытваем сколько раз пользователь нарушил регламент по срокам сдачи отчета 
                new UserPitomnik().setCountPasses(1L);
                
                logger.info(new UserPitomnik().getCountPasses() + " количество пропусков");
            }
                logger.info(nextDate + " date" + ":" + finishDate + " days");
    }
}
}
