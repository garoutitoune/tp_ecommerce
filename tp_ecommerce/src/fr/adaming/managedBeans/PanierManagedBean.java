package fr.adaming.managedBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.LigneCommande;
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
	@EJB
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

//		 recuperer le panier de la session
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
	
	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public void addProd() throws IOException {
		this.panier=paservice.addProd(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmation","Le produit a été ajouté au panier."));
	}
	
	public void delProd() throws IOException {
		this.panier=paservice.delProd(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("voirProds1Categ.xhtml");
	}
	
	public void del2Prod() {
		this.panier=paservice.delProd(this.panier ,this.produit);
		//actualiser le panier de la session
		maSession.setAttribute("paSession", this.panier);
	}
	
	
}
