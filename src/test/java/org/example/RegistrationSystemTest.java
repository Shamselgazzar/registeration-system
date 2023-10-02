package org.example;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {
    private static RegistrationSystem regSys;
    private static ByteArrayOutputStream outputStream;

    @BeforeAll
    public static void setUpBeforeAll() {
        regSys = new RegistrationSystem();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @BeforeEach
    public void setUpBeforeEach() {
        regSys.loggedInFlag = false;
    }

    @Nested
    class loginTesting{
        @Test
        public void loginSuccessful() throws NotValidMailException {
            String input = "5\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            Assertions.assertFalse(regSys.loggedInFlag);
            regSys.login("ahmed@gmail.com","1234");
            Assertions.assertTrue(regSys.loggedInFlag);
        }
        @Test
        public void loginWelcomeUserNameMessage() throws NotValidMailException {
            String input = "5\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            regSys.login("ahmed@gmail.com","1234");
            assertTrue(outputStream.toString().contains("Login successful. Welcome, Ahmed!"));
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
        public void testSignupSuccessful() {
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
        public void testInvalidEmail() {
            var email = "nnn";
            var password = "password";
            var name = "Test User";
            String input = name +"\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertThrows(NotValidMailException.class, () -> regSys.signup(email, password));
        }

    }

    @Nested
    class LoggedInTests {
        @BeforeEach
        public void setUp() {

            regSys.loggedInFlag = true;
            regSys.userRegistered =  regSys.users.get(0);

        }
        @Test
        public void testListUserAccounts()  {
            String input = "1\n5\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            assertTrue(outputStream.toString().contains("List of user accounts for Ahmed:"));
            assertFalse(outputStream.toString().contains("Invalid choice. Please try again."));
        }

        @Test
        public void depositCorrectAccount() {
            String input = "1\n2\n200\n1\n5\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            assertTrue( outputStream.toString().contains("Deposit successful"));
        }

        @Test
        public void depositWrongAccount()  {
            String input = "2\n200\n3\n5\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            assertTrue(outputStream.toString().contains(" not found."));
        }

        @Test
        public void withdrawalSuccessful()  {
            String input = "1\n3\n0\n1\n5\n"; // Simulate withdrawing from account 1 and then "Logout"
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            //assertEquals("Deposit successful", outputStream.toString());
            assertTrue(outputStream.toString().contains("Withdraw successful"));
        }

        @Test
        public void withdrawalFail()  {

            String input = "3\n200\n1\n5\n"; // Simulate withdrawing from account 1 and then "Logout"
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            assertTrue(outputStream.toString().contains("insufficient funds"));
        }

        @Test
        public void testOpenNewAccount()  {
            String input = "4\n5\n"; // Simulate opening a new account and then "Logout"
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            assertTrue(outputStream.toString().contains("new account is created"));
        }

        @Test
        public void testLogout() throws NotValidMailException {
            regSys.loggedInFlag = true; // Set loggedInFlag to true to simulate logged-in state
            String input = "5\n"; // Simulate choosing to logout
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            assertDoesNotThrow(() -> regSys.loggedIn());
            assertFalse(outputStream.toString().contains("Invalid choice. Please try again."));
        }

    }


}