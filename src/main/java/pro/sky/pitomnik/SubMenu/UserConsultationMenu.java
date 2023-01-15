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
        return stringBuilder.toString();
    }
}
