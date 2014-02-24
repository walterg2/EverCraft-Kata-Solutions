package evercraft;

import org.junit.Test;

public class AbilityOutOfRangeTest {

	@Test(expected = IndexOutOfBoundsException.class)
	public void TestAssignZero() {
		@SuppressWarnings("unused")
		Ability bad = Ability.valueOf(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestAssignTooHigh() {
		@SuppressWarnings("unused")
		Ability bad = Ability.valueOf(21);
	}
}
