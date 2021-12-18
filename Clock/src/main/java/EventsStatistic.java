import java.util.HashMap;

public interface EventsStatistic {
    void incEvent(String eventName);

    double getEventStatisticByName(String eventName);

    HashMap<String, Double> getAllEventStatistic();

    void printStatistic();
}
