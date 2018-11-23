package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "proMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {

	// transformation de l'association uml en JAVA
	@EJB
	private IProduitService proService;

	// declaration des attributs
	private Produit produit;
	private Categorie categorie;
	private Gerant gerant;
	private UploadedFile file;

	HttpSession maSession;

	public ProduitManagedBean() {
		this.produit = new Produit();
		this.categorie = new Categorie();
	}

	// init
	@PostConstruct
	public void inti() {
		// recuperer la session en cours
		maSession = (HttpSession) FacesContext.getCurrentInstance().getCurrentInstance().getExternalContext()
				.getSession(false);

		// recuperer le formateur de la session
		this.gerant = (Gerant) maSession.getAttribute("gSession");
	}
	
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String ajouterProduit() {
		this.produit.setPhoto(file.getContents());
		Produit proOut = proService.addProduit(produit, categorie);

		if (proOut.getId() != 0) {
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = proService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));

			return "ajouterPro";
		}
	}
	
	
	public String supprimerProduit() {
		int verif=proService.deleteProduit(produit);
		
		if(verif!=0){
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = proService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));

			return "accueil";
		}
	}
	
	
	public String modifierProduit() {
		int verif=proService.modifierProduit(produit, categorie);
		if(verif!=0){
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = proService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "modifierPro";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�"));

			return "accueil";
		}
	}
}
