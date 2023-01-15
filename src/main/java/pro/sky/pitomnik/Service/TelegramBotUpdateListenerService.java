package pro.sky.pitomnik.Service;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import pro.sky.pitomnik.Constants.Menu;
import pro.sky.pitomnik.Interface.MenuForUser;
import pro.sky.pitomnik.Model.UserPitomnik;
import pro.sky.pitomnik.Repository.UserPitomnikRepository;
import pro.sky.pitomnik.Service.Cat.SubMenuCatOfUserConsultation;
import pro.sky.pitomnik.Service.Cat.SubMenuOfAboutCatPitomnik;
import pro.sky.pitomnik.Service.Dog.SubMenuOfAboutPitomnik;
import pro.sky.pitomnik.Service.Dog.SubMenuOfUserConsultation;
import pro.sky.pitomnik.SubMenu.InfoAboutPitomnikMenu;
import pro.sky.pitomnik.SubMenu.KeepengPet;
import pro.sky.pitomnik.SubMenu.UserConsultationMenu;

@Service
public class TelegramBotUpdateListenerService implements UpdatesListener, MenuForUser {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListenerService.class);
    
    private final TelegramBot telegramBot;
    private final InfoAboutPitomnikMenu infoAboutPitomnikMenu;
    private final SubMenuOfAboutPitomnik subMenuOfAboutPitomnik;
    private final SubMenuOfAboutCatPitomnik subMenuOfAboutCatPitomnik;
    private final SubMenuCatOfUserConsultation subMenuCatOfUserConsultation;
    private final UserConsultationMenu userConsultationMenu;
    private final SubMenuOfUserConsultation subMenuOfUserConsultation;
    private final SubMenuKeepingPet subMenuKeepingPet;
    private final KeepengPet keepengPet;
    private final UserPitomnikRepository userPitomnikRepository;
    private boolean isCheckOfCommandStart = false;
    private boolean isCheck1Base = false;
    private boolean isCheck2Base = false;
    private boolean isCheck3Base = false;
    private boolean isCheck4Base = false;
    

    
    public TelegramBotUpdateListenerService(TelegramBot telegramBot, 
    InfoAboutPitomnikMenu infoAboutPitomnikMenu,
    SubMenuOfAboutPitomnik subMenuOfAboutPitomnik,
    SubMenuOfAboutCatPitomnik subMenuOfAboutCatPitomnik,
    SubMenuCatOfUserConsultation subMenuCatOfUserConsultation,
    UserConsultationMenu userConsultationMenu,
    SubMenuOfUserConsultation subMenuOfUserConsultation,
    SubMenuKeepingPet subMenuKeepingPet,
    KeepengPet keepengPet,
    UserPitomnikRepository userPitomnikRepository) { 
        this.telegramBot = telegramBot;   
        this.infoAboutPitomnikMenu = infoAboutPitomnikMenu;
        this.subMenuOfAboutPitomnik = subMenuOfAboutPitomnik;
        this.subMenuOfAboutCatPitomnik = subMenuOfAboutCatPitomnik;
        this.subMenuCatOfUserConsultation = subMenuCatOfUserConsultation;
        this.userConsultationMenu = userConsultationMenu;
        this.subMenuOfUserConsultation = subMenuOfUserConsultation;
        this.subMenuKeepingPet = subMenuKeepingPet;
        this.keepengPet = keepengPet;
        this.userPitomnikRepository = userPitomnikRepository;
    }

   
   
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    public TelegramBot getTelegramBot() {
        return telegramBot;
    }
   
    @Override
    public String menu() {
        Map<String, String> menuItems = new TreeMap<>(
            Map.of(
            "1", "Узнать информацию о приюте",
            "2", "Как взять собаку из приюта",
            "3", "Прислать отчет о питомце",
            "4", "Позвать волонтера"
        )
        );
       StringBuilder stringBuilder = new StringBuilder();
       menuItems.forEach((k,v) -> {
          stringBuilder.append(k.trim() + "." + " " + v + "\n");
       });
        return stringBuilder.toString();
    }

   /**
    * описание - метод создает меню для выбора приюта
    * @return меню выбора приюта в виде строки
    */
    public String menuEntryDogAndCat() {
        Map<String, String> menuItems = new TreeMap<>(
            Map.of(
            "1", "Приют для кошек",
            "2", "Приют для собак"
        )
        );
       StringBuilder stringBuilder = new StringBuilder();
       menuItems.forEach((k,v) -> {
          stringBuilder.append(k.trim() + "." + " " + v + "\n");
       });
        return stringBuilder.toString();
    }

    /**
     * метод - вычисляет id пользователя и дает обратную связь
     * @param update
     * @param welcomeUserMessage - приветственное сообщение для пользователя
     * @param menu
    */
    private void sendMessageToTelegramBot(Update update, String welcomeUserMessage, String menu) {
        long chatId = update.message().chat().id();
        String result = welcomeUserMessage + "\n" + menu;
        telegramBot.execute(new SendMessage(chatId, result)); 
    }
    
    /**
     * метод - основной, для обработки все запросов пользователя чат-бота
    */
    @Override
    public int process(List<Update> updates) {
        
        updates.forEach(update -> {
            // base menu
            if(update.message().text() != null && update.message().text().equals("/start")) {


                //    сброс состояния 
                isCheck1Base = false;
                isCheck2Base = false;
                isCheck3Base = false;
                isCheck4Base = false;      
                Menu.isDog = false;
                Menu.isCat = false;             
            
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь выбрать для себя нужный приют. При выборе в этом меню, прописывай пункты по типу /dog или /cat Для отмены и возврата в основное меню пришли /start", menuEntryDogAndCat());

            } else if(update.message().text() != null && update.message().text().equals("/dog")) {
               
                Menu.isDog = true;
              
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь выбрать для себя нужную опцию и получить актуалную информацию о приюте для собак. При выборе в этом меню, прописывай пункты по типу /1base, /2base и т.д. Для отмены и возврата в основное меню пришли /start", menu());
            } else if(update.message().text() != null && update.message().text().equals("/cat")) {

                Menu.isCat = true;

                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь выбрать для себя нужную опцию и получить актуалную информацию о приюте для кошек. При выборе в этом меню, прописывай пункты по типу /1base, /2base и т.д. Для отмены и возврата в основное меню пришли /start", menu());
            } 
            
            
            else if(update.message().text() != null && update.message().text().equals(Menu.ONE_BASE)) {
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь узнать для себя полную информацию о приюте. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", infoAboutPitomnikMenu.menu());
                isCheck1Base = true;
            } else if(update.message().text() != null && update.message().text().equals(Menu.TWO_BASE)) {
                isCheck2Base = true;
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь узнать для себя полную информацию о том, как надлежащим образом подготовиться ко встречи с питомцем. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", userConsultationMenu.menu());
            } else if(update.message().text() != null && update.message().text().equals(Menu.THREE_BASE)) {
                isCheck3Base = true;
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь прислать информацию о состоянии питомца. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", keepengPet.menu());
            } else if(update.message().text() != null && update.message().text().equals(Menu.FOUR_BASE)) {
                isCheck4Base = true;
                String result = "Волонтер скоро вам ответит";
                long chatId = update.message().chat().id();
                telegramBot.execute(new SendMessage(chatId, result));
                // здесь якобы данные чата волонетров
                telegramBot.execute(new SendMessage("12345", "Волонтер тебя зовут"));
     } 

            // submenu
            if(isCheck1Base && Menu.isDog) {
                subMenuOfAboutPitomnik.subMenuOfAboutPitomnik(update);
            } else if(isCheck1Base && Menu.isCat){
                subMenuOfAboutCatPitomnik.subMenuOfAboutCatPitomnik(update);                
            }
            
            if(isCheck2Base && Menu.isDog) {
                subMenuOfUserConsultation.subMenuOfUserConsultation(update);
            } else if(isCheck2Base && Menu.isCat) {
                subMenuCatOfUserConsultation.subMenuCatOfUserConsultation(update);
            }
            
            if(isCheck3Base) {
                 
                    try {
                        subMenuKeepingPet.subMenuOfKeepingPet(update);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }  
            } 
            // если испытательный период закончен
            if(new UserPitomnik().getPassedTest()) {
                // заносим информацию об этом в базу данных 
                userPitomnikRepository.save(
                    new UserPitomnik(update.message().chat().id(), 
                    new UserPitomnik().getCountPasses(), 
                    new UserPitomnik().getPassedTest()));

                    String result = "Волонтер, пользователь прошел испытательный период, загляни в базу!";
                    // здесь якобы данные чата волонетров
                    telegramBot.execute(new SendMessage("12345", result));

            }
        });
    return UpdatesListener.CONFIRMED_UPDATES_ALL;
    
    }
}
