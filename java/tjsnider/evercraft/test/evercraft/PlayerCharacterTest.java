package evercraft;

import static evercraft.Alignment.Evil;
import static evercraft.Alignment.Good;
import static evercraft.Alignment.Neutral;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PlayerCharacterTest {
	PlayerCharacter subject;
	
	@Before
	public void setUp() {
		subject = new PlayerCharacter();
	}

	@Test
	public void testCharacterName() {
		assertNull(subject.getName());
		subject.setName("Bob");
		assertEquals("Bob", subject.getName());
	}

	@Test
	public void testCharacterAlignment() {
		assertNull(subject.getAlignment());
		subject.setAlignment(Good);
		assertEquals(Good, subject.getAlignment());
	}
	
	@Test
	public void testAlignmentList() {
		assertArrayEquals(Alignment.values(), new Alignment[] { Good, Evil, Neutral});
	}
	
	@Test
	public void testArmorClass() {
		assertEquals(10, subject.getArmorClass());
	}
	
	@Test
	public void testHitPoints( ){
		assertEquals(5, subject.getHitPoints());
	}
	
	@Test
	public void testAttack() {
		PlayerCharacter antagonist = new PlayerCharacter();
		assertEquals(10, antagonist.getArmorClass());
		assertEquals(Result.Fail, subject.attack(antagonist, 9));
		assertEquals(Result.Success, subject.attack(antagonist, 11));
		assertEquals(Result.Success, subject.attack(antagonist, 10));
	}
	
	@Test
	public void testAttackDamage() {
		PlayerCharacter antagonist = new PlayerCharacter();
		assertEquals(5, antagonist.getHitPoints());
		assertEquals(Result.Fail, subject.attack(antagonist, 5));
		assertEquals(5, antagonist.getHitPoints());
		
		assertEquals(Result.Success, subject.attack(antagonist, 12));
		assertEquals(4, antagonist.getHitPoints());
		
		assertEquals(Result.Critical, subject.attack(antagonist, 20));
		assertEquals(2, antagonist.getHitPoints());
		
		subject.attack(antagonist, 20);
		assertEquals(0, antagonist.getHitPoints());
		assertTrue(antagonist.isDead());
		
		subject.attack(antagonist, 15);
		assertEquals(-1, antagonist.getHitPoints());
		assertTrue(antagonist.isDead());
	}
	
	@Test
	public void testAbilityScores() {
		assertEquals(10, subject.getStrengthScore());
		assertEquals(10, subject.getDexterityScore());
		assertEquals(10, subject.getConstitutionScore());
		assertEquals(10, subject.getWisdomScore());
		assertEquals(10, subject.getIntelligenceScore());
		assertEquals(10, subject.getCharismaScore());		
	}
	
	@Test
	public void testExperienceGain() {
		PlayerCharacter antagonist = new PlayerCharacter();
		
		assertEquals(0, subject.getExperience());

		subject.attack(antagonist, 1);
		assertEquals(0, subject.getExperience());
		
		subject.attack(antagonist, 10);
		assertEquals(10, subject.getExperience());
		
		subject.attack(antagonist, 20);
		assertEquals(20, subject.getExperience());
	}
	
	@Test
	public void testStartingCharacterLevel() {
		assertEquals(1, subject.getLevel());
	}
	
}
