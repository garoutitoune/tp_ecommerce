package fr.adaming.service;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.dao.LigneCommandeDaoImpl;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
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

	@Override
	public void savePanier(Panier panier, Client client) {
		Commande commande=new Commande(new Date());
		commande.setClient(client);
		lidao.savePanier(panier, commande);
		
	}

}
