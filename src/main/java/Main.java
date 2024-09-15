import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws Exception {

        if (log.isInfoEnabled()) {
            log.info("Logger is initialized correctly.");
        } else {
            System.out.println("Logger is not initialized correctly.");
        }

        log.info("Test info log");
        log.error("Test error log");

        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);


        log.info("Race is started. Horses amount: {}", horses.size());

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");

        log.info("End of the race. Winner is {}", hippodrome.getWinner().getName());
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
