import com.purerangers.display.InputManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputManagerExceptionsTest {
    @Test
    @DisplayName("Given an input that is the wrong format, the InputManager should not throw a \"InputMismatchException\"")
    void inputManagerDoesNotThrowInputMismatchException() {
        float missmatch = 3.4511314f;
        Assertions.assertDoesNotThrow(() -> InputManager.convertTextualNumbersInString(String.valueOf(missmatch)));
    }
    @Test
    @DisplayName("Given a null input, the InputManager should not throw a \"NullPointerException\"")
    void inputManagerDoesNotThrowNullPointerException() {
        String nullString = null;
        Assertions.assertDoesNotThrow(() -> InputManager.convertTextualNumbersInString(String.valueOf(nullString)));
    }
}
