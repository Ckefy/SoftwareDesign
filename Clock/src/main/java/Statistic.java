import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

public class Statistic implements EventsStatistic {
    private final HashMap<String, ArrayList<Instant>> timestampEvents;
    private final Clock clock;

    private final double MINUTES_IN_HOUR = 60;

    public Statistic(Clock clock) {
        this.clock = clock;
        this.timestampEvents = new HashMap<>();
    }

    @Override
    public void incEvent(String eventName) {
        timestampEvents.putIfAbsent(eventName, new ArrayList<>());
        timestampEvents.get(eventName).add(clock.instant());
    }

    @Override
    public double getEventStatisticByName(String eventName) {
        this.updateStatistic();

        return getStatisticWithoutUpdate(eventName);
    }

    @Override
    public HashMap<String, Double> getAllEventStatistic() {
        this.updateStatistic();

        HashMap<String, Double> resultStatistic = new HashMap<>();
        for (String eventName : this.timestampEvents.keySet()) {
            resultStatistic.put(eventName, this.getStatisticWithoutUpdate(eventName));
        }

        return resultStatistic;
    }

    @Override
    public void printStatistic() {
        HashMap<String, Double> resultStatistic = this.getAllEventStatistic();

        for (String eventName : resultStatistic.keySet()) {
            System.out.println("Requests per minute for method " + eventName + " is " + resultStatistic.get(eventName));
        }
    }

    private double getStatisticWithoutUpdate(String eventName) {
        if (!timestampEvents.containsKey(eventName)) {
            return 0;
        }

        return timestampEvents.get(eventName).size() / MINUTES_IN_HOUR;
    }

    private void updateStatistic() {
        Instant pastHour = clock.instant().minus(1, ChronoUnit.HOURS);

        for (String eventName : this.timestampEvents.keySet()) {
            ArrayList<Instant> filtered = new ArrayList<>();

            for (Instant inst : this.timestampEvents.get(eventName)) {
                if (inst.isAfter(pastHour)) {
                    filtered.add(inst);
                }
            }

            if (filtered.isEmpty()) {
                this.timestampEvents.remove(eventName);
            } else {
                this.timestampEvents.put(eventName, filtered);
            }
        }
    }
}
