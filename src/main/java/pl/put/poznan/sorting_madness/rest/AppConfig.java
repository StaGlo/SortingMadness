package pl.put.poznan.sorting_madness.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.put.poznan.sorting_madness.logic.SortingMadness;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("floatSortingMadness")
    public SortingMadness<Float> floatSortingMadness() {
        return new SortingMadness<>();
    }

    @Bean
    @Qualifier("stringSortingMadness")
    public SortingMadness<String> stringSortingMadness() {
        return new SortingMadness<>();
    }
}
