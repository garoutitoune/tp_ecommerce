package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {

	
	public Commande addCommande(Commande commande);
	public void delCommande(Commande commande);
	public Commande searchCommandeById(Commande commande);
	
	
	
}
