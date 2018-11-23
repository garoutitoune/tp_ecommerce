package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao{

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;
	
	@Override
	public LigneCommande addLigne(LigneCommande ligne) {
		em.persist(ligne);
		return ligne;
	}

	@Override
	public LigneCommande searchLigneById(LigneCommande ligne) {
		return em.find(LigneCommande.class, ligne.getId());
	}

	@Override
	public void delLigne(LigneCommande ligne) {
		em.remove(this.searchLigneById(ligne));
	}

}
