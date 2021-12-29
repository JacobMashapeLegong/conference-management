package za.co.onespark.codingproblems.conferencemanagement.service.impl;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConferenceManagementServiceImplTest {

    private ConferenceManagementServiceImpl conferenceManagementService = new ConferenceManagementServiceImpl();

    @Test
    void createSessions() {
        try {
            var bufferedReader = new BufferedReader(new FileReader("src/main/java/za/co/onespark/codingproblems/conferencemanagement/input/conference.txt"));

            var sessions = conferenceManagementService.createSessions(bufferedReader);
            assertEquals(sessions.size(), 19);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createMorningSessions() {
        try {
            var bufferedReader = new BufferedReader(new FileReader("src/main/java/za/co/onespark/codingproblems/conferencemanagement/input/conference.txt"));

            var sessions = conferenceManagementService.createSessions(bufferedReader);
            assertEquals(sessions.size(), 19);
            var morningSessions = conferenceManagementService.createMorningSessions(sessions);
            assertEquals(4, morningSessions.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createAfternoonSessions() {
        try {
            var bufferedReader = new BufferedReader(new FileReader("src/main/java/za/co/onespark/codingproblems/conferencemanagement/input/conference.txt"));

            var sessions = conferenceManagementService.createSessions(bufferedReader);
            var morningSessions = conferenceManagementService.createMorningSessions(sessions);
            var afternoonSessions = conferenceManagementService.createAfternoonSessions(sessions);
            assertEquals(morningSessions.size(), 4);
            assertEquals(afternoonSessions.size(), 6);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTracks() {
        try {
            var bufferedReader = new BufferedReader(new FileReader("src/main/java/za/co/onespark/codingproblems/conferencemanagement/input/conference.txt"));
            var sessions = conferenceManagementService.createSessions(bufferedReader);
            int trackCount = 1;
            while (!sessions.isEmpty()) {
                var track = conferenceManagementService.createTrack(sessions);
                System.out.println("\nTrack " + trackCount + ":\n");
                System.out.println(track);
                trackCount++;
            }
            assertEquals(3, trackCount);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("conference input not found");
        }
    }
}