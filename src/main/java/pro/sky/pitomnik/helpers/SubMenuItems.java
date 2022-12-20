package pro.sky.pitomnik.helpers;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class SubMenuItems {

   private static final String introduce = "Привет, здесь ты можешь узнать информацию о приюте \n";
   public static final SortedMap<String, String> subMenuMapOneBase = new TreeMap<String, String>(
    
    Map.of(
        "1", "могу рассказать о приюте",
        "2", "расписание работы приюта и адрес",
        "3", "общие рекомендации о технике безопасности на территории приюта",
        "4", "отправить контактные данные для связи",
        "5", "позвать волонтера"
    )); 
    
    public static final SortedMap<String, String> subMenuMapTwoBase = new TreeMap<String, String>(
    
    Map.of(
        "01", "правила знакомства с собакой",
        "2", "список документов, чтобы забрать собаку",
        "3", "список рекомендаций по транспортировке животного",
        "4", "список рекомендаций по обустройству дома для щенка",
        "5", "список рекомендаций по обустройству дома для взрослой собаки",
        "6", "список рекомендаций по обустройству дома для собаки с ограниченными возможностями",
        "7", "советы кинолога по первичному общению с собакой",
        "8", "рекомендации по проверенным кинологам",
        "9", "список причин для отказа в выдаче собаки",
        "10", "записать контактные данные для связи"
    )); 

    public static final SortedMap<String, String> subMenuMapThreeBase = new TreeMap<String, String>(
    
    Map.of(
        "1", "прислать фото животного",
        "2", "прислать рацион животного",
        "3", "прислать отчет о самочувствии животного",
        "4", "прислать отчет об изменениях в поведении",
        "5", "запросить форму ежедневого отчета"
    )); 
    
    

    public static final String subMenu(SortedMap<String, String> menuMap) {
        StringBuilder menuItemConcat = new StringBuilder();
        menuItemConcat.append(introduce);
        menuMap.forEach((k, v) -> {
            menuItemConcat.append(k + "."  + " " + v + " " + "\n");
        });
        menuItemConcat.append("нажми цифру соответствующую твоим чаяниям, с частицей \"sub\", например 1sub! \n или цифру 4 чтобы позвать волонтера");
        String resultString = new String(menuItemConcat);
        return resultString;
    }
    
 
    
}
