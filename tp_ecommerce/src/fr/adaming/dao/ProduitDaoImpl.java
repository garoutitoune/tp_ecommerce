package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao{
	
	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;

	@Override
	public List<Produit> getAllProduit() {
		String req = "SELECT ca From Categorie ca";

		Query query = em.createQuery(req);
		List<Produit> liste=query.getResultList();
		
		for(Produit pro:liste) {
			pro.setImage("data:image/png);base64,"+Base64.encodeBase64String(pro.getPhoto()));
		}
		
		return liste;
	}
	
	

}
