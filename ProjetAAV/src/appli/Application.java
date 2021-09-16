package appli;
import sac.*;

import java.io.FileNotFoundException;

public class Application {

	public static void main(String[] args) {
		SacADos sac;
		try {
			sac = new SacADos("objets.txt", 30);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(sac);
	}

}
