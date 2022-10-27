package it.prova.gestionesmartphoneapp.dao.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionesmartphoneapp.model.App;

public class AppDAOImpl implements AppDAO {

	EntityManager entityManager;

	@Override
	public List<App> list() throws Exception {
		return entityManager.createQuery("from App", App.class).getResultList();
	}

	@Override
	public App get(Long id) throws Exception {
		if (id == null || id < 1)
			throw new Exception("Impossibile effettuare la ricerca, id mancante o non valido.");
		return entityManager.createQuery("from App where id=?1", App.class).setParameter(1, id).getResultStream()
				.findFirst().orElse(null);
	}

	@Override
	public void update(App o) throws Exception {
		if (o == null || o.getId() == null || o.getId() < 1)
			throw new Exception("Impossibile eseguire operazione, input mancante o non valido");
		o = entityManager.merge(o);
	}

	@Override
	public void insert(App o) throws Exception {
		if (o == null)
			throw new Exception("Impossibile eseguire operazione, input mancante o non valido");
		entityManager.persist(o);
	}

	@Override
	public void delete(App o) throws Exception {
		if (o == null || o.getId() == null || o.getId() < 1)
			throw new Exception("Impossibile eseguire operazione, input mancante o non valido");
		entityManager.remove(entityManager.merge(o));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public App getAppEager(Long id) throws Exception {
		if (id == null || id < 1)
			throw new Exception("Impossibile effettuare la ricerca, id mancante o non valido.");
		TypedQuery<App> result = entityManager
				.createQuery("from App a inner join fetch a.smartphones where a.id=?1", App.class).setParameter(1, id);
		return result.getResultStream().findFirst().orElse(null);
	}

}
