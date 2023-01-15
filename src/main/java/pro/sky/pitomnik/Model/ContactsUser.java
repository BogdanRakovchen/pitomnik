package pro.sky.pitomnik.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ContactsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    /**
     * контактный номер пользователя
    */
    private String contactsUser;

    public ContactsUser(Long chatId, String contactsUser) {
        this.chatId = chatId;
        this.contactsUser = contactsUser;
    }

    public ContactsUser(String contactsUser) {
        this.contactsUser = contactsUser;
    }
}
