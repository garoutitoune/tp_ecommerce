package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;

	@Override
	public List<Categorie> getAllCategorie() {
		String req = "SELECT ca From Categorie ca";

		Query query = em.createQuery(req);
		List<Categorie> liste=query.getResultList();
		
		for(Categorie cat:liste) {
			cat.setImage("data:image/png);base64,"+Base64.encodeBase64String(cat.getPhoto()));
		}
		
		return liste;
		
	}

	@Override
	public Categorie addCategorie(Categorie ca) {
		em.persist(ca);
		return ca;
	}

	@Override
	public int modifierCategorie(Categorie ca) {
		String req="UPDATE Categorie ca SET ca.nom=:pNom, ca.photo=:pPhoto, ca.description=:pDescription WHERE ca.id=:pIdca";
		
		Query query=em.createQuery(req);
		
		query.setParameter("pNom", ca.getNom());
		query.setParameter("pPhoto", ca.getPhoto());
		query.setParameter("pDescription", ca.getDescription());
		query.setParameter("pIdca", ca.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int supprimerCategorie(Categorie ca) {
		Categorie caOut=em.find(Categorie.class, ca.getId());
		
		if(caOut.getId()!=0) {
			em.remove(caOut);
			return 1;
		}else {
		return 0;
		}
	}

}
