package za.co.onespark.codingproblems.conferencemanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
public class Track {
    @NonNull
    private List<Session> morningSessions;
    @NonNull
    private final Session lunch = Session.builder().title("Lunch").startTime(LocalTime.NOON).build();
    @NonNull
    private List<Session> afternoonSessions;
    @NonNull
    private final Session networkingEvent = Session.builder().title("Networking Event").build();

    @Override
    public String toString() {
        var lastSessionBeforeNwrtWorkEvnt = afternoonSessions.get(afternoonSessions.size()-1);
        networkingEvent.setStartTime(lastSessionBeforeNwrtWorkEvnt.getStartTime().plusMinutes(lastSessionBeforeNwrtWorkEvnt.getDuration()));
        var track = getString(morningSessions, "") + lunch + "\n";
        return getString(afternoonSessions, track) + networkingEvent;
    }

    private String getString(List<Session> sessions, String track) {
        for (var session : sessions) {
            track += session + "\n";
        }
        return track;
    }
}
