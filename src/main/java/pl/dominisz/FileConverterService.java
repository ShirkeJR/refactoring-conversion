package pl.dominisz;

public class FileConverterService {

    private Logger log;
    private NotificationClient notificationSender;
    private FileConverter fileConverter;

    public FileConverterService(NotificationClient notificationSender, FileConverter fileConverter, Logger logger) {
        this.notificationSender = notificationSender;
        this.fileConverter = fileConverter;
        this.log = logger;
    }

    public ConversionResult convert(String sourceFileName, String destinationFileName) {
        log.info("Start converting app file " + sourceFileName + " to " + destinationFileName);
        ConversionResult conversionResult = fileConverter.convert(sourceFileName, destinationFileName);
        log.info("Stop converting app file " + sourceFileName + " to " + destinationFileName);
        if (conversionResult.timeout()) {
            notificationSender.sendFailedNotification(conversionResult);
            log.info("Sent failed notification to sender");
        }
        return conversionResult;
    }
}
