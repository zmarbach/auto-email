package autoemails;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class DayService {
    private List<Day> days = new ArrayList<Day>();

    public void generateAndReturnDaysInFeb(){
        //create day for each month in feb and add to set of days
        for (int i = 1; i < 30; i++) {
            LocalDate date = LocalDate.of(2020,2, i);
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
            Day day = new Day("2/" + i, dateTime);
            //set this cuz I started late so first 10 days wont be reported
            //TODO - need to update number below if APP stops at any time (based on which days have already been reported to Dawn)
            if(i <= 11){
                day.setEmailSent(true);
            }
            days.add(day);
        }

        for (Day day: days){
            System.out.println(day.getDaySlashMonth() + " --- " + day.getLocalDateTime() + " --- " + day.isEmailSent());
        }
    }

    public List<Day> getDays() {
        return days;
    }
}
