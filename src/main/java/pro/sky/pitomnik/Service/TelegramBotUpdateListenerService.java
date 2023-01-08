package pro.sky.pitomnik.Service;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.SendResponse;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.botcommandscope.BotCommandScope;
import com.pengrad.telegrambot.model.botcommandscope.BotCommandScopeDefault;

import jakarta.annotation.PostConstruct;
import pro.sky.pitomnik.Interface.MenuForUser;
import pro.sky.pitomnik.SubMenu.InfoAboutPitomnik;

@Service
public class TelegramBotUpdateListenerService implements UpdatesListener, MenuForUser {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListenerService.class);
    
    private TelegramBot telegramBot;
    private InfoAboutPitomnik infoAboutPitomnik;
    private SubMenuItemsService subMenuItemsService;
    private boolean isCheckOfCommandStart = false;
    
    public TelegramBotUpdateListenerService(TelegramBot telegramBot, 
    InfoAboutPitomnik infoAboutPitomnik,
    SubMenuItemsService subMenuItemsService) { 
        this.telegramBot = telegramBot;   
        this.infoAboutPitomnik = infoAboutPitomnik;
        this.subMenuItemsService = subMenuItemsService;
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

    private void sendMessageToTelegramBot(Update update, String welcomeUserMessage, String menu) {
        long chatId = update.message().chat().id();
        String result = welcomeUserMessage + "\n" + menu;
        telegramBot.execute(new SendMessage(chatId, result)); 
    }
    
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            // base menu
            if(update.message().text().equals("/start")) {
                isCheckOfCommandStart = true;
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь выбрать для себя нужную опцию и получить актуалную информацию о приюте. При выборе в этом меню, прописывай пункты по типу /1base, /2base и т.д.", menu() );
            } else if(update.message().text().equals("/1base")) {
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь узнать для себя нужную информацию о приюте. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", infoAboutPitomnik.menu());
            } else if(update.message().text().equals("/2base")) {
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь узнать для себя нужную информацию о приюте. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", infoAboutPitomnik.menu());
            } else if(update.message().text().equals("/3base")) {
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь узнать для себя нужную информацию о приюте. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", infoAboutPitomnik.menu());
            } else if(update.message().text().equals("/4base")) {
                sendMessageToTelegramBot(update, "привет друг, здесь ты можешь узнать для себя нужную информацию о приюте. При выборе в этом меню, прописывай пункты по типу /1sub, /2sub и т.д.", infoAboutPitomnik.menu());
            } 
            // else {
            //     long chatId = update.message().chat().id();
            //     SendMessage sendMessage = new SendMessage(chatId, "начните с команды /start");
            //     telegramBot.execute(sendMessage);
            // }
            // submenu
            subMenuItemsService.subMenuOfAboutPitomnik(update);

        });
        

    return UpdatesListener.CONFIRMED_UPDATES_ALL;
    
}
}
