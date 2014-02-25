package evercraft;

public class Ability {
	private static final Ability[] FLYWEIGHT = new Ability[21];

	private int score;
	private int modifier;

	private Ability(final int score) {
		this.score = score;
		this.modifier = (int)Math.floor((score - 10.0) / 2.0);
	}

	public int getScore() {
		return score;
	}

	public int getModifier() {
		return modifier;
	}

	public static Ability valueOf(int abilityScore) {
		if (abilityScore < 1 || abilityScore > 20) {
			throw new IndexOutOfBoundsException();
		}
		if (FLYWEIGHT[abilityScore] == null) {
			FLYWEIGHT[abilityScore] = new Ability(abilityScore);
		}
		return FLYWEIGHT[abilityScore];
	}

}
