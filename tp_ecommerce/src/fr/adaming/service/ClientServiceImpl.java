package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService{

	// transformation de l'association UML en JAVA
	@EJB
	private IClientDao cldao;	
	
	@Override
	public Client isExist(Client cl) {
		return cldao.isExist(cl);
	}

	@Override
	public Client addClient(Client cl) {
		return cldao.addClient(cl);
	}

	@Override
	public Client modifClient(Client cl) {
		return cldao.modifClient(cl);
	}

	@Override
	public void delClient(Client cl) {
		cldao.delClient(cl);
	}

	@Override
	public Client searchById(Client cl) {
		return cldao.searchById(cl);
	}

}
