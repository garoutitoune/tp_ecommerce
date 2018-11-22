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

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;
import fr.adaming.service.ICategorieService;

@ManagedBean
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// transformation de l'association uml en java
	@EJB
	private ICategorieService caService;

	// declaration des attributs

	private Categorie categorie;
	private Gerant gerant;

	HttpSession maSession;

	// constructeur vide
	public CategorieManagedBean() {
		super();
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

	public String ajouterCategorie() {

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
		return null;
	}

}
