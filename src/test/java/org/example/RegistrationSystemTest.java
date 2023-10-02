package org.example;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {
    private RegistrationSystem regSys;
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    public void setUp() {
        regSys = new RegistrationSystem();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Nested
    class loginTesting{
        @Test
        public void loginSuccessful() throws NotValidMailException {
            String input = "5\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            Assertions.assertFalse(regSys.loggedInFlag);
            regSys.login("ahmed@gmail.com","1234");
            //assertTrue(outputStream.toString().contains("Login successful. Welcome, Ahmed!"));
            Assertions.assertTrue(regSys.loggedInFlag);
        }

        @Test
        public void loginFailedWrongEmail() throws NotValidMailException {
            Assertions.assertFalse(regSys.loggedInFlag);
            regSys.login("invalid@gmail.com","1234");
            Assertions.assertFalse(regSys.loggedInFlag);
        }
        @Test
        public void loginFailedWrongPass() throws NotValidMailException {
            Assertions.assertFalse(regSys.loggedInFlag);
            regSys.login("ahmed@gmail.com","0000");
            Assertions.assertFalse(regSys.loggedInFlag);
        }
        @Test
        public void loginFailedBothWrong() throws NotValidMailException {
            Assertions.assertFalse(regSys.loggedInFlag);
            regSys.login("invalid@gmail.com","0000");
            Assertions.assertFalse(regSys.loggedInFlag);
        }

    }

    @Nested
    class signupTests{
        @Test
        public void testSignupSuccessful() throws NotValidMailException {
            var email = "new@gmail.com";
            var password = "password";
            var name = "Test User";

            String input = name +"\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));



            assertDoesNotThrow(() -> regSys.signup(email,password));
            assertEquals(email, regSys.users.get(regSys.users.size() - 1).getEmail());
            assertEquals(password, regSys.users.get(regSys.users.size()-1).getPassword());
            assertEquals(name, regSys.users.get(regSys.users.size()-1).getName());
            assertTrue(outputStream.toString().contains("user signed up successfully."));
        }


        @Test
        public void testInvalidEmail() throws NotValidMailException {
            var email = "nnn";
            var password = "password";
            var name = "Test User";
            String input = name +"\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThrows(NotValidMailException.class, () -> regSys.signup(email, password));
        }

    }


}