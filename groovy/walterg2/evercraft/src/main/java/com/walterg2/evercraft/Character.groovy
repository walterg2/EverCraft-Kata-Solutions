package com.walterg2.evercraft

import groovy.transform.Canonical


@Canonical class Character {
	String name
	Alignment alignment
	int armorClass = 10
	int hitPoints = 5
	
	boolean attack(Character defender, int roll) {
		defender.dealDamage(roll)
	}
	
	boolean dealDamage(int roll) {
		def returnVal = false
		if (roll >= this.armorClass) {
			hitPoints -= 1
			returnVal = true
		}
		
		returnVal
	}
	
}
