package com.walterg2.evercraft

import spock.lang.Specification

class AttackSpec extends Specification {

	def attacker, defender

	void setup() {
		attacker = new Character()
		defender = new Character()
	}
	
	// Feature: Character Can Attack
	def "it should allow me to successfully attack another character when die roll is 11"() {
		when:
			def result = attacker.attack(defender, 11)
		then:
			result == true
	}
	
	def "it should not allow me to successfully attac another character when the die roll is 9"() {
		when:
			def result = attacker.attack(defender, 9)
		then:
			result == false
	}

	def "it should allow me to successfully attack another character when die roll is 10"() {
		when:
			def result = attacker.attack(defender, 10)
		then:
			result == true
	}
	
	// Feature: Character Can Damage
	def "it deals one point of damage to the defender when the attack is successful"() {
		when:
			def result = attacker.attack(defender, 11)
		then:
			result == true
			defender.hitPoints == 4
	}
}
