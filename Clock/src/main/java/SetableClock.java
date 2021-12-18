import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;

public class SetableClock extends Clock {
    private Instant currentTime;

    public SetableClock(Instant currentTime) {
        this.currentTime = currentTime;
    }

    public void setCurrentTime(Instant currentTime) {
        this.currentTime = currentTime;
    }

    public void addCurrentTime(long add, TemporalUnit unit) {
        this.setCurrentTime(this.instant().plus(add, unit));
    }

    @Override
    public ZoneId getZone() {
        throw new UnsupportedOperationException("This function deprecated in tests");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        throw new UnsupportedOperationException("This function deprecated in tests");
    }

    @Override
    public Instant instant() {
        return this.currentTime;
    }
}
