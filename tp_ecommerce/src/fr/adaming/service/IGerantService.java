package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Gerant;

@Local
public interface IGerantService {
	
	public Gerant isExist(Gerant gIn);
	

}
