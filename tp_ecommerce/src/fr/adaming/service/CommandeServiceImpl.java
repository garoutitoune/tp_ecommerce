package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService{

	@EJB
	private ICommandeDao codao;
	
	@Override
	public Commande addCommande(Commande commande, Client client) {
		commande.setClient(client);
		return codao.addCommande(commande);
	}

	@Override
	public void delCommande(Commande commande) {
		codao.delCommande(commande);		
	}

	@Override
	public Commande searchCommandeById(Commande commande) {
		return codao.searchCommandeById(commande);
	}

}
