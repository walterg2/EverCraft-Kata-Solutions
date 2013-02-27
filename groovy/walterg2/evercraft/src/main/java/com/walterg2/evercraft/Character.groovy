package com.walterg2.evercraft

import groovy.transform.Canonical


@Canonical class Character {
	String name
	Alignment alignment
	int armorClass = 10
}
