package evercraft;

import static evercraft.Result.Critical;
import static evercraft.Result.Fail;
import static evercraft.Result.Success;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CharacterLevelTest {
	PlayerCharacter subject;
	private int expectedLevel;
	private int expectedHitPoints;

	public CharacterLevelTest(Integer experience, Integer expectedLevel, Integer constitution, Integer expectedHitPoints) {
		this.subject = new PlayerCharacter();
		this.subject.addExperience(experience);
		this.subject.setConstitutionScore(constitution);
		this.expectedLevel = expectedLevel;
		this.expectedHitPoints = expectedHitPoints;
	}
	
    @Parameters
    public static Collection<Object[]> data() {
    	return asList(new Object[][] {
    		{0,1,9,4}, {10,1,10,5}, {999,1,12,6},
    		{1000,2,9,8}, {1500,2,10,10}, {1999,2,12,12},
    		{2000,3,9,12}, {2500,3,10,15}, {2999,3,12,18}, 
    		{3000,4,9,16}, {3500,4,10,20}, {3999,4,12,24}, 
    		{4000,5,9,20}, {4500,5,10,25}, {4999,5,12,30}
        });
    }
    
	@Test
	public void testCharacterLevel() {
		assertEquals(expectedLevel, subject.getLevel());
	}
	
	@Test
	public void testHitPoints() {
		assertEquals(expectedHitPoints, subject.getHitPoints());
	}
	
	@Test
	public void testAttackRoll() {
		PlayerCharacter antagonist = new PlayerCharacter();
		int attackBonus = subject.getLevel() / 2;
		assertEquals(Success, subject.attack(antagonist, 10 - attackBonus));
		assertEquals(Fail, subject.attack(antagonist, 9 - attackBonus));
		assertEquals(Success, subject.attack(antagonist, 19));
		assertEquals(Critical, subject.attack(antagonist, 20));
	}
	
	@Test
	public void testDamage() {
		PlayerCharacter antagonist = new PlayerCharacter();
		assertEquals(Success, subject.attack(antagonist, 15));
		assertEquals(4, antagonist.getHitPoints());
	}

}
