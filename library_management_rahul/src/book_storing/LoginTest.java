package book_storing;

public class LoginTest {

}


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginTest {

	@Test
	void test() {
		Home ltest= new Home();
		
		boolean output= ltest.userLogin("Banish", "Banish");
		
		assertEquals(true,output);
	}

}
