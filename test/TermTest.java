import static org.junit.Assert.*;
/**
 * @author Denise Doyle 
 * Term test case, checks for expected output in one passing and other failing cases(null pointer, illegal arguments)
 * Passing?
 */


import org.junit.Test;

public class TermTest {
	
	@Test
	public void TermConstructorTest(){
		Term test = new Term(10, "testTerms");
		assertEquals(10, test.weight, 0);
		assertEquals("testTerms", test.query);
	}

	@Test(expected = NullPointerException.class)
	public void nullTest(){
		new Term(0,null);
	}
	@Test(expected = NullPointerException.class)
	public void emptyQueryTest(){
		new Term(0,"");
	}

	@Test(expected = NullPointerException.class)
	public void nullWeightTest(){
		new Term((Long) null,"null weight");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void lessThanZeroTest(){
		new Term(-1, "illegal argument");
	}

}