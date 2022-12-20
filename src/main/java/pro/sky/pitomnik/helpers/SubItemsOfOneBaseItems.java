package pro.sky.pitomnik.helpers;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.AbstractClass.SubItemsOfBaseItems;
import pro.sky.pitomnik.Repository.NewUserConsultationRepository;

@Component
public class SubItemsOfOneBaseItems extends SubItemsOfBaseItems  {

    private TelegramBot telegramBot;
    private NewUserConsultationRepository newUserConsultationRepository;


    public SubItemsOfOneBaseItems(TelegramBot telegramBot, NewUserConsultationRepository newUserConsultationRepository) {
        this.telegramBot = telegramBot;
        this.newUserConsultationRepository = newUserConsultationRepository;
    }        

    @Override
    public void subOfOneBase(Update update) {
        String response;
        SendMessage message = new SendMessage("", "");
        Long chatId = update.message().chat().id();
        if(update.message().text().contains("1sub") ) {
            response = newUserConsultationRepository.getAboutPitomnik();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } 
        else if(update.message().text().contains("2sub")) {
            response = "Расположение: " + newUserConsultationRepository.getPlaceShelter() + "\n" + newUserConsultationRepository.getworkingHours();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } 
        else if(update.message().text().contains("3sub")) {
            response = "Правила пропуска: " + newUserConsultationRepository.getRegulationsPass() +
            "\n" + "Правила нахождения на территории: " + newUserConsultationRepository.getRegulationsBeingInside() + 
            "\n" + "Правила обращения с животными: " + newUserConsultationRepository.getRegulationsCommunicationWithDogs();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        }
    }


   
           
        
}
