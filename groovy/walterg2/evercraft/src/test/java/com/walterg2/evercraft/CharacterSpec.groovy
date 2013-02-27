package com.walterg2.evercraft

import spock.lang.Specification

class CharacterSpec extends Specification {

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
}
