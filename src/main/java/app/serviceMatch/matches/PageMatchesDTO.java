package app.serviceMatch.matches;

import app.utils.SessionFactoryUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@NoArgsConstructor
public class PageMatchesDTO {
    @Id
    private Long id;
    @Column(name = "player1")
    private String Player1;
    @Column(name = "player2")
    private String Player2;
    @Column(name = "winner")
    private String winner;
    @Column(name = "TotalCount")
    private int TotalCount;


    public PageMatchesDTO(String player1, String player2, String winner, String player) {
        Player1 = player1;
        Player2 = player2;
        this.winner = winner;
    }
}
