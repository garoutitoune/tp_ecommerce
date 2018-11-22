package fr.adaming.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ligneCommandes")
public class LigneCommande {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_li")
	private int id;
	private int qt;
	private double prix;
	public LigneCommande(int id, int qt, double prix) {
		super();
		this.id = id;
		this.qt = qt;
		this.prix = prix;
	}
	public LigneCommande(int qt, double prix) {
		super();
		this.qt = qt;
		this.prix = prix;
	}
	public LigneCommande() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", qt=" + qt + ", prix=" + prix + "]";
	}
	
	
	
	
	
}
