package book_storing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Statement;

import org.junit.jupiter.api.Test;



class ConnectionTesting {

	@Test
	void test() {
		
		DbConnect db= new DbConnect(); //making an object db
		Statement output = (Statement) db.connection();
		Object expectedOutput = null;
		assertEquals(expectedOutput,output);
		
	}

}
