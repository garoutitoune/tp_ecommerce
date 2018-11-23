package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeService {

	public Commande addCommande(Commande commande, Client client);
	public void delCommande(Commande commande);
	public Commande searchCommandeById(Commande commande);
}
