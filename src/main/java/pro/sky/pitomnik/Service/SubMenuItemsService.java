package pro.sky.pitomnik.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Model.UserOfPitomnik;
import pro.sky.pitomnik.Repository.InfoAboutPitomnikRepository;
import pro.sky.pitomnik.Repository.UserOfPitomnikRepository;
import pro.sky.pitomnik.SubMenu.InfoAboutPitomnik;

@Service
public class SubMenuItemsService {

    private Logger logger = LoggerFactory.getLogger(SubMenuItemsService.class);


    private TelegramBot telegramBot;
    private InfoAboutPitomnikRepository infoAboutPitomnikRepository;
    private UserOfPitomnikRepository userOfPitomnikRepository;
    private static final Pattern PATTERN_OF_NUMBER_TELEPHONE = Pattern.compile("[0-9]+"); 

    private boolean isItemFourSubWasCheck = false;


    public SubMenuItemsService(TelegramBot telegramBot, 
    InfoAboutPitomnikRepository infoAboutPitomnikRepository,
    UserOfPitomnikRepository userOfPitomnikRepository) {
        this.telegramBot = telegramBot;
        this.infoAboutPitomnikRepository = infoAboutPitomnikRepository;
        this.userOfPitomnikRepository = userOfPitomnikRepository;
    }


    private void sendMessageToTelegramBot(Update update, String result ) {
        long chatId = update.message().chat().id();
        telegramBot.execute(new SendMessage(chatId, result)); 
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
                // здесь сохранение в бз, но id не генерируется автоматически
                if(resultOfMatch && update.message().text().trim().length() == 11) {
                    userOfPitomnikRepository.save(new UserOfPitomnik(update.message().text().trim()));
                    logger.info("write phonenumber");
                    String result = "Спасибо, ваш номер телефона записан";
                    sendMessageToTelegramBot(update, result);
                    isItemFourSubWasCheck = false;
                } else {
                    sendMessageToTelegramBot(update, "Пожалуйста введите корректный одиннадцатизначный номер");   
                }
              

            } else if(update.message().text().equals("/5sub")) {
                String result = "Волонтер, тебя зовут";
                // здесь якобы данные чата волонетров
                long chatId = update.message().chat().id();
                telegramBot.execute(new SendMessage(chatId, result));
            }
       
}
    
}
