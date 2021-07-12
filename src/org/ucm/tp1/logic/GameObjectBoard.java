package org.ucm.tp1.logic;

import org.ucm.tp1.logic.GameObjects.GameObject;
import org.ucm.tp1.logic.GameObjects.GameObjectList;
import org.ucm.tp1.logic.GameObjects.IAttack;

public class GameObjectBoard {
	private GameObjectList objectList;
	
	public GameObjectBoard() {
		objectList = new GameObjectList();
	}

	public IAttack getAttackableInPosition(int i, int y) {
		return objectList.getAttackableInPosition(i,y);
	}

	public void attack() {
		objectList.attack();
		
	}

	public boolean positionIsEmpty(int i, int y) {
		return objectList.positionIsEmpty(i, y);
	}

	public void advanceVampires() {
		objectList.advanceVampires();
		
	}

	public void removeDeadObjects() {
		objectList.removeDeadObjects();
		
	}

	public Object getObjectInPosition(int i, int j) {
		return objectList.getObjectInPosition(i, j);
	}

	public void addNewObject(GameObject object) {
		objectList.addNewObject(object);
	}

	public void doGarlicPush() {
		objectList.doGarlicPush();
		
	}

	public void doLightFlash() {
		objectList.doLightFlash();
	}

	public String serialize() {
		return objectList.serialize();
	}

}
