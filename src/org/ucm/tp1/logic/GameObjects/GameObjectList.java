package org.ucm.tp1.logic.GameObjects;

import java.util.ArrayList;

public class GameObjectList {
	private ArrayList<GameObject> gameobjects;
	
	public GameObjectList() {
		gameobjects = new ArrayList<GameObject>();
	}

	public IAttack getAttackableInPosition(int i, int y) {
		for(GameObject object: gameobjects) {
			if(object.isInPosition(i,y)) {
				return object;
			}
		}
		return null;
	}

	public void attack() {
		for(GameObject object: gameobjects) {
			object.attack();
		}
	}

	public boolean positionIsEmpty(int i, int y) {
		for(GameObject object: gameobjects) {
			if(object.isInPosition(i, y)) return false;
		}
		return true;
	}

	public void addNewObject(GameObject object) {
		gameobjects.add(object);
	}

	public void advanceVampires() {
		for(GameObject object: gameobjects) {
			object.advanceOnePosition();
		}
	}

	public void removeDeadObjects() {
		for(int k = 0; k < gameobjects.size();k++) {
			if(gameobjects.get(k).isDead()) {
				gameobjects.remove(k);
				k--;
			}
		}		
	}

	public GameObject getObjectInPosition(int i, int j) {
		for(GameObject object: gameobjects) {
			if(object.isInPosition(i,j)) return object;
		}
		return null;
	}

	public void doGarlicPush() {
		for(GameObject object: gameobjects) {
			object.receiveGarlicPush();
		}
	}

	public void doLightFlash() {
		for(GameObject object: gameobjects) {
			object.receiveLightFlash();
		}
	}

	public String serialize() {
		String aux = "";
		for(GameObject object: gameobjects) {
			aux += object.serialize();
		}
		return aux;
	}

}
