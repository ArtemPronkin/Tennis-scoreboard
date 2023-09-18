package Controller.ServiceMatch.Score;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionScore extends Exception {
    String message;
}
