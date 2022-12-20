package pro.sky.pitomnik.Service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import pro.sky.pitomnik.Model.BooleansData;
import pro.sky.pitomnik.Model.User;
import pro.sky.pitomnik.Repository.*;
import pro.sky.pitomnik.helpers.MenuItems;
import pro.sky.pitomnik.helpers.SubItemsOfOneBaseItems;
import pro.sky.pitomnik.helpers.SubItemsOfThreeItems;
import pro.sky.pitomnik.helpers.SubItemsOfTwoBaseItems;
import pro.sky.pitomnik.helpers.SubMenuItems;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class TelegramBotUpdateListenerService implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListenerService.class);
    
    private TelegramBot telegramBot;
    private NewUserConsultationRepository newUserConsultationRepository;
    private PictureRepository pictureRepository;
    private SubItemsOfOneBaseItems subItemsOfOneBaseItems;
    private SubItemsOfTwoBaseItems subItemsOfTwoBaseItems;
    private SubItemsOfThreeItems subItemsOfThreeItems;
    private BooleansData booleansData;
    private Boolean isShowMenu = false;

    public TelegramBotUpdateListenerService(TelegramBot telegramBot,
    NewUserConsultationRepository newUserConsultationRepository,
    PictureRepository pictureRepository,
    SubItemsOfOneBaseItems subItemsOfOneBaseItems,
    SubItemsOfTwoBaseItems subItemsOfTwoBaseItems,
    SubItemsOfThreeItems subItemsOfThreeItems,
    BooleansData booleansData) { 
        this.telegramBot = telegramBot;
        this.newUserConsultationRepository = newUserConsultationRepository;
        this.pictureRepository = pictureRepository;
        this.subItemsOfOneBaseItems = subItemsOfOneBaseItems;
        this.subItemsOfTwoBaseItems = subItemsOfTwoBaseItems;
        this.subItemsOfThreeItems = subItemsOfThreeItems;
        this.booleansData = booleansData;
       
    }
   
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    private void checkQueryOfUser(String response, Long chatId) {

        String responses = response;
        SendMessage message;
        message = new SendMessage(chatId, responses);
        telegramBot.execute(message);
    
}
    
    @Override
    public int process(List<Update> updates) {
        
       

        updates.forEach(update -> {
            String response;
            SendMessage message = new SendMessage("", "");
            Long chatId = update.message().chat().id();
            telegramBot.execute(message);

        // основное меню    
        if (update.message().text().contains("/start")) {
        
           checkQueryOfUser(MenuItems.menu(), chatId);
           isShowMenu = true;
        } else if(update.message().text().contains("1base") && isShowMenu == true) {
            checkQueryOfUser(SubMenuItems.subMenu(SubMenuItems.subMenuMapOneBase), chatId); 
            booleansData.setOneBase(true);           
        } else if(update.message().text().contains("2base") && isShowMenu == true) {
            checkQueryOfUser(SubMenuItems.subMenu(SubMenuItems.subMenuMapTwoBase), chatId); 
            booleansData.setTwoSubOfTwoBase(true);     
        } else if(update.message().text().contains("3base") && isShowMenu == true) {
            checkQueryOfUser(SubMenuItems.subMenu(SubMenuItems.subMenuMapThreeBase), chatId); 
            booleansData.setThreeSubOfThreeBase(true);     
        } 
        else if(update.message().text().contains("4base") && isShowMenu == true) {
            chatId = update.message().chat().id();
            message = new SendMessage(chatId, "Волонтер тебя приглашают на помощь");
            telegramBot.execute(message);
        }

            

        // подменю 
        Boolean isOneBase = booleansData.getOneBase();
        Boolean isTwoSubOfTwoBase = booleansData.getTwoSubOfTwoBase();
        Boolean isThreeOfThreeBase = booleansData.getThreeSubOfThreeBase();
         if(isOneBase == true) {
            subItemsOfOneBaseItems.subOfOneBase(update); 
            }  else if(isTwoSubOfTwoBase == true) {
                subItemsOfTwoBaseItems.subOfTwoBase(update); 
            } else if (isThreeOfThreeBase == true) {
                subItemsOfThreeItems.subOfOneBase(update);
            }

            //  subItemsOfTwoBaseItems.subOfOneBase(update);
    
        // } else if(update.message().text().contains("4sub") && isShowMenu == true) {
            
        // }     

    });

    return UpdatesListener.CONFIRMED_UPDATES_ALL;
    
}

}