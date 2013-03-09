package com.walterg2.evercraft

import spock.lang.Specification

class CharacterSpec extends Specification {

	def character
	
	void setup() {
		character = new Character('Bob', Alignment.GOOD)
	}

		// Feature: Character has a name
	def "it should have a name as part of the constructor"() {
		when:
			def name = character.name
		then:
			name == 'Bob'
	}
	
	def "it should allow to set a name on a character"() {
		when:
			def character = new Character()
			character.name = 'Bob'
		then:
			character.name == 'Bob'
	}
	
	// Feature: Character has an alignment
	def "it should allow me to set a Good alignment as part of the character"() {
		when:
			character = new Character('Bob', Alignment.GOOD)
		then:
			character.alignment == Alignment.GOOD
	}

	def "it should allow me to set a Good alignment on a character"() {
		when:
			def character = new Character()
			character.alignment = Alignment.GOOD
		then:
			character.alignment == Alignment.GOOD
	}

	def "it should allow me to set a Neutral alignment as part of the character"() {
		when:
			def character = new Character('Bob', Alignment.NEUTRAL)
		then:
			character.alignment == Alignment.NEUTRAL
	}
	
	def "it should allow me to set a Neutral alignment on a character"() {
		when:
			def character = new Character()
			character.alignment = Alignment.NEUTRAL
		then:
			character.alignment == Alignment.NEUTRAL
	}
	
	def "it should allow me to set an Evil alignment as part of the character"() {
		when:
			def character = new Character('Bob', Alignment.EVIL)
		then:
			character.alignment == Alignment.EVIL
	}

	def "it should allow me to set an Evil alignment on a character"() {
		when:
			def character = new Character()
			character.alignment = Alignment.EVIL
		then:
			character.alignment == Alignment.EVIL
	}
	
	// Feature: Armor Class & Hit Points
	def "it should have an armor class defaulted to 10"() {
		when:
			def armorClass = character.armorClass
		then:
			armorClass == 10
	}
	
	def "it should have hit points defaulted to 5"() {
		when:
			def hitPoints = character.hitPoints
		then:
			hitPoints == 5
	}
	
	// Feature: Character Can Attack
	def "it should allow me to successfully attack another character when die roll is 11"() {
		given:
			def attacker = new Character()
			def defender = new Character()
		when:
			def result = attacker.attack(defender, 11)
		then:
			result == true
	}
	
	def "it should not allow me to successfully attac another character when the die roll is 9"() {
		given:
		def attacker = new Character()
		def defender = new Character()
	when:
		def result = attacker.attack(defender, 9)
	then:
		result == false
	}

	def "it should allow me to successfully attack another character when die roll is 10"() {
		given:
			def attacker = new Character()
			def defender = new Character()
		when:
			def result = attacker.attack(defender, 10)
		then:
			result == true
	}
	
}
