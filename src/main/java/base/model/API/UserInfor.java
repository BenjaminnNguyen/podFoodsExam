package base.model.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfor {
    private String email;
    private String password;
}
