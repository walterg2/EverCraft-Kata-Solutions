package com.walterg2.evercraft

import spock.lang.Specification

class CharacterSpec extends Specification {

	// Feature: Character has a name
	def "it should have a name as part of the constructor"() {
		when:
			def character = new Character('Bob')
		then:
			character.name == 'Bob'
	}
	
	def "it should allow to set a name on a character"() {
		when:
			def character = new Character()
			character.name = 'Bob'
		then:
			character.name == 'Bob'
	}
	
	// Feature: Character has an alignment
	def "it should allow me to set an alignment as part of the character"() {
		when:
			def character = new Character('Bob', 'GOOD')
		then:
			character.alignment == 'GOOD'
	}
	def "it should allow me to set an alignment on a character"() {
		when:
			def character = new Character()
			character.alignment = Alignment.GOOD
		then:
			character.alignment == 'GOOD'
	}
}
