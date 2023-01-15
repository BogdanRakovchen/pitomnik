package pro.sky.pitomnik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pro.sky.pitomnik.Constants.Menu;
import pro.sky.pitomnik.Repository.Dog.InfoAboutPitomnikDogRepository;
import pro.sky.pitomnik.Service.TelegramBotUpdateListenerService;
import pro.sky.pitomnik.Service.Dog.SubMenuOfAboutPitomnik;
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

	@Autowired
	private InfoAboutPitomnikDogRepository infoAboutPitomnikDogRepository;

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
				"5", "контактные данные охраны для оформления пропуска на машину",
				"6", "позвать волонтера"
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
				"1", "правила знакомства с животным",
				"2", "список документов, чтобы взять животное из приюта",
				"3", "список рекомендаций по транспортировке животного",
				"4", "список рекомендаций по обустройству дома для животного",
				"5", "список рекомендаций по обустройству дома для взрослого животного",
				"6", "список рекомендаций по обустройству дома для животного с ограниченными возможностями",
				"7", "советы кинолога по первичному общению с животным",
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

	@Test
	public void shouldCheckCommandsTelegramBotWithValuesString() {

	List<String> constants = new ArrayList<>(Arrays.asList(Menu.ONE_BASE,Menu.TWO_BASE,Menu.THREE_BASE,Menu.FOUR_BASE,Menu.ONE_SUB,
			Menu.TWO_SUB,
			Menu.THREE_SUB,
			Menu.FOUR_SUB,
			Menu.FIVE_SUB,
			Menu.SIX_SUB,
			Menu.SEVEN_SUB,
			Menu.EIGHT_SUB,
			Menu.NINE_SUB, 
			Menu.TEN_SUB,
			Menu.ELEVEN_SUB
			));

			List<String> commands = new ArrayList<>(Arrays.asList("/1base","/2base","/3base","/4sub","/1sub",
				"/2sub",
				"/3sub",
				 "/4sub",
				 "/5sub",
				"/6sub",
				"/7sub",
				"/8sub",
				 "/9sub",
				"/10sub",
				 "/11sub"
			));

			
			
			constants.stream()
			.forEach(el -> commands.forEach(e -> Assertions.assertEquals(el, e)));	
	}


	@Test
	public void shouldResultTypeString() {
		String typeGetAboutPitomnik = infoAboutPitomnikDogRepository.getAboutPitomnik();
		String typeGetLocationPitomnik = infoAboutPitomnikDogRepository.getLocationPitomnik();
		String typeGetPreventionOfAccidents = infoAboutPitomnikDogRepository.getPreventionOfAccidents();
		String typeGetSecurityContact = infoAboutPitomnikDogRepository.getSecurityContact();
		String typeGetShedulePitomnik = infoAboutPitomnikDogRepository.getShedulePitomnik();

		Assertions.assertTrue(typeGetAboutPitomnik instanceof String, typeGetAboutPitomnik);
		Assertions.assertTrue(typeGetLocationPitomnik instanceof String, typeGetLocationPitomnik);
		Assertions.assertTrue(typeGetPreventionOfAccidents instanceof String, typeGetPreventionOfAccidents);
		Assertions.assertTrue(typeGetSecurityContact instanceof String, typeGetSecurityContact);
		Assertions.assertTrue(typeGetShedulePitomnik instanceof String, typeGetShedulePitomnik);

	}
}


