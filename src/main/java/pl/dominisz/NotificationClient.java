package pl.dominisz;

public class NotificationClient {

    private String email;
    private String emailLogin;
    private String emailPassword;

    public NotificationClient(String email, String emailLogin, String emailPassword) {
        this.email = email;
        this.emailLogin = emailLogin;
        this.emailPassword = emailPassword;
    }

    public void sendFailedNotification(ConversionResult conversionResult) {

    }
}
