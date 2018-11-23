package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneServiceImpl implements ILigneService{
	
	@EJB
	ILigneCommandeDao lidao;

	@Override
	public LigneCommande addLigne(LigneCommande ligne, Commande commande, Produit produit) {
		ligne.setCommande(commande);
		ligne.setProduit(produit);
		return lidao.addLigne(ligne);
	}

	@Override
	public LigneCommande searchLigneById(LigneCommande ligne) {
		return lidao.searchLigneById(ligne);
	}

	@Override
	public void delLigne(LigneCommande ligne) {
		lidao.delLigne(ligne);
		
	}

}
