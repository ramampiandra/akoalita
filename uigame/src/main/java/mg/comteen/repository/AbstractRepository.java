package mg.comteen.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractRepository<T> {
	
	@PersistenceContext(name = "servorona")
	protected EntityManager entityManager;
	
	/**
	 * Insert Object T in DataBase
	 * @param t
	 */
	public void create(T t){
		entityManager.persist(t);
	}
}
