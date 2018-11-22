package fr.adaming.dao;


import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

public interface IPanierDao {

	public Panier addProd(Panier panier, Produit produit); // opération +1 =>opération +x
	public Panier delProd(Panier panier, Produit produit); //opération -1
	//supprimer une ligne de commande?
	
	
	
}
