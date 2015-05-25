package lt.web.service.model;

public class FormaSablonas {
	/*
	 * IDK what to put here
	 */
	int id; //sablono id, kuriant nustatyti nereikia;
	String pavadinimas;//formai suteikiamas pavadinimas
	String duom;//formos sablono duomenys, kadangi backend su formos struktura/duomenimis nedirba viska turi but paversta i string, kurio max ilgis 10k;
	public String getPavadinimas() {
		return pavadinimas;
	}
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	public String getDuom() {
		return duom;
	}
	public void setDuom(String duom) {
		this.duom = duom;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
}
