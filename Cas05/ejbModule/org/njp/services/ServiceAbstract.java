package org.njp.services;

import java.util.List;

import org.njp.dao.IDAOAbstract;
import org.njp.entities.BasicEntity;

public abstract class ServiceAbstract<T extends BasicEntity, DAO extends IDAOAbstract<T>> implements IServiceAbstract<T> {
	
	public ServiceAbstract() {
	}
	
	@Override
	public boolean add(T object) {
		return this.dao.add(object);
	}

	@Override
	public boolean removeById(int id) {
		return this.dao.removeById(id);
	}

	@Override
	public boolean update(T object) {
		return this.dao.update(object);
	}

	@Override
	public List<T> getAll() {
		return this.dao.getAll();
	}

	@Override
	public T getById(int id) {
		return this.dao.getById(id);
	}
	
	public abstract void setDAO(DAO dao);

	protected DAO dao; 
}
