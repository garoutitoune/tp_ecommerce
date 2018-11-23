package fr.adaming.dao;


import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

public interface IPanierDao {

	public Panier addProd(Panier panier, Produit produit); // op�ration +1 =>op�ration =x
	public Panier delProd(Panier panier, Produit produit); //op�ration -1
	public Panier delProd2(Panier panier, Produit produit); //supprimer une ligne de commande
	
	
}
