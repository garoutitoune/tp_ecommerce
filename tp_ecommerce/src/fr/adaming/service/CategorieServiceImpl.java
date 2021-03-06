package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;

@Stateful
public class CategorieServiceImpl implements ICategorieService{
	
	@EJB
	private ICategorieDao caDao;

	@Override
	public List<Categorie> getAllCategorie() {
		
		return caDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie ca, Gerant g) {
		//recup�re le gerant dans une liste
		List<Gerant> gIn=new ArrayList<Gerant>();
		
		//injection gerant dans liste
		gIn.add(g);
		
		//injection liste gerant (1 gerant) dans cat�gorie
		ca.setGerant(gIn);
		return caDao.addCategorie(ca);
	}

	@Override
	public int modifierCategorie(Categorie ca) {
		System.out.println("c'est quoi ce truc " +ca.getPhoto());
		if(ca.getPhoto().length!=0) {
			return caDao.modifierCategorie(ca);
		}else {
			
			return caDao.modifierCategoriePhoto(ca);
		}
	}

	@Override
	public int supprimerCategorie(Categorie ca) {
		
		return caDao.supprimerCategorie(ca);
	}

	

}
