package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Gerant;

@Local
public interface IGerantDao {
	
	public Gerant isEsist(Gerant g);

}
