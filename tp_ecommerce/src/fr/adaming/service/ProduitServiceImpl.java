package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;

@Stateful
public class ProduitServiceImpl {
	
	//transformation de l'association UML en java 
	@EJB
	private IProduitDao proDao;

}
