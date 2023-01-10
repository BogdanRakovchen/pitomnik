package pro.sky.pitomnik.Abstract;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Repository.ContactsUserRepository;

abstract public class SubMenu {
    protected final Logger logger = LoggerFactory.getLogger(SubMenu.class);
    protected final TelegramBot telegramBot;
    protected final ContactsUserRepository contactsUserRepository;
    protected static final Pattern PATTERN_OF_NUMBER_TELEPHONE = Pattern.compile("[0-9]+"); 

    public SubMenu(TelegramBot telegramBot, ContactsUserRepository contactsUserRepository) {
        this.telegramBot = telegramBot;
        this.contactsUserRepository = contactsUserRepository;
    }

    protected void sendMessageToTelegramBot(Update update, String result ) {
        long chatId = update.message().chat().id();
        telegramBot.execute(new SendMessage(chatId, result)); 
    }
}
