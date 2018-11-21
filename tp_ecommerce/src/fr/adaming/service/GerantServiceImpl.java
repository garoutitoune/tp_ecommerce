package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IGerantDao;
import fr.adaming.model.Gerant;

@Stateful
public class GerantServiceImpl implements IGerantService{
	
	// transformation de l'association UML en JAVA
	@EJB
	private IGerantDao gDao;

	@Override
	public Gerant isExist(Gerant gIn) {
		
		return gDao.isEsist(gIn);
	}

}
