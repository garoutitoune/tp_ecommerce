package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Gerant;

@Local
public interface ICategorieService {
	
	public List<Categorie> getAllCategorie();
	
	public Categorie addCategorie(Categorie ca, Gerant g);
	
	public int modifierCategorie(Categorie ca);
	
	public int supprimerCategorie(Categorie ca);
	
	
}
