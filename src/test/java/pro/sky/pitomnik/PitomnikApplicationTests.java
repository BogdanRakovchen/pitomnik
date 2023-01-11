package pro.sky.pitomnik;

import java.util.Map;
import java.util.TreeMap;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pro.sky.pitomnik.Service.TelegramBotUpdateListenerService;
import pro.sky.pitomnik.SubMenu.InfoAboutPitomnikMenu;
import pro.sky.pitomnik.SubMenu.KeepengPet;
import pro.sky.pitomnik.SubMenu.UserConsultationMenu;

@SpringBootTest
class PitomnikApplicationTests {

	@Autowired
	private TelegramBotUpdateListenerService telegramBotUpdateListenerService;

	@Autowired
	private InfoAboutPitomnikMenu infoAboutPitomnikMenu;

	@Autowired
	private KeepengPet keepengPet;

	@Autowired
	private UserConsultationMenu userConsultationMenu;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldResultBaseMenuMapOfStringValueOf() {

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
        

		String actual = telegramBotUpdateListenerService.menu();
		String expected = stringBuilder.toString();

		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void shouldResultInfoAboutPitomnikSubMenuMapOfStringValueOf() {
		Map<String, String> menuItems = new TreeMap<>(
            Map.of(
            "1", "Узнать информацию о приюте",
            "2", "расписание работы, адрес приюта",
            "3", "о технике безопасности на территории приюта",
            "4", "оставить контактные данные",
            "5", "позвать волонтера"
        )
        );
       StringBuilder stringBuilder = new StringBuilder();
       menuItems.forEach((k,v) -> {
          stringBuilder.append(k + "." + " " + v + "\n");
       });
        
		String actual = infoAboutPitomnikMenu.menu();
		String expected = stringBuilder.toString();

		Assertions.assertEquals(expected, actual);
    }
	
	@Test
	public void shouldResultKeepengPetSubMenuMapOfStringValueOf() {
		
			Map<String, String> menuItems = new TreeMap<>(
				Map.of(
				"1", "получить форму ежедневного отчета",   
				"2", "отправить фото животного и форму ежедневного отчета",
				"3", "позвать волонтера"
			)
			);
		   StringBuilder stringBuilder = new StringBuilder();
		   menuItems.forEach((k,v) -> {
			  stringBuilder.append(k + "." + " " + v + "\n");
		   });
			
			String actual = keepengPet.menu();
			String expected = stringBuilder.toString();

			Assertions.assertEquals(expected, actual);
		
	}

	@Test
	public void shouldResultUserConsultationSubMenuMapOfStringValueOf() {
		Map<String, String> menuItems = new TreeMap<>(
            Map.of(
            "1", "правила знакомства с собакой",
            "2", "список документов, чтобы взять собаку из приюта",
            "3", "список рекомендаций по транспортировке животного",
            "4", "список рекомендаций по обустройству дома для щенка",
            "5", "список рекомендаций по обустройству дома для взрослой собаки",
            "6", "список рекомендаций по обустройству дома для собаки с ограниченными возможностями",
            "7", "советы кинолога по первичному общению с собакой",
            "8", "рекомендации по проверенным кинологам",
            "9", "список причин для отказа"
        )
        );
        menuItems.put("10", "оставить контактный номер");
        menuItems.put("11", "позвать волонтера");

       StringBuilder stringBuilder = new StringBuilder();
       menuItems.forEach((k,v) -> {
          
          stringBuilder.append(k + "." + " " + v + "\n");
       });
	   String actual = userConsultationMenu.menu();
	   String expected = stringBuilder.toString();

	   Assertions.assertEquals(expected, actual);
    }

}


