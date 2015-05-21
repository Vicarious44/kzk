package lt.web.service.model;

import java.util.Vector;

public class FormaSablonas {
	/*
	 * IDK what to put here
	 */
	String pavadinimas;
	Vector<String> klausimai;
	public String getPavadinimas() {
		return pavadinimas;
	}
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	public Vector<String> getKlausimai() {
		return klausimai;
	}
	public void setKlausimai(Vector<String> klausimai) {
		this.klausimai = klausimai;
	}
}
