package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao{

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;
	
	@Override
	public Client isExist(Client cl) {
		String req = "SELECT c FROM Client c WHERE c.email=:pMail AND c.mdp=:pMdp";

		Query query = em.createQuery(req);

//		// assigner les valeurs aux params
		query.setParameter("pMail", cl.getEmail());
		query.setParameter("pMdp", cl.getMdp());

		return (Client) query.getSingleResult();
	}

	@Override
	public Client addClient(Client cl) {
		em.persist(cl);
		return cl;
	}

	@Override
	public Client modifClient(Client cl) {
		Client clout=this.searchById(cl);
		clout.setNom(cl.getNom());
		clout.setAdresse(cl.getAdresse());
		clout.setEmail(cl.getEmail());
		clout.setMdp(cl.getMdp());
		clout.setTel(cl.getTel());
		return em.merge(clout);
	}

	@Override
	public void delClient(Client cl) {
		cl=this.searchById(cl);
		em.remove(cl);
	}

	@Override
	public Client searchById(Client cl) {
		return em.find(Client.class, cl.getId());
	}
	
	
	
	

}
