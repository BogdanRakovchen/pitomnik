package pro.sky.pitomnik.Service.Dog;

import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Abstract.SubMenu;
import pro.sky.pitomnik.Constants.Menu;
import pro.sky.pitomnik.Model.ContactsUser;
import pro.sky.pitomnik.Repository.ContactsUserRepository;
import pro.sky.pitomnik.Repository.UserPitomnikRepository;
import pro.sky.pitomnik.Repository.Cat.InfoAboutPitomnikCatRepository;
import pro.sky.pitomnik.Repository.Dog.InfoAboutPitomnikDogRepository;

@Service
public class SubMenuOfAboutPitomnik extends SubMenu {

    private final InfoAboutPitomnikDogRepository infoAboutPitomnikRepository;
    private boolean isItemFourSubWasCheck = false;

    public SubMenuOfAboutPitomnik(
        TelegramBot telegramBot, 
        ContactsUserRepository contactsUserRepository,
        InfoAboutPitomnikDogRepository infoAboutPitomnikRepository
        ) {
        super(telegramBot,contactsUserRepository);
        this.infoAboutPitomnikRepository = infoAboutPitomnikRepository;
    }

    /**
     * метод - обрабатывает запросы пользователя
     * и выдает соответствующий результат:<br>
     * 1. информация о питомнике<br>
     * 2. адрес и расписание<br>
     * 3. правила безопасности<br> 
     * 4. запись номера телефона<br>
     * 5. номер телефона охраны питомника<br>
     * 6. позвать волонтера<br>
     * @param update
    */
    public void subMenuOfAboutPitomnik(Update update) {
            if(update.message().text().equals("/1sub")) {
                
                String result = "Инфа о питомнике: " + infoAboutPitomnikRepository.getAboutPitomnik();
                sendMessageToTelegramBot(update, result);
              
            } else if(update.message().text().equals("/2sub")) {
            
                String result = "Адрес и расписание: " + infoAboutPitomnikRepository.getLocationPitomnik() 
                + "," + "\n" + infoAboutPitomnikRepository.getShedulePitomnik(); 
                sendMessageToTelegramBot(update, result);   
            } else if(update.message().text().equals("/3sub")) {
                String result = "Правила безопасности: " + infoAboutPitomnikRepository.getPreventionOfAccidents();
                sendMessageToTelegramBot(update, result);   
            } else if(update.message().text().equals("/4sub")) {
                isItemFourSubWasCheck = true;
                String result = "Введите номер телефона, в формате 89991112325";
                sendMessageToTelegramBot(update, result);
                
            } else if(update.message().text().equals("/5sub")) {
                // номер телефона охраны
                String result = "Контакты охраны: " + infoAboutPitomnikRepository.getSecurityContact();
                sendMessageToTelegramBot(update, result);
                
            } else if(isItemFourSubWasCheck) {
               Boolean resultOfMatch = PATTERN_OF_NUMBER_TELEPHONE.matcher(update.message().text()).matches();

               if(resultOfMatch && update.message().text().trim().length() == 11) {
                contactsUserRepository.save(new ContactsUser(update.message().chat().id(), update.message().text().trim()));
                    logger.info("write phonenumber");
                    String result = "Спасибо, ваш номер телефона записан";
                    sendMessageToTelegramBot(update, result);
                    isItemFourSubWasCheck = false;
                } else {
                    sendMessageToTelegramBot(update, "Пожалуйста введите корректный одиннадцатизначный номер");   
                }
              

            } else if(update.message().text().equals("/6sub")) {
                String result = "Волонтер скоро вам ответит";
                // здесь якобы данные чата волонетров
                long chat = update.message().chat().id();
                telegramBot.execute(new SendMessage(chat, result));
            }
       }
}
