package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeDao {

	public LigneCommande addLigne(LigneCommande ligne);
	public LigneCommande searchLigneById(LigneCommande ligne);
	public void delLigne(LigneCommande ligne);
	
	
}
