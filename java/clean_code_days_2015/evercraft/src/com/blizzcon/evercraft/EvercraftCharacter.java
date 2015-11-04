package com.blizzcon.evercraft;

public class EvercraftCharacter {

	private static final int CRITICAL_HIT = 20;
	private String name;
	private Alignment alignment;
	private int armor = 10;
	private int hitPoints = 5;
	private int experiencePoints = 0;

	public EvercraftCharacter(String name, Alignment alignment) {
		this.name = name;
		this.alignment = alignment;
	}

	protected EvercraftCharacter(String name, Alignment alignment, int armor, int hitPoints) {
		this.name = name;
		this.alignment = alignment;
		this.armor = armor;
		this.hitPoints = hitPoints;
	}

	public String getName() {
		return name;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public int getArmor() {
		return this.armor;
	}

	public int getHitPoints() {
		return this.hitPoints;
	}

	public boolean getsAttacked(int dieResult) {
		boolean isHit = dieResult >= armor;
		if (isHit) {
			if (dieResult == CRITICAL_HIT) {
				hitPoints--;
			}
			hitPoints--;
		}
		return isHit;
	}

	public boolean isDead() {
		return hitPoints == 0;
	}

	public int experiencePoints() {
		return experiencePoints ;
	}

	public void getsAttacked(EvercraftCharacter attacker, int attackerDieRoll) {
		getsAttacked(armor);
		attacker.experiencePoints +=10;
	}
}
