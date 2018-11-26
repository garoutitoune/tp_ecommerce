package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{
	
	//transformation de l'association UML en java 
	@EJB
	private IProduitDao proDao;

	@Override
	public List<Produit> getAllProduit() {
		
		return proDao.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		return proDao.addProduit(pro);
	}

	@Override
	public int deleteProduit(Produit pro) {
		
		return proDao.deleteProduit(pro);
	}

	@Override
	public int modifierProduit(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		if(pro.getPhoto().length!=0) {
			return proDao.modifierProduit(pro);
		}else {
			return proDao.modifierProduit(pro);
		}
		
	}

	@Override
	public List<Produit> getAllProduitByCat(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		return proDao.getAllProduitCat(pro);
	}

	@Override
	public Produit getProduitById(Produit pro) {
		System.out.println("produit service prodao"+proDao+"proid"+pro.getId());
		return proDao.getProduitById(pro);
	}

	@Override
	public List<Produit> getProduitByDes(Produit pro, Categorie ca) {
		pro.setCategorie(ca);
		return proDao.getProduitByDescr(pro);
	}

}
