import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

public class LogAnalizerTest {

    private LogAnalizer logAnalizer = new LogAnalizer();

    @Test
    void checkValidLogFileInvalid() {
        Assertions.assertThrows(InvalidParameterException.class, () -> {
            logAnalizer.analyseLogs("InvalidPath");
        });
    }

}
