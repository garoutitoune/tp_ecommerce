package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;


import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public List<Produit> getAllProduit();
	
	public Produit addProduit(Produit pro);
	
	public int deleteProduit(Produit pro);
	
	public int modifierProduit(Produit pro);
	
	public List<Produit> getAllProduitCat(Produit pro);
	
	public Produit getProduitById(Produit pro);
	
	public List<Produit> getProduitByDescr(Produit pro);

}
