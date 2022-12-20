package pro.sky.pitomnik.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import pro.sky.pitomnik.Model.User;
import pro.sky.pitomnik.Repository.ConsultationPotentialOwnerRepository;
import pro.sky.pitomnik.Repository.UserRepository;

@Component
public class SubItemsOfTwoBaseItems {

    @Autowired
    private TelegramBot telegramBot;
    private ConsultationPotentialOwnerRepository consultationPotentialOwnerRepository;
 
    @Autowired
    private UserRepository userRepository;

    public SubItemsOfTwoBaseItems() {

    }


    public SubItemsOfTwoBaseItems(TelegramBot telegramBot, 
    ConsultationPotentialOwnerRepository consultationPotentialOwnerRepository,
    UserRepository userRepository) {
        this.telegramBot = telegramBot;
        this.consultationPotentialOwnerRepository = consultationPotentialOwnerRepository;
        this.userRepository = userRepository;
    }     

    // @Override
    public void subOfTwoBase(Update update) {
        String response;
        SendMessage message = new SendMessage("", "");
        Long chatId = update.message().chat().id();

        if(update.message().text().contains("1sub") ) {
            response = consultationPotentialOwnerRepository.getDogDatingRules();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } 
        else if(update.message().text().contains("2sub")) {
            response = "Cписок документов: \n " + consultationPotentialOwnerRepository.getListOfDocuments();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } 
        else if(update.message().text().contains("3sub")) {
            response = "Cписок рекомендаций по транспортировке животного: \n " + consultationPotentialOwnerRepository.getListOfRecommendationsForTransportingAnAnimal();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("4sub")) {
            response = "список рекомендаций по обустройству дома для щенка: \n " + consultationPotentialOwnerRepository.getListOfRecommendationsForHomeImprovementForAPuppy(); 
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("5sub")) {
            response = "список рекомендаций по обустройству дома для взрослой собаки: \n " + consultationPotentialOwnerRepository.getListOfRecommendationsForHomeImprovementForAnAdultDog();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("6sub")) {
            response = "список рекомендаций по обустройству дома для собаки с ограниченными возможностями: \n " + consultationPotentialOwnerRepository.getHomeImprovementListForDogsWithDisabilities();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("7sub")) {
            response = "советы кинолога по первичному общению с собакой: \n " + consultationPotentialOwnerRepository.getCynologistAdviceOnInitialCommunicationWithADog();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("8sub")) {
            response = "рекомендации по проверенным кинологам: \n " + consultationPotentialOwnerRepository.getRecommendationsForProvenCynologists();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("9sub")) {
            response = "список причин для отказа в выдаче собаки: \n " + consultationPotentialOwnerRepository.getListOfReasonsRefuse();
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("10sub")) {
            response = "записать контактные данные для связи: \n ";
            message = new SendMessage(chatId, response);
            telegramBot.execute(message);
        } else if(update.message().text().contains("12345")) {
                userRepository.save(new User(chatId, update.message().text() , "", "", ""));
                response = "контактный телефон записан";
                message = new SendMessage(chatId, response);
                telegramBot.execute(message);
            }
        }
    }
