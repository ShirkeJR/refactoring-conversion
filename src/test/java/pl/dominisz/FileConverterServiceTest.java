package pl.dominisz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FileConverterServiceTest {


    private static final String SOURCE_FILENAME = "sourceFileName";
    private static final String DESTINATION_FILENAME = "destinationFileName";

    private FileConverterService fileConverterService;
    private FileConverter fileConverter;
    private NotificationClient notificationClient;
    private Logger logger;

    @Before
    public void setUp() throws Exception {
        fileConverter = mock(FileConverter.class);
        notificationClient = mock(NotificationClient.class);
        logger = mock(Logger.class);
        fileConverterService = new FileConverterService(notificationClient, fileConverter, logger);
    }

    @Test
    public void shouldConvertFiles() {
        //given
        ConversionResult conversionResult = mock(ConversionResult.class);
        when(fileConverter.convert(SOURCE_FILENAME, DESTINATION_FILENAME)).thenReturn(conversionResult);
        when(conversionResult.timeout()).thenReturn(false);

        //when
        ConversionResult actualConversionResult = fileConverterService.convert(SOURCE_FILENAME, DESTINATION_FILENAME);

        //then
        assertEquals(actualConversionResult, conversionResult);
        verify(logger).info("Start converting app file " + SOURCE_FILENAME + " to " + DESTINATION_FILENAME);
        verify(logger).info("Stop converting app file " + SOURCE_FILENAME + " to " + DESTINATION_FILENAME);
    }

    @Test
    public void shouldFailConversionAndNotifyClient() {
        //given
        ConversionResult conversionResult = mock(ConversionResult.class);
        when(fileConverter.convert(SOURCE_FILENAME, DESTINATION_FILENAME)).thenReturn(conversionResult);
        when(conversionResult.timeout()).thenReturn(true);

        //when
        ConversionResult actualConversionResult = fileConverterService.convert(SOURCE_FILENAME, DESTINATION_FILENAME);

        //then
        assertEquals(actualConversionResult, conversionResult);
        verify(logger).info("Start converting app file " + SOURCE_FILENAME + " to " + DESTINATION_FILENAME);
        verify(logger).info("Stop converting app file " + SOURCE_FILENAME + " to " + DESTINATION_FILENAME);
        verify(notificationClient, times(1)).sendFailedNotification(conversionResult);
    }
}
