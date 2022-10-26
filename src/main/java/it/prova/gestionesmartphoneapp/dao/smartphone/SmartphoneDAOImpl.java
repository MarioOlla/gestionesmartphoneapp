package it.prova.gestionesmartphoneapp.dao.smartphone;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionesmartphoneapp.model.Smartphone;

public class SmartphoneDAOImpl implements SmartphoneDAO {

	EntityManager entityManager;

	@Override
	public List<Smartphone> list() throws Exception {
		return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();
	}

	@Override
	public Smartphone get(Long id) throws Exception {
		if (id == null || id < 1)
			throw new Exception("Impossibile effettuare la ricerca, input mancante o non valido.");
		return entityManager.createQuery("from Smartphone where id=?1", Smartphone.class).setParameter(1, id).getResultStream().findFirst()
				.orElse(null);
	}

	@Override
	public void update(Smartphone o) throws Exception {
		if (o == null || o.getId() == null || o.getId() < 1)
			throw new Exception("Impossibile eseguire operazione, input invalido");
		o = entityManager.merge(o);

	}

	@Override
	public void insert(Smartphone o) throws Exception {
		if (o == null)
			throw new Exception("Impossibile eseguire operazione, input invalido");
		entityManager.persist(o);
	}

	@Override
	public void delete(Smartphone o) throws Exception {
		if (o == null || o.getId() == null || o.getId() < 1)
			throw new Exception("Impossibile eseguire operazione, input invalido");
		entityManager.remove(entityManager.merge(o));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

}
