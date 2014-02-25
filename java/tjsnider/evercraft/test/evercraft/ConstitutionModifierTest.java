package evercraft;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ConstitutionModifierTest {
	private int expectedHitPoints;
	PlayerCharacter subject;

	public ConstitutionModifierTest(Integer constitution, Integer expectedHitPoints) {
		this.subject = new PlayerCharacter();
		this.subject.setConstitutionScore(constitution);
		this.expectedHitPoints = expectedHitPoints;
	}
	
    @Parameters
    public static Collection<Object[]> data() {
    	return asList(new Object[][] {
    		{1,1}, {2,1}, {3,1}, {4,2}, {5,2}, 
            {6,3}, {7,3}, {8,4}, {9,4}, {10,5}, 
            {11,5}, {12,6}, {13,6}, {14,7}, {15,7}, 
            {16,8}, {17,8}, {18,9}, {19,9}, {20,10}
        });
    }
    
	@Test
	public void TestHitPoints() {
		assertEquals(expectedHitPoints, subject.getHitPoints());
	}

}
