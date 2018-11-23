package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.IPanierService;

@ManagedBean(name="paMB")
@SessionScoped
public class PanierManagedBean implements Serializable{

	//attributs
	private HttpSession maSession;
	private Panier panier;
	private Produit produit;
	
	//asso uml java
	private IPanierService paservice;

	public PanierManagedBean() {
		super();
	}
	//init
	@PostConstruct
	public void init() {
		// créer/récupérer une session pour le panier
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true);

		// recuperer le panier de la session
		this.panier =(Panier) maSession.getAttribute("paSession");
		if(this.panier==null) { //au cas ou le panier n'etait pas cree
			this.panier =new Panier(); //ajouter les panier à la session
			maSession.setAttribute("paSession", this.panier);
		}
	}	

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	
	public void addProd(Produit p) {
		paservice.addProd(this.panier, p);
	}
	
	
}
