package pro.sky.pitomnik.helpers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;

import pro.sky.pitomnik.AbstractClass.SubItemsOfBaseItems;
import pro.sky.pitomnik.Model.Picture;
import pro.sky.pitomnik.Repository.NewUserConsultationRepository;
import pro.sky.pitomnik.Repository.PictureRepository;

@Component
public class SubItemsOfThreeItems extends SubItemsOfBaseItems {

    private Logger logger = LoggerFactory.getLogger(SubItemsOfThreeItems.class);

    //@Autowired
    private TelegramBot telegramBot;
   // @Autowired
    private PictureRepository pictureRepository;

    Boolean isUploadPhoto = false;

    public SubItemsOfThreeItems(TelegramBot telegramBot, 
    PictureRepository pictureRepository) {
        this.telegramBot = telegramBot;
        this.pictureRepository = pictureRepository;
    }     

    @Override
    public void subOfOneBase(Update update) {
        String response;
        SendMessage message = new SendMessage("", "");
        Long chatId = update.message().chat().id();
        if(update.message().photo()[0].fileSize() > 1) {
            isUploadPhoto = true;
            response = "upload photo";
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
            uploadPhoto(update);
        }
    }

    public void uploadPhoto(Update update) {
        String response;
        SendMessage message = new SendMessage("", "");
        Long chatId = update.message().chat().id();
        
            GetFile getFileRequest = new GetFile(update.message().photo()[0].fileId());
            GetFileResponse getFileResponse = telegramBot.execute(getFileRequest);
            logger.info(getFileRequest + " getFileRequest", getFileResponse + " getFileResponse");    
            // try {
            //     File file = getFileResponse.file();
            //     byte[] fileContent = telegramBot.getFileContent(file);
            //     Picture picture = new Picture(fileContent);
            //     pictureRepository.save(picture);
    
            //     } catch (IOException e) {
            //         System.out.println(e);
            //     }

            //     response = "фото отправлено";
            //     message = new SendMessage(chatId, response);
            //     telegramBot.execute(message);
    }
}
