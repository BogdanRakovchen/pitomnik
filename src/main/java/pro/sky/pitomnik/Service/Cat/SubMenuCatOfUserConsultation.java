package pro.sky.pitomnik.Service.Cat;

import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Abstract.SubMenu;
import pro.sky.pitomnik.Constants.Menu;
import pro.sky.pitomnik.Model.ContactsUser;
import pro.sky.pitomnik.Repository.ContactsUserRepository;
import pro.sky.pitomnik.Repository.Cat.UserConsultationCatRepository;
import pro.sky.pitomnik.Repository.Dog.UserConsultationDogRepository;

@Service
public class SubMenuCatOfUserConsultation extends SubMenu {

    private final UserConsultationCatRepository userConsultationCatRepository;
    private Boolean isItemFourSubWasCheck = false;

    public SubMenuCatOfUserConsultation(
        TelegramBot telegramBot,
        ContactsUserRepository contactsUserRepository,
        UserConsultationCatRepository userConsultationCatRepository
        ) {
        super(telegramBot, contactsUserRepository);    
        this.userConsultationCatRepository = userConsultationCatRepository;
    }
    
    /**
     * метод - обрабатывает запросы пользователя 
     * @param update
    */
    public void subMenuCatOfUserConsultation(Update update) {
        if(update.message().text().equals(Menu.ONE_SUB)) {
            String result = "правила знакомства: " + userConsultationCatRepository.getCatDatingRules();
            sendMessageToTelegramBot(update, result);
        } else if(update.message().text().equals(Menu.TWO_SUB)) {
            String result = "список документов: " + userConsultationCatRepository.getListOfDocumentsCat();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.THREE_SUB)) {
            String result = "список рекомендаций: " + userConsultationCatRepository.getShippingRecommendationListCat();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.FOUR_SUB)) {
            String result = "список рекомендаций: " + userConsultationCatRepository.getHomeImprovementListForCat();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.FIVE_SUB)) {
            String result = "список рекомендаций: " + userConsultationCatRepository.getHomeImprovementListForAdultCat();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.SIX_SUB)) {
            String result = "список рекомендаций: " + userConsultationCatRepository.getHomeImprovementListForCatWithDisabilities();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.SEVEN_SUB)) {
            String result = "советы кинолога: " + userConsultationCatRepository.getCynologistAdviceCat();
            sendMessageToTelegramBot(update, result);   
        }  else if(update.message().text().equals(Menu.EIGHT_SUB)) {
            String result = "рекомендации по проверенным кинологам: " + userConsultationCatRepository.getRecommendationsProvenCynologistsCat();
            sendMessageToTelegramBot(update, result);   
        } else if(update.message().text().equals(Menu.NINE_SUB)) {
            String result = "список причин для отказа: " + userConsultationCatRepository.getListReasonsOfRefusalCat();
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
