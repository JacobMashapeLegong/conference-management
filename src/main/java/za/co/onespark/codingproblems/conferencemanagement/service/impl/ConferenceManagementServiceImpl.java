package za.co.onespark.codingproblems.conferencemanagement.service.impl;

import za.co.onespark.codingproblems.conferencemanagement.model.Session;
import za.co.onespark.codingproblems.conferencemanagement.service.ConferenceManagementService;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConferenceManagementServiceImpl implements ConferenceManagementService {


    @Override
    public List<Session> createSessions(BufferedReader bufferedReader) {
        List<Session> sessions = new ArrayList<>();
        while (true) {
            try {
                var line = bufferedReader.readLine();
                if (line == null) break;

                int index;
                long duration;
                index = line.lastIndexOf(" ");
                if (line.endsWith("lightning")) {
                    duration = 5L;
                } else {
                    var minutes = line.substring(index + 1,line.lastIndexOf("min"));
                    duration = Long.parseLong(minutes);
                }

                sessions.add(Session.builder()
                        .title(line.substring(0, index))
                        .duration(duration)
                        .build());

            } catch (IOException e) {
                throw new RuntimeException("conference input not found");
            }
        }
        return sessions;
    }


    private List<Session> getSessions(List<Session> sessions, long totalDuration, LocalTime startTime) {
        List<Session> scheduledSessions = new ArrayList<>();
        var occupiedDuration = 0;
        while ((occupiedDuration <= totalDuration) && !sessions.isEmpty()) {
            var session = sessions.stream().findAny().get();
            session.setStartTime(startTime);
            if (occupiedDuration + session.getDuration() <= totalDuration) {
                startTime = startTime.plusMinutes(session.getDuration());
                scheduledSessions.add(session);
                sessions.remove(session);
            }
            occupiedDuration += session.getDuration();
        }
        return scheduledSessions; //TODO: Optimize algorithm to allocate full space of total duration
    }

    @Override
    public List<Session> createMorningSessions(List<Session> sessions) {
        return getSessions(sessions, 180L, LocalTime.of(9, 0));
    }

    @Override
    public List<Session> createAfternoonSessions(List<Session> sessions) {
        return getSessions(sessions, 240L, LocalTime.of(13, 0));
    }
}
