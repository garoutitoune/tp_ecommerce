package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.CommandeServiceImpl;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneService;
import fr.adaming.service.LigneServiceImpl;

@Stateless
public class PanierDaoImpl implements IPanierDao{

	
	@Override
	public Panier addProd(Panier panier, Produit produit) {

		
		int verif=0; 
		//verifier si le produit est déjà dans le panier
		List<LigneCommande> liste=panier.getListeLignes();
		
		LigneCommande li=new LigneCommande();
		
		for (int i = 0; i < liste.size(); i++) {
			li=liste.get(i);
			if(li.getProduit().getDesignation()==produit.getDesignation()) {
				li.setQt(li.getQt()+1); //si le produit est trouvé dans le panier, ajouter
				li.setPrix(produit.getPrix()*li.getQt()); //actualiser le prix de la ligne de commande
				verif=1;
			}
		}
		if(verif==0) { //si le produit n'a pas été trouvé dans le panier, le rajouter
			li=new LigneCommande(1, produit.getPrix());
			li.setProduit(produit);
			//ajouter la nouvelle ligne de commande au panier
			liste.add(li);
		}
		
		
		return panier;
	}

	@Override
	public Panier delProd(Panier panier, Produit produit) {
		//on suppose que le produit est dans le panier et qt>1
		List<LigneCommande> liste=panier.getListeLignes();
		
		LigneCommande li=new LigneCommande();
		
		for (int i = 0; i < liste.size(); i++) {
			li=liste.get(i);
			if(li.getProduit().getDesignation()==produit.getDesignation()) {
				li.setQt(li.getQt()-1); //si le produit est trouvé dans le panier, soustraire
				//si la qt est =0, retirer du panier
				if(li.getQt()==0) {
					liste.remove(li);
				}else {
					li.setPrix(produit.getPrix()*li.getQt()); //actualiser le prix de la ligne de commande
				}
				
			}
		}
		return panier;
	}

	@Override
	public Panier delProd2(Panier panier, Produit produit) {
		//on suppose que le produit est dans le panier et qt>1
		List<LigneCommande> liste=panier.getListeLignes();
		
		LigneCommande li=new LigneCommande();
		
		for (int i = 0; i < liste.size(); i++) {
			li=liste.get(i);
			if(li.getProduit().getDesignation()==produit.getDesignation()) {
				liste.remove(i);//si le produit est trouvé dans le panier, supprimer sa ligne de commande
			}
		}
		return panier;		
	}

	

	@Override
	public void savePanier(Panier panier, Commande commande) {
		
//		//lier les objets en java
//		for (LigneCommande li : panier.getListeLignes()) {
//			li.setCommande(commande);
//		}
		
		ILigneService liservice=new LigneServiceImpl();
		ICommandeService coservice=new CommandeServiceImpl();
		
		//ajouter les lignes de commande
		for (LigneCommande li : panier.getListeLignes()) {
			liservice.addLigne(li, commande, li.getProduit());
		}
		//ajouter la commande
		coservice.addCommande(commande, commande.getClient());
		
//		//persister la classe maitre
//		for (LigneCommande li : panier.getListeLignes()) {
//			em.persist(li);
//		}
//		//persister l'esclave
//		em.persist(commande);
		
	}

	
	
	
	
	
	
	
}
