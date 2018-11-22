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
import fr.adaming.service.ICategorieService;

@ManagedBean(name="caMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// transformation de l'association uml en java
	@EJB
	private ICategorieService caService;

	// declaration des attributs

	private Categorie categorie;
	private Gerant gerant;
	private UploadedFile file;

	HttpSession maSession;

	// constructeur vide
	public CategorieManagedBean() {
		this.categorie=new Categorie();
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
	

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String ajouterCategorie() {
		System.out.println(file.getSize());
		this.categorie.setPhoto(file.getContents());

		// j'ajoute la categorie et le gerant avec
		Categorie caOut = caService.addCategorie(categorie, gerant);
		if (caOut.getId() != 0) {
			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Categorie> liste = caService.getAllCategorie();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeCaSession", liste);
			return "accueil";

		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));
			
			return "ajouter";
		}
	}
	
	public String modifierCategorie() {
		int verif=caService.modifierCategorie(categorie);
		if(verif!=0) {
			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Categorie> liste = caService.getAllCategorie();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeCaSession", liste);
			return "accueil";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));
			
			return "ajouter";
		}
		
	}
	
	public String supprimerCategorie() {
		int verif=caService.supprimerCategorie(categorie);
		if(verif!=0) {
			// si tout c'est bien passé recupère la liste et je l'injecte
			List<Categorie> liste = caService.getAllCategorie();

			// mettre a jour la liste dans la session
			maSession.setAttribute("listeCaSession", liste);
			return "supprimer";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a échoué"));
			
			return "accueil";
		}
		
	}

}
