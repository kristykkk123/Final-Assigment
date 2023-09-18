package Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.time.*;


public class OpeningHoursRepository {

private class StoreHours {
        LocalTime openTime;
        LocalTime closeTime;

        StoreHours(LocalTime openTime, LocalTime closeTime) {
            this.openTime = openTime;
            this.closeTime = closeTime;
        }
    }

    private Map<String, StoreHours> hoursByDay = new HashMap<>();

    public OpeningHoursRepository() {
        loadOpeningHoursFromCSV();
    }

   
            private void loadOpeningHoursFromCSV() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    OpeningHoursRepository.class.getResourceAsStream("/data/OpeningHours.csv")))) {
        
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split by spaces and tabs
                    String[] parts = Arrays.stream(line.split("[ \t]+"))
                                            .filter(part -> !part.isEmpty())
                                            .toArray(String[]::new);
        
                    int dayOfWeekNumber = Integer.parseInt(parts[0].trim());
                    String dayOfWeek = parts[1].trim().toUpperCase();
                    LocalTime openTime = LocalTime.parse(parts[2].trim());
                    LocalTime closeTime = LocalTime.parse(parts[3].trim());
        
                    StoreHours storeHours = new StoreHours(openTime, closeTime);
                    hoursByDay.put(dayOfWeek, storeHours);
                } 
                System.out.println("Loaded days from CSV:");
                hoursByDay.keySet().forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

    public LocalTime getOpeningTimeForDay(String day) {
        StoreHours storeHours = hoursByDay.get(day);
        if (storeHours == null) {
            throw new IllegalArgumentException("No opening hours found for day: " + day);
        }
        return storeHours.openTime;
    }

    public LocalTime getClosingTimeForDay(String day) {
        StoreHours storeHours = hoursByDay.get(day);
    if (storeHours == null) {
        throw new IllegalArgumentException("No closing hours found for day: " + day);
    }
    return storeHours.closeTime;
}


































}