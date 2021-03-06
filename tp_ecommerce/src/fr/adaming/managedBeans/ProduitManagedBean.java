package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class ProduitManagedBean implements Serializable {

	// transformation de l'association uml en JAVA
	@EJB
	private IProduitService proService;

	// declaration des attributs
	private Produit produit;
	private Categorie categorie;
	private Gerant gerant;
	private UploadedFile file;
	private List<Produit> listeProds;
	private boolean indice;

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

	public List<Produit> getListeProds() {
		return listeProds;
	}

	public void setListeProds(List<Produit> listeProds) {
		this.listeProds = listeProds;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	//m�thodes
	
	

	public void listeprodsmeth(int i) throws IOException {
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
		//recup la liste des produits pour 1 cat�g
		this.categorie.setId(i);
		this.listeProds=proService.getAllProduitByCat(new Produit(), this.categorie);
		maSession.setAttribute("listeProds1Categ", this.listeProds);
//		return "voirProds1Categ.xhtml";
		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
	}
	
	public String produitByCat() {
		//recuperer la liste de produit selon la categorie
		List<Produit> listePro=proService.getAllProduitByCat(produit, categorie);
		
		indice=true;
		
		maSession.setAttribute("listeProCatSession", listePro);
		return "accueil";
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

			return "supprimerPro";
		}
	}
	
	
	public String modifierProduit() {
		this.produit.setPhoto(file.getContents());
		int verif=proService.modifierProduit(produit, categorie);
		if(verif!=0){
			// si tout c'est bien pass� recup�re la liste et je l'injecte
			List<Produit> liste = proService.getAllProduit();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeProSession", liste);
			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a �chou�"));

			return "modifierPro";
		}
	}
	
	public String modifierLienProduit() {
		return "modifierPro";
	}
	
	public String rechercherByDesignation() {
		List<Produit> listeOut=proService.getProduitByDes(produit, categorie);
		if(listeOut!=null) {
			maSession.setAttribute("listeProRechSession", listeOut);
			
			return "recherchePro";
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�"));
			return "recherchePro";
		}
	}
}
