package evercraft;

import static evercraft.Result.Success;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import evercraft.classes.Fighter;

@RunWith(Parameterized.class)
public class ClassPermutationsTest {
	private PlayerCharacter subject;
	private PlayerCharacter antagonist;
	private int expectedHP;
	private int expectedHit;
	
	public ClassPermutationsTest(Class<CharacterClass> profession, Integer level, Integer hp, Integer hit) throws InstantiationException, IllegalAccessException {
		this.subject = new PlayerCharacter();
		this.subject.setCharacterClass(profession.newInstance());
		this.subject.addExperience(1000 * (level - 1));
		this.expectedHP = hp.intValue();
		this.expectedHit = hit.intValue();
		
		this.antagonist = new PlayerCharacter();
	}
	
    @Parameters
    public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
            		{Fighter.class, 1, 10, 9}, 
            		{Fighter.class, 2, 20, 8} 
            	});
    }
    
	@Test
	public void TestHitPoints() {
		assertEquals(expectedHP, subject.getHitPoints());
	}
	
	@Test
	public void TestHitRoll() {
		assertEquals(Success, subject.attack(antagonist, expectedHit));
	}

}
