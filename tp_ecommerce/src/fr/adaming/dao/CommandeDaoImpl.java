package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao{

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;
	
	@Override
	public Commande addCommande(Commande commande) {
		em.persist(commande);
		return commande;
	}

	@Override
	public void delCommande(Commande commande) {
		commande=this.searchCommandeById(commande);
		em.remove(commande);
	}

	@Override
	public Commande searchCommandeById(Commande commande) {
		return em.find(Commande.class, commande.getId());
	}

}
