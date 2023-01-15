package pro.sky.pitomnik.Controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.callbacks.Callback;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import pro.sky.pitomnik.Model.ContactsUser;
import pro.sky.pitomnik.Model.Report;
import pro.sky.pitomnik.Model.Dog.ImageDog;
import pro.sky.pitomnik.Repository.ContactsUserRepository;

@RestController
@RequestMapping("/user")
public class PitomnikController {

    private final ContactsUserRepository contactsUserRepository;

    public PitomnikController(ContactsUserRepository contactsUserRepository) {
        this.contactsUserRepository = contactsUserRepository;
    }
    
    
    @GetMapping("/contact")
    @Operation(summary = "поиск пользователя с контактными номерами")
    @ApiResponse(
        description = "возвращает id, id чата и контактный номер пользователя",
        content = @Content(
            schema = @Schema(implementation = ContactsUser.class)
        ))
    @ApiResponse(responseCode = "400", description = "неправильный идентификатор") 
    @ApiResponse(responseCode = "200", description = "запрос выполнен")
    public void getContactsUser(@Parameter(schema = @Schema(type = "long")) long id) {
       
    }

    @GetMapping("/report")
    @Operation(method = "get", summary = "поиск отчета пользователя по id чата")
    @ApiResponse(
        description = "возвращает id, id чата, отчет, тип файла отчета, и дату отправления",
        content = @Content(
            schema = @Schema(implementation = Report.class)
        ))
    @ApiResponse(responseCode = "400", description = "неправильный идентификатор") 
    @ApiResponse(responseCode = "200", description = "запрос выполнен успешно")
    @ApiResponse(responseCode = "500", description = "ошибка на сервере")
     public void getReport(@Parameter(schema = @Schema(type = "long")) long id) {
       
    }


    @GetMapping("/imageanimal")
    @Callback(
        operation = {
            @Operation(
                method = "get",
                description = "получение данных id, chat_id, тип файла и изображение животного",
                parameters = {
                    @Parameter(schema = @Schema(implementation = ImageDog.class))
                },
                responses = {
                    @ApiResponse(responseCode = "200", description = "запрос выполнен успешно"),
                    @ApiResponse(responseCode = "400", description = "неправильный идентификатор")
                }
            )
        }
        
    )
     public void getImageAnimal(@Parameter(schema = @Schema(type = "long")) long id) {
       
    }
}
