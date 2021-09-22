package sac;

public class Objet {
	private String name;
	private float value, weight;
	private boolean isInTheBag;
	private float ratio;

	public Objet(String nom, float valeur, float poids) {
		this.name = nom;
		this.value = valeur;
		this.weight = poids;
		this.isInTheBag = false;
		this.ratio = -1; // utilise pour l'algo glouton
	}

	public String toString() {
		return (this.isInTheBag ? "[x]" : "[ ]" ) + " ; \t" + this.value + " ; \t" + this.weight + " ; \t" + this.name + " ; "+this.ratio +"\n";
	}

	public String getName() {
		return name;
	}

	public float getValue() {
		return value;
	}

	public float getWeight() {
		return weight;
	}

	public float getRatio() { return ratio; }

	public boolean isInTheBag() {
		return isInTheBag;
	}

	public void setIsInTheBag() {
		isInTheBag = true;
	}

	public void setRatio(float ratio) { this.ratio = ratio; }
}
