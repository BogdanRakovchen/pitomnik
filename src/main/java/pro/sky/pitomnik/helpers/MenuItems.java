package pro.sky.pitomnik.helpers;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MenuItems {

    private static final String introduce = "Привет, это чат-бот приюта для животных из Астаны,\nесли ты сюда зашел, значит знал дорогу и для чего она тебе, поэтому не буду тебя утомлять лишней балтовней, давай сразу к делу,вот список того, что тебе возможно ннннадо, короче выбирай:\n";
    private static final SortedMap<String, String> menuMap = new TreeMap<String, String>(
        Map.of(
        "1", "Узнать информацию о приюте",
        "2", "Как взять собаку из приюта",
        "3", "Прислать отчет о питомце",
        "4", "Позвать волонтера"
    ));

    public static final String menu() {
        StringBuilder menuItemConcat = new StringBuilder();
        menuItemConcat.append(introduce);
        menuMap.forEach((k, v) -> {
            menuItemConcat.append(k + "."  + " " + v + " " + "\n");
        });
        menuItemConcat.append("нажми цифру соответствующую твоим чаяниям, с частицей \"base\", например 1base!");
        String resultString = new String(menuItemConcat);
        return resultString;
    }
}
