package za.co.onespark.codingproblems.conferencemanagement.service;

import za.co.onespark.codingproblems.conferencemanagement.model.Session;
import za.co.onespark.codingproblems.conferencemanagement.model.Track;

import java.io.BufferedReader;
import java.util.List;

public interface ConferenceManagementService {


    default Track createTrack(List<Session> sessions) {
        return Track.builder()
                .morningSessions(createMorningSessions(sessions))
                .afternoonSessions(createAfternoonSessions(sessions))
                .build();
    }

    List<Session> createAfternoonSessions(List<Session> sessions);

    List<Session> createSessions(BufferedReader bufferedReader);

    List<Session> createMorningSessions(List<Session> sessions);
}
