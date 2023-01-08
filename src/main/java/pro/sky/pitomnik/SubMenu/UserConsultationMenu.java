package pro.sky.pitomnik.SubMenu;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import pro.sky.pitomnik.Interface.MenuForUser;

@Component
public class UserConsultationMenu implements MenuForUser {

    @Override
    public String menu() {
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
        return stringBuilder.toString();
    }
}
