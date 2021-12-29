package za.co.onespark.codingproblems.conferencemanagement.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class Session {
    private String title;
    private long duration;
    private LocalTime startTime;

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        var space = " ";
        var checkForMinAppension = "Networking Event".equalsIgnoreCase(title) | "Lunch".equalsIgnoreCase(title) ? "" : duration + "min";
        return startTime.format(timeFormatter) + space + title + space + checkForMinAppension;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
