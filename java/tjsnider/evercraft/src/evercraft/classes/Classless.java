package evercraft.classes;

import evercraft.CharacterClass;

public class Classless implements CharacterClass {

	@Override
	public int attackBonus(final int level) {
		if (level < 1) {
			throw new IllegalArgumentException();
		}
		return level / 2;
	}

	@Override
	public int hitPointsPerLevel() {
		return 5;
	}

}
