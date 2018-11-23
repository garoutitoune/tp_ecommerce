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
public class ProduitDaoImpl implements IProduitDao {

	@PersistenceContext(unitName = "tp_ecommerce")
	private EntityManager em;

	@Override
	public List<Produit> getAllProduit() {
		String req = "SELECT pr From Produit pr";

		Query query = em.createQuery(req);
		List<Produit> liste = query.getResultList();

		for (Produit pro : liste) {
			pro.setImage("data:image/png);base64," + Base64.encodeBase64String(pro.getPhoto()));
		}

		return liste;
	}

	@Override
	public Produit addProduit(Produit pro) {
		em.persist(pro);
		return pro;
	}

	@Override
	public int deleteProduit(Produit pro) {
		Produit proOut = em.find(Produit.class, pro.getId());

		if (proOut.getId() != 0) {
			em.remove(proOut);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int modifierProduit(Produit pro) {
		String req="UPDATE produits SET description=:pDescription, designiation=:pDesign, photo=:pPhoto, prix=:pPrix, quantite=:pQtite, selectionne=:pSelection WHERE id_p=:pIdp AND ca_id=:pIdCa";
		Query query=em.createNativeQuery(req, Produit.class);
		
		query.setParameter("pDescription", pro.getDescription());
		query.setParameter("pDesign", pro.getDesignation());
		query.setParameter("pPhoto", pro.getPhoto());
		query.setParameter("pPrix", pro.getPrix());
		query.setParameter("pQtite", pro.getQuantite());
		query.setParameter("pSelection", pro.isSelectionne());
		query.setParameter("pId", pro.getId());
		query.setParameter("pIdCa", pro.getCategorie().getId());
		
		return query.executeUpdate();
		
	}

	@Override
	public List<Produit> getAllProduitCat(Produit pro) {
		String req="SELECT pro FROM Produit pro WHERE pro.categorie.id=:pIdCa";
		
		Query query=em.createQuery(req);
		List<Produit> liste = query.getResultList();

		for (Produit pr:liste) {
			pr.setImage("data:image/png);base64," + Base64.encodeBase64String(pr.getPhoto()));
		}

		return liste;
	}

	@Override
	public Produit getProduitById(Produit pro) {
		Produit proOut=em.find(Produit.class, pro.getId());
	proOut.setImage("data:image/png);base64," + Base64.encodeBase64String(proOut.getPhoto()));	
	
		return proOut;
	}

}
