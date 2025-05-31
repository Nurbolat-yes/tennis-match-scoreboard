package by.nurbolat.tennismatchscoreboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "matches")
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player1")
    private Player player1Id;

    @ManyToOne
    @JoinColumn(name = "player2")
    private Player player2Id;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winnerId;

}
