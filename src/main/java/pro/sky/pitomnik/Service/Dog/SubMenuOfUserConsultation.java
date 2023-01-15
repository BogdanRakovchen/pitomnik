package pro.sky.pitomnik.Service.Dog;

import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Abstract.SubMenu;
import pro.sky.pitomnik.Constants.Menu;
import pro.sky.pitomnik.Model.ContactsUser;
import pro.sky.pitomnik.Repository.ContactsUserRepository;
import pro.sky.pitomnik.Repository.Dog.UserConsultationDogRepository;

@Service
public class SubMenuOfUserConsultation extends SubMenu {

    private final UserConsultationDogRepository userConsultationRepository;
    private Boolean isItemFourSubWasCheck = false;

    public SubMenuOfUserConsultation(
        TelegramBot telegramBot,
        ContactsUserRepository contactsUserRepository,
        UserConsultationDogRepository userConsultationRepository
        ) {
        super(telegramBot, contactsUserRepository);    
        this.userConsultationRepository = userConsultationRepository;
    }
    
    /**
     * метод - обрабатывает запросы пользователя 
     * @param update
    */
    public void subMenuOfUserConsultation(Update update) {
        if(update.message().text().equals(Menu.ONE_SUB)) {
            String result = "правила знакомства: " + userConsultationRepository.getDogDatingRules();
            sendMessageToTelegramBot(update, result);
        } else if(update.message().text().equals(Menu.TWO_SUB)) {
            String result = "список документов: " + userConsultationRepository.getListOfDocuments();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.THREE_SUB)) {
            String result = "список рекомендаций: " + userConsultationRepository.getShippingRecommendationList();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.FOUR_SUB)) {
            String result = "список рекомендаций: " + userConsultationRepository.getHomeImprovementListForPuppy();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.FIVE_SUB)) {
            String result = "список рекомендаций: " + userConsultationRepository.getHomeImprovementListForAdultDog();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.SIX_SUB)) {
            String result = "список рекомендаций: " + userConsultationRepository.getHomeImprovementListForDogWithDisabilities();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.SEVEN_SUB)) {
            String result = "советы кинолога: " + userConsultationRepository.getCynologistAdvice();
            sendMessageToTelegramBot(update, result);   
        }  else if(update.message().text().equals(Menu.EIGHT_SUB)) {
            String result = "рекомендации по проверенным кинологам: " + userConsultationRepository.getRecommendationsProvenCynologists();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.NINE_SUB)) {
            String result = "список причин для отказа: " + userConsultationRepository.getListReasonsOfRefusal();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.TEN_SUB)) {
            String result = "Введите номер телефона, в формате 89991112325";
            sendMessageToTelegramBot(update, result);
            isItemFourSubWasCheck = true;
        } else if(isItemFourSubWasCheck) {
           Boolean resultOfMatch = PATTERN_OF_NUMBER_TELEPHONE.matcher(update.message().text()).matches();

                if(resultOfMatch && update.message().text().trim().length() == 11) {
                    contactsUserRepository.save(new ContactsUser(update.message().text().trim()));
                        logger.info("write phonenumber");
                        String result = "Спасибо, ваш номер телефона записан";
                        sendMessageToTelegramBot(update, result);
                        isItemFourSubWasCheck = false;
                    } else {
                        sendMessageToTelegramBot(update, "Пожалуйста введите корректный одиннадцатизначный номер");   
                    }
                

        } else if(update.message().text().equals(Menu.ELEVEN_SUB)) {
            String result = "Волонтер скоро вам ответит";
            // здесь якобы данные чата волонетров
            long chatId = update.message().chat().id();
            telegramBot.execute(new SendMessage(chatId, result));
        }
   }
}
