package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
	public Client isExist(Client cl);
	public Client addClient(Client cl);
	public Client modifClient(Client cl);
	public void delClient(Client cl);
	public Client searchById(Client cl);
	

}
