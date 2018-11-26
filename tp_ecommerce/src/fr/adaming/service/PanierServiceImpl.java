package fr.adaming.service;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.dao.IPanierDao;
import fr.adaming.dao.LigneCommandeDaoImpl;
import fr.adaming.dao.PanierDaoImpl;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

@Stateful
public class PanierServiceImpl implements IPanierService{
	
	// transformation de l'association UML en JAVA
	private IPanierDao pdao=new PanierDaoImpl();		

	@Override
	public Panier addProd( Panier panier, Produit produit) {
		return pdao.addProd(panier, produit);
	}

	@Override
	public Panier delProd(Panier panier, Produit produit) {
		return pdao.delProd(panier, produit);
	}

	@Override
	public Panier delProd2(Panier panier, Produit produit) {
		return pdao.delProd2(panier, produit);
	}

	@Override
	public void savePanier(Panier panier, Client client) {
		Commande commande=new Commande(new Date());
		commande.setClient(client);
//		ILigneCommandeDao lidao=new LigneCommandeDaoImpl();
//		lidao.savePanier(panier, commande);
		pdao.savePanier(panier, commande);
		
	}

}
