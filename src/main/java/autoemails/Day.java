package autoemails;

import java.time.LocalDateTime;

public class Day {

    private String daySlashMonth;
    private LocalDateTime localDateTime;
    private boolean emailSent;
    private int stepCount;

    public Day(String daySlashMonth, LocalDateTime localDateTime) {
        this.daySlashMonth = daySlashMonth;
        this.localDateTime = localDateTime;
    }

    public String getDaySlashMonth() {
        return daySlashMonth;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public boolean isEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }


}
