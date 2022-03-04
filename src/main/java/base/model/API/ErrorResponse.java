package base.model.API;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private ArrayList<String> errors;

}
