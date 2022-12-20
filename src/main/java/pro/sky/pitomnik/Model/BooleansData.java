package pro.sky.pitomnik.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class BooleansData {
    private Boolean oneBase = false;
    private Boolean twoBase = false;
    private Boolean threeBase = false;
    private Boolean fourBase = false;
    private Boolean twoSubOfTwoBase = false;
    private Boolean threeSubOfThreeBase = false;


    public BooleansData() {

    }

}
