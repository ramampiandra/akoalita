package mg.comteen.repository;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mg.comteen.config.UserConnectionSignUp;
import mg.comteen.domain.entity.User;

@Repository
public class UserRepository  extends AbstractRepository<User> {
	
	private static final Logger logging = Logger.getLogger(UserRepository.class);
	
	private static final String JPQL_FIND_USER_BY_ID = "SELECT user FROM User user WHERE user.username = :userId";
	
	//@Transactional(readOnly = true)
	public User findUserByUserId(String userId) {
		logging.debug("User id value ******" + userId);
		try {
			Query query = entityManager.createQuery(JPQL_FIND_USER_BY_ID);
			query.setParameter("userId", userId);
		    User user = (User)query.getSingleResult();
		    logging.debug("User value ******" + user);
		    return user;
		} catch(Exception ex) {
			return null;
		}
	}

}
