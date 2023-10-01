
package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void checkSummation(){
        User user = new User();
        var s = user.sum(7 , 3);
        assertEquals(10, s);
    }

}