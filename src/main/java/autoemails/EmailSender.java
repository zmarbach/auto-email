package autoemails;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    private EmailAuthenticator emailAuthenticator= new EmailAuthenticator();
    private StepsFromFileHandler stepsFromFileHandler = new StepsFromFileHandler();
    private int numOfEmailsSentToDawn = 0;

    public void execute(Day day) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, emailAuthenticator);
        session.setDebug(true);

        try {
            stepsFromFileHandler.assignStepsToDay(day);

            //send error email to me if steps = 0, that means I forgot to update the SS
            if(day.getStepCount() == 0){
                Message messageIfZeroSteps = new MimeMessage(session);
                messageIfZeroSteps.setFrom(new InternetAddress(emailAuthenticator.getUsername()));
                messageIfZeroSteps.setRecipients(Message.RecipientType.TO, InternetAddress.parse("zachary.marbach@improving.com"));
                messageIfZeroSteps.setSubject("Zero steps error!!!");
                messageIfZeroSteps.setText(day.getDaySlashMonth() + " reportedly had 0 steps...no email was sent to Dawn. You need to send manually.");

                Transport.send(messageIfZeroSteps);
            }

            //send email to Dawn and cc my Improving email if all is good
            else{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emailAuthenticator.getUsername()));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Dawn.Dearstone@improving.com"));
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("zachary.marbach@improving.com"));
                message.setSubject("Get Up And Move!");
                message.setText(day.getDaySlashMonth() + " - " + day.getStepCount() + " steps");

                Transport.send(message);
                numOfEmailsSentToDawn++;
                System.out.println("Total emails sent to Dawn = " + numOfEmailsSentToDawn);

                //must toggle emailSent so wont send duplicate emails
                day.setEmailSent(true);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumOfEmailsSentToDawn() {
        return numOfEmailsSentToDawn;
    }
}
