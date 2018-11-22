package fr.adaming.service;

import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

public interface IPanierService {

	public Panier addProd(Panier panier, Produit produit);
	public Panier delProd(Panier panier, Produit produit);
}
