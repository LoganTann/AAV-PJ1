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
		//
		Scanner sc= new Scanner(new File(chemin));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();

			String[] parsed = line.split(";");
			String objectName = parsed[0];
			float objectValue = Float.parseFloat(parsed[1]);
			float objectPrice = Float.parseFloat(parsed[2]);

			Objet object = new Objet(objectName, objectValue, objectPrice);
			objets.add(object);
		}
	}
	
	public String toString() {
		return "poids max : " + this.poidsMaximal + "kg";
	}
}
