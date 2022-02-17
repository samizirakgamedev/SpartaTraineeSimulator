import com.purerangers.InputManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class InputManagerTests {
    Scanner scanner = new Scanner(System.in);

    @Test
    @DisplayName("Given an invalid input, the handleInputExceptions method will not throw a \"InputMismatchException\"")
    public void getDoubleDoesNotThrowInputMismatchException(){
        inTest();
        //Assertions.assertDoesNotThrow(InputManager::getDouble);
    }
    public void inTest(){
        var data = "Hello, World!\r\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.out.println(scanner.nextLine());
        } finally {
            System.setIn(stdin);
        }
    }
}
