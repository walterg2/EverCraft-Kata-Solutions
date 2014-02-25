package evercraft;

import static evercraft.Result.Critical;
import static evercraft.Result.Fail;
import static evercraft.Result.Success;
import evercraft.classes.Classless;

public class PlayerCharacter {
	private static final int baseArmorClass = 10;
	private static final int baseLevel = 1;
	
	private String name;
	private CharacterClass characterClass = new Classless();
	private Alignment alignment;
	private int armorClass = baseArmorClass;
	private int hitPoints = characterClass.hitPointsPerLevel();
	private Ability strength = Ability.valueOf(10);
	private Ability dexterity = Ability.valueOf(10);
	private Ability constitution = Ability.valueOf(10);
	private Ability wisdom = Ability.valueOf(10);
	private Ability intelligence = Ability.valueOf(10);
	private Ability charisma = Ability.valueOf(10);
	private int experience = 0;
	private int level = baseLevel;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public Alignment getAlignment() {
		return alignment;
	}
	
	public void setAlignment(final Alignment alignment) {
		this.alignment = alignment;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public int getStrengthScore() {
		return strength.getScore();
	}

	public void setStrengthScore(int score) {
		strength = Ability.valueOf(score);
	}

	public int getDexterityScore() {
		return dexterity.getScore();
	}

	public void setDexterityScore(int score) {
		dexterity = Ability.valueOf(score);
		recalculateArmorClass();
	}

	public int getConstitutionScore() {
		return constitution.getScore();
	}

	public void setConstitutionScore(int score) {
		constitution = Ability.valueOf(score);
		recalculateHitPoints();
	}

	public int getWisdomScore() {
		return wisdom.getScore();
	}

	public void setWisdomScore(int score) {
		wisdom = Ability.valueOf(score);
	}

	public int getIntelligenceScore() {
		return intelligence.getScore();
	}

	public void setIntelligenceScore(int score) {
		intelligence = Ability.valueOf(score);
	}

	public int getCharismaScore() {
		return charisma.getScore();
	}

	public void setCharismaScore(int score) {
		charisma = Ability.valueOf(score);
	}

	public int getStrengthModifier() {
		return strength.getModifier();
	}

	public int getDexterityModifier() {
		return dexterity.getModifier();
	}

	public int getConstitutionModifier() {
		return constitution.getModifier();
	}

	public int getWisdomModifier() {
		return wisdom.getModifier();
	}

	public int getIntelligenceModifier() {
		return intelligence.getModifier();
	}

	public int getCharismaModifier() {
		return charisma.getModifier();
	}

	public int getExperience() {
		return experience;
	}

	public int getLevel() {
		return this.level;
	}
	
	private void recalculateArmorClass() {
		this.armorClass = baseArmorClass + this.dexterity.getModifier();
	}
	
	private int hitPointsPerLevel( ){
		return Math.max(1, characterClass.hitPointsPerLevel() + constitution.getModifier());
	}

	private void recalculateHitPoints() {
		this.hitPoints = this.level * hitPointsPerLevel();
	}
	
	private void recalculateLevel() {
		final int startingLevel = level;
		level = baseLevel + experience / 1000;
		final int levelIncrease = level - startingLevel;
		
		this.hitPoints += levelIncrease * hitPointsPerLevel();
	}

	public Result attack(PlayerCharacter antagonist, int roll) {
		final Result result = antagonist.takeHit(roll, this.strength.getModifier(), characterClass.attackBonus(this.level));
		if(result != Result.Fail) {
			experience += 10;
		}
		return result;
	}

	/**
	 * returns indication of successful hit
	 * 
	 * SIDE EFFECT!!!! modifies hitpoint on successful hit
	 * @param roll
	 * @return
	 */
	private Result takeHit(int roll, int strengthMod, int levelMod) {
		if (roll == 20) {
			hitPoints -= Math.max(1, 2*(1+strengthMod));
			return Critical;
		} else if ((roll + strengthMod + levelMod) >= this.armorClass) {
			hitPoints -= Math.max(1, 1+strengthMod);
			return Success;
		} else {
			return Fail;
		}
	}

	public boolean isDead() {
		return hitPoints <= 0;
	}

	public void addExperience(int experience) {
		this.experience += experience;
		recalculateLevel();
	}

	public void setCharacterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
		recalculateHitPoints();
	}

}
