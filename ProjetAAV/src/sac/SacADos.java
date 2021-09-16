package sac;

import java.util.ArrayList;

public class SacADos {
	private float poidsMaximal;
	private ArrayList<Objet> objets;
	
	public SacADos() {
		poidsMaximal = 30;
		objets = new ArrayList<>();
	}
	
	public SacADos(String chemin, float poidsMaximal) {
		this.poidsMaximal = poidsMaximal;
	}
	
	public String toString() {
		return "poids max : " + this.poidsMaximal + "kg";
	}
}
