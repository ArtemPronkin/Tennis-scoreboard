package app.serviceMatch.matchesScoreCalculations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreException extends Exception {
    String message;
}
