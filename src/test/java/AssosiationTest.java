import by.nurbolat.tennismatchscoreboard.entity.Match;
import by.nurbolat.tennismatchscoreboard.entity.Player;
import by.nurbolat.tennismatchscoreboard.util.HibernateUtil;
import org.junit.jupiter.api.Test;

public class AssosiationTest {
        @Test
        void save(){
            var sessionFactory = HibernateUtil.getSessionFactory();
            var session = sessionFactory.openSession();

            session.beginTransaction();

            var player1 = Player.builder()
                    .name("Nurbolat")
                    .build();

            var player2 = Player.builder()
                    .name("Aslan")
                    .build();

            session.saveOrUpdate(player1);
            session.saveOrUpdate(player2);

            var match = Match.builder()
                    .player1Id(player1)
                    .player2Id(player2)
                    .winnerId(player1)
                    .build();

            session.saveOrUpdate(match);

            System.out.println(session.get(Match.class, 1));

            session.getTransaction().commit();
        }
}
