package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Gerant;

@Stateless
public class GerantDaoImpl implements IGerantDao {

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;

	@Override
	public Gerant isEsist(Gerant g) {
		String req = "SELECT g FROM Gerant g WHERE g.mail=:pMail AND g.mdp=:pMdp";

		Query query = em.createQuery(req);

		// assigner les valeurs aux params
		query.setParameter("pMail", g.getMail());
		query.setParameter("pMdp", g.getMdp());

		return (Gerant) query.getSingleResult();
	}

}
