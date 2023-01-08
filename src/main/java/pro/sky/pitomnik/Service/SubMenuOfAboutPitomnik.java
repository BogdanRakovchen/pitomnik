package pro.sky.pitomnik.Service;

import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Abstract.SubMenu;
import pro.sky.pitomnik.Model.ContactsUser;
import pro.sky.pitomnik.Repository.InfoAboutPitomnikRepository;
import pro.sky.pitomnik.Repository.ContactsUserRepository;

@Service
public class SubMenuOfAboutPitomnik extends SubMenu {

    private InfoAboutPitomnikRepository infoAboutPitomnikRepository;
    private boolean isItemFourSubWasCheck = false;

    public SubMenuOfAboutPitomnik(
        TelegramBot telegramBot, 
        ContactsUserRepository contactsUserRepository,
        InfoAboutPitomnikRepository infoAboutPitomnikRepository
        ) {
        super(telegramBot,contactsUserRepository);
        this.infoAboutPitomnikRepository = infoAboutPitomnikRepository;
    }

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
              

            } else if(update.message().text().equals("/5sub")) {
                String result = "Волонтер скоро вам ответит";
                // здесь якобы данные чата волонетров
                long chatId = update.message().chat().id();
                telegramBot.execute(new SendMessage(chatId, result));
            }
       }
}
