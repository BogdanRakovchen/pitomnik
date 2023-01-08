package pro.sky.pitomnik.SubMenu;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import pro.sky.pitomnik.Interface.MenuForUser;

@Component
public class InfoAboutPitomnikMenu implements MenuForUser {
    
    @Override
    public String menu() {
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
        return stringBuilder.toString();
    }
}
