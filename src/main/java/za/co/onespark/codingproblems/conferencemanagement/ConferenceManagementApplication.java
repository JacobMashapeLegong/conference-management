package za.co.onespark.codingproblems.conferencemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.onespark.codingproblems.conferencemanagement.service.ConferenceManagementService;
import za.co.onespark.codingproblems.conferencemanagement.service.impl.ConferenceManagementServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@SpringBootApplication
public class ConferenceManagementApplication implements CommandLineRunner {
    /**
     * Conference >> Track[] >>> Sessions
     * >>>> Morning Sessions [9am - 12pm]
     * >>>>> Talk[]
     * ***** Lunch *****
     * >>>> Afternoon Sessions [1pm - Networking Event Start Time]
     * >>>>> Talk[]
     * ***** Networking Event Session ***** [ must start by 5pm and cannot start before 4pm] ..............
     */
    private final ConferenceManagementService conferenceManagementService = new ConferenceManagementServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\jacobmashape.legong\\" +
                    "OneDrive - Personal\\OneDrive\\OneSpark-Coding-Problems\\conference-management\\src\\main\\java\\" +
                    "za\\co\\onespark\\codingproblems\\conferencemanagement\\input\\conference.txt"));
            //TODO: Please replace with your machine's absolute path

            var sessions = conferenceManagementService.createSessions(bufferedReader);
            int trackCount = 1;
            while (!sessions.isEmpty()) {
                var track = conferenceManagementService.createTrack(sessions);
                System.out.println("\nTrack " + trackCount + ":\n");
                System.out.println(track);
                trackCount++;
            }
            //TODO : Find out why given output is inccorect for track 2.
        } catch (FileNotFoundException e) {
            throw new RuntimeException("conference input not found");
        }
    }

}
