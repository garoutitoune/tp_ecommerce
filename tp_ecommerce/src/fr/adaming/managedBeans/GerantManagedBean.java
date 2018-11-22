package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IGerantService;

@ManagedBean(name="gMB")
@RequestScoped
public class GerantManagedBean implements Serializable{
	
	//transformation de l'association UML en JAVA 
	@EJB
	private IGerantService gService;
	
	@EJB
	private ICategorieService caService;
	
	// declaration des attributs
	private Gerant gerant;
	private List<Categorie> listeCategorie;
	private List<Produit> listeProduit;
	
	//constructeur vide
	public GerantManagedBean() {
		this.gerant=new Gerant();
		
	}


	public Gerant getGerant() {
		return gerant;
	}


	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}


	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}


	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}


	public List<Produit> getListeProduit() {
		return listeProduit;
	}


	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}
	
	//les autres methodes
	public String seConnecter() {

		try {
			// aller chercher le formateur dans la bd
			Gerant gOut=gService.isExist(gerant);
			

			// recuperer les categories
			this.listeCategorie=caService.getAllCategorie();
			// recuperer les produits
		
			// ajouter le formateur dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("gSession", gOut);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCaSession", this.listeCategorie);
			

			return "accueil";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le login ou mdp n'est pas valide"));

		}

		return "login";
	}

	public String seDeconnecter() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "login";
	}
	
	
	
	

}
