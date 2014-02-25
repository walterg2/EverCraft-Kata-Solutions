package evercraft;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AbilityRangeTest {
	private int input;
	private int expected;
	
	public AbilityRangeTest(Integer input, Integer expected) {
		this.input = input.intValue();
		this.expected = expected.intValue();
	}
	
    @Parameters
    public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {{1,-5}, {2,-4}, {3,-4}, {4,-3}, {5,-3}, 
            									 {6,-2}, {7,-2}, {8,-1}, {9,-1}, {10,0}, 
            									 {11,0}, {12,1}, {13,1}, {14,2}, {15,2}, 
            									 {16,3}, {17,3}, {18,4}, {19,4}, {20,5}});
    }
    
	@Test
	public void TestRange() {
		assertEquals(expected, Ability.valueOf(input).getModifier());
	}

}
