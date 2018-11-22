package fr.adaming.service;

import javax.ejb.EJB;

import fr.adaming.dao.IPanierDao;
import fr.adaming.dao.PanierDaoImpl;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

public class PanierServiceImpl implements IPanierService{
	
	// transformation de l'association UML en JAVA
	private IPanierDao pdao=new PanierDaoImpl();		

	@Override
	public Panier addProd(Panier panier, Produit produit) {
		return pdao.addProd(panier, produit);
	}

	@Override
	public Panier delProd(Panier panier, Produit produit) {
		return pdao.delProd(panier, produit);
	}

}
