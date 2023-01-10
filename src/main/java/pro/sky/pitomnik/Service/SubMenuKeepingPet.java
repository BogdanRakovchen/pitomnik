package pro.sky.pitomnik.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;

import pro.sky.pitomnik.Model.ImageAnimal;
import pro.sky.pitomnik.Model.Report;
import pro.sky.pitomnik.Repository.ImageAnimalRepository;
import pro.sky.pitomnik.Repository.ReportRepository;

@Service
public class SubMenuKeepingPet {

   private final Logger logger = LoggerFactory.getLogger(SubMenuKeepingPet.class);

    private final TelegramBot telegramBot;
    private final ReportRepository reportRepository;
    private final ImageAnimalRepository imageAnimalRepository;

    public SubMenuKeepingPet(
        TelegramBot telegramBot, 
        ReportRepository reportRepository,
        ImageAnimalRepository imageAnimalRepository) {
        this.telegramBot = telegramBot;
        this.reportRepository = reportRepository;
        this.imageAnimalRepository = imageAnimalRepository;
    }

    /**
     * общий метод, который высчитывает id пользователя и отправляет 
     * ему сообщения 
    */
    private void sendMessageToTelegramBot(Update update, String result ) {
        long chatId = update.message().chat().id();
        telegramBot.execute(new SendMessage(chatId, result)); 
    }

    /**
     * метод - загружает с компьютера файл (отчет)
     * и отправляет его пользователю чата для скачивания 
     * @param update
     * @throws IOException
    */
    private void loadReportForUser(Update update) throws IOException {
    
        File file = new File("src/main/resources/static/report", "report.odt");
        try(InputStream in = new FileInputStream(file.getPath())) {
            byte[] buffer = new byte[in.available()];
            in.read(buffer, 0, buffer.length);
            long chatId = update.message().chat().id();
            telegramBot.execute(new SendDocument(chatId, buffer).caption("отчет")
            .fileName("report-file.odt")); 

         }  catch(IOException ex){      
            System.out.println(ex.getMessage());
        } 
    }

    /**
     * метод - обрабатывает запросы пользователя и выдает 
     * соответствующий результат:<br>
     * 1. скачать файл (отчет) для заполнения<br>
     * 2. загрузить заполненный отчет и фотографию животного<br>
     * 3. позвать волонтера на помощь<br> 
     * @param update
     * @throws IOException
    */
    public void subMenuOfKeepingPet(Update update) throws IOException {
         if(update.message().text() != null && update.message().text().equals("/1sub")) {
            loadReportForUser(update);
        } else if(update.message().text() != null && update.message().text().equals("/2sub")) {         
            sendMessageToTelegramBot(update, "загрузите файл и изображение");
        } else if(update.message().document() != null && update.message().document().equals(update.message().document())) {
            long chatId = update.message().chat().id();
            GetFile getFileRequest = new GetFile(update.message().document().fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);

            try {    
               byte[] fileContent = telegramBot.getFileContent(getFileResponse.file());
               reportRepository.save(new Report(chatId, fileContent, getFileRequest.getContentType(), LocalDate.now()));
                } catch (IOException e) {
                 logger.info(e + "");
            }
            
            SendMessage sendMessage = new SendMessage(chatId, "файл загружен");
            telegramBot.execute(sendMessage);
        } else if(update.message().photo() != null && update.message().photo().equals(update.message().photo())) {
            long chatId = update.message().chat().id();
            GetFile getFileRequest = new GetFile(update.message().photo()[0].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            try {    
                byte[] fileContent = telegramBot.getFileContent(getFileResponse.file());
                imageAnimalRepository.save(new ImageAnimal(chatId, fileContent, getFileRequest.getContentType()));
                } catch (IOException e) {
                 logger.info(e + "");
            }
           
            SendMessage sendMessage = new SendMessage(chatId, "изображение загружено");
            telegramBot.execute(sendMessage);
        } 
        else if(update.message().text() != null && update.message().text().equals("/3sub")) {
            String result = "Волонтер скоро вам ответит";
            // здесь якобы данные чата волонетров
            long chatId = update.message().chat().id();
            telegramBot.execute(new SendMessage(chatId, result));
        }
    }   
}
