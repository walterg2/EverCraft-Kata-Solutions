package evercraft.classes;

import evercraft.CharacterClass;

public class Fighter implements CharacterClass {

	@Override
	public int attackBonus(final int level) {
		if (level < 1) {
			throw new IllegalArgumentException();
		}
		return level;
	}

	@Override
	public int hitPointsPerLevel() {
		return 10;
	}

}
