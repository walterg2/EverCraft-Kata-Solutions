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
public class StrengthCombatTest {
	private int roll;
	private Result expectedResult;
	private int expectedDamage;
	PlayerCharacter subject;
	PlayerCharacter antagonist;
	
	public StrengthCombatTest(Integer strength, Integer roll, Result hitResult, Integer damage) {
		this.subject = new PlayerCharacter();
		this.antagonist = new PlayerCharacter();
		this.subject.setStrengthScore(strength);
		this.roll = roll.intValue();
		this.expectedResult = hitResult;
		this.expectedDamage = damage.intValue();
	}
	
    @Parameters
    public static Collection<Object[]> data() {
            return asList(new Object[][] {
            	{1, 1, Fail, 0}, {1, 19, Success, 1}, {10, 10, Success, 1},
            	{17, 7, Success, 4}, {13, 20, Critical, 4}, {1, 20, Critical, 1}
            });
    }

	@Test
	public void testAttack() {
		assertEquals(expectedResult, subject.attack(antagonist, roll));
		assertEquals(expectedDamage, 5 - antagonist.getHitPoints());
	}

}
