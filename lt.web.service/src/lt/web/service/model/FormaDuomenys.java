package lt.web.service.model;

public class FormaDuomenys {
	int id; // formos id, kuriant rnustatyti nereikia, pasiimant ji bus nustatoma i id kuriuo per rest'a galima ja pasiekti. Reikalingas norint pasiekti jau pateiktus duomenis.
	int t_id; // sablono id kuriam priklauso sie dumenys, kuriant rnustatyti nereikia. Reikalingas pasiimti sablona;
	String data; //formos uzpildymo duomenys, kadangi backend su formos struktura/duomenimis nedirba viska turi but paversta i string, kurio max ilgis 10k;
	String owner;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setOwner(String id){
		this.owner = id;
	}
	public String getOwner(){
		return owner;
	}
}
