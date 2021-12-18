import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class StatisticTest {
    private EventsStatistic statistic;
    private SetableClock clock;
    private final double delta = 0.0000001;

    @Before
    public void resetTime() {
        clock = new SetableClock(Instant.now());
        statistic = new Statistic(clock);
    }

    @Test
    public void testOutdated() {
        statistic.incEvent("Old example");
        clock.addCurrentTime(1, ChronoUnit.HOURS);

        Assert.assertEquals(0, statistic.getEventStatisticByName("Old example"), delta);
    }

    @Test
    public void testStatisticByName() {
        statistic.incEvent("First example");
        statistic.incEvent("First example");
        statistic.incEvent("First example");

        Assert.assertEquals(3.0 / 60, statistic.getEventStatisticByName("First example"), delta);
    }

    @Test
    public void testSeveralStatisticByName() {
        statistic.incEvent("First example");
        clock.addCurrentTime(1, ChronoUnit.HOURS);

        statistic.incEvent("First example");
        clock.addCurrentTime(30, ChronoUnit.MINUTES);
        statistic.incEvent("First example");
        statistic.incEvent("Second example");

        Assert.assertEquals(2.0 / 60, statistic.getEventStatisticByName("First example"), delta);
    }

    @Test
    public void testNoneStatisticByName() {
        Assert.assertEquals(0, statistic.getEventStatisticByName("None example"), delta);
    }

    @Test
    public void testAllEventStatistic() {
        statistic.incEvent("First example");
        clock.addCurrentTime(1, ChronoUnit.HOURS);

        statistic.incEvent("First example");
        clock.addCurrentTime(30, ChronoUnit.MINUTES);
        statistic.incEvent("First example");
        statistic.incEvent("Second example");

        HashMap<String, Double> eventStatistics = statistic.getAllEventStatistic();
        Assert.assertTrue(eventStatistics.containsKey("First example"));
        Assert.assertTrue(eventStatistics.containsKey("Second example"));
        Assert.assertFalse(eventStatistics.containsKey("Another example"));

        Assert.assertEquals(2.0 / 60, eventStatistics.get("First example"), delta);
        Assert.assertEquals(1.0 / 60, eventStatistics.get("Second example"), delta);
    }
}
