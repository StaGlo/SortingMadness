package pl.put.poznan.sorting_madness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the application.
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sorting_madness.rest", "pl.put.poznan.sorting_madness.logic"})
public class SortingMadnessApplication {

    /**
     * Main method of the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
