package evercraft;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DexterityModifierTest {
	private int expectedArmorClass;
	PlayerCharacter subject;

	public DexterityModifierTest(Integer dexterity, Integer expectedArmorClass) {
		this.subject = new PlayerCharacter();
		this.subject.setDexterityScore(dexterity);
		this.expectedArmorClass = expectedArmorClass;
	}
	
    @Parameters
    public static Collection<Object[]> data() {
    	return asList(new Object[][] {
    		{1,5}, {2,6}, {3,6}, {4,7}, {5,7}, 
            {6,8}, {7,8}, {8,9}, {9,9}, {10,10}, 
            {11,10}, {12,11}, {13,11}, {14,12}, {15,12}, 
            {16,13}, {17,13}, {18,14}, {19,14}, {20,15}
        });
    }
    
	@Test
	public void TestArmor() {
		assertEquals(expectedArmorClass, subject.getArmorClass());
	}

}
