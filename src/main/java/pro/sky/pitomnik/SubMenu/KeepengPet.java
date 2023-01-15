package pro.sky.pitomnik.SubMenu;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component
public class KeepengPet {
 
    public String menu() {
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
        return stringBuilder.toString();
    }
}
