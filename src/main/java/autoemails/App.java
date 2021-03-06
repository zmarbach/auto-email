package autoemails;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class App {

    public static void main(String[] args) {
        DayService dayService = new DayService();
        EmailSender emailSender = new EmailSender();
        StepsFromFileHandler stepsFromFileHandler = new StepsFromFileHandler();


        Date feb29 = new Date(2020, Calendar.FEBRUARY, 29);
            long feb29Time = feb29.getTime();

            dayService.generateAndReturnDaysInFeb();

            //TODO - handle issue with NOT sending emails on Sat/Sun and send those results on Monday
            while(new Date().getTime() <= feb29Time && emailSender.getNumOfEmailsSentToDawn() < 50){
                for (Day day : dayService.getDays()) {
                    LocalDateTime now = LocalDateTime.now();

                    //only check and take action at 9am every day in Feb
                    if(now.getHour() == 9 && now.getMinute() == 1 && now.getSecond() == 1){

                        //read file and assign most current values
                        stepsFromFileHandler.assignFileValuesToDay(day);

                        if(shouldSendEmail(now, day)){
                            emailSender.execute(day);
                        }
                    }
                }
            }
        }

        public static boolean shouldSendEmail(LocalDateTime now, Day day){
            //return true IF...
            // currentTime is after midnight of given day
            // AND it is 9am
            // AND email has not already been sent for that day
            return  now.isAfter(day.getLocalDateTime().plusDays(1))
                    && !day.isEmailSent();
        }
    }

