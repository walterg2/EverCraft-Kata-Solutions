package com.blizzcon.evercraft;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static com.blizzcon.evercraft.CharacterTest.CharacterBuilder.*;

public class CharacterTest {

	@Test
	public void aCharacterIsIdentifiable() {
		EvercraftCharacter momo = aCharacter().withName("momo").build();
		assertEquals("momo", momo.getName());
	}

	@Test
	public void aCharacterHasAnAlignment() {
		assertEquals(Alignment.GOOD, aCharacter().withAlignment(Alignment.GOOD).build().getAlignment());
	}

	@Test
	public void aCharacterHasDefaultArmorOf10() {
		assertEquals(10, anyCharacter().getArmor());
	}

	@Test
	public void aCharacterHasDefaultHitPointsOf5() {
		assertEquals(5, anyCharacter().getHitPoints());
	}

	@Test
	public void aCharacterGetsAttackedButSavedByArmor() {
		EvercraftCharacter victim = anyCharacter();
		int currentHitPoints = victim.getHitPoints();
		int dieResult = 9;

		victim.getsAttacked(dieResult);

		assertEquals(currentHitPoints, victim.getHitPoints());
	}

	@Test
	public void aCharacterLoosesOneHitPointOnHit() {
		EvercraftCharacter victim = aCharacter().withArmor(10).withHitPoints(5).build();
		int currentHitPoints = victim.getHitPoints();
		int victimArmor = victim.getArmor();
		victim.getsAttacked(victimArmor);
		assertEquals(currentHitPoints - 1, victim.getHitPoints());
	}
	
	@Test
	public void aCharacterLooseTwoHitPointsOnCriticalHit() {
		EvercraftCharacter victim = aCharacter().withHitPoints(8).build();
		victim.getsAttacked(20);
		assertEquals(6, victim.getHitPoints());
	}
	
	@Test
	public void aCharacterIsKilledWhenHitPointsGetsToZero() {
		EvercraftCharacter victim = aCharacter().withHitPoints(1).build();
		victim.getsAttacked(victim.getArmor());
		assertTrue(victim.isDead());
	}

	@Test
	public void aCharacterEarnsExperiencePointsWhenAttackingAnEnemy() {
		EvercraftCharacter attacker = anyCharacter();
		EvercraftCharacter victim = anyCharacter();
		int currentAttackerExperiencePoints = attacker.experiencePoints();
		int attackerDieRoll = victim.getArmor();
		
		victim.getsAttacked(attacker, attackerDieRoll);
		
		assertEquals(currentAttackerExperiencePoints + 10, attacker.experiencePoints());
	}

	public static class CharacterBuilder {

		private String name = "someName";
		private Alignment alignment;
		private int hitPoints = 5;
		private int armor = 10;

		public EvercraftCharacter build() {
			return new EvercraftCharacter(name, alignment, armor, hitPoints);
		}

		public CharacterBuilder withHitPoints(int hitPoints) {
			this.hitPoints = hitPoints;
			return this;
		}

		public CharacterBuilder withArmor(int armor) {
			this.armor = armor;
			return this;
		}

		public CharacterBuilder withAlignment(Alignment alignment) {
			this.alignment = alignment;
			return this;
		}

		public CharacterBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public static CharacterBuilder aCharacter() {
			return new CharacterBuilder();
		}

		public static EvercraftCharacter anyCharacter() {
			return new CharacterBuilder().build();
		}

	}
}
