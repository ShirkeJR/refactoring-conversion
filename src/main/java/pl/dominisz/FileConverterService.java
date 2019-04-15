package pl.dominisz;

public class FileConverterService {

    private static final Logger log = LoggerFactory.getLogger(FileConverterService.class);

    public FileConverterService() {
    }

    public ConversionResult convert(String f1, String f2) {

        log.info("Start converting app file");

        ConversionResult res = new ConversionResult();

        FileConverter c = new FileConverter();

        // We convert file f1 and write result to file f2.
        c.convert(f1, f2, res);

        // If file conversion took too long we send an email notification.
        if (res.getInf().get(0).getCode() != -1) {
            log.info("Stop converting app file " + f1);
            return res;
        } else {
            NotificationClient notificationSender =
                    new NotificationClient("admins@some.domain.com", "emailLogin", "password123");
            notificationSender.sendFailedNotification();
            return res;
        }
    }

}
