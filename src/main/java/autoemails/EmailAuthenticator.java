package autoemails;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
    final String username = "zachary.marbach@improving.com";  // like yourname@outlook.com
    final String password = "Buggywhip22!!";   // password here

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    public String getUsername() {
        return username;
    }
}
