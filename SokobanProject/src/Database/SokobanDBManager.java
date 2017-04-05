package Database;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class SokobanDBManager {

private SessionFactory factory;

	
	
	
	

	public SokobanDBManager() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); 
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();
	}
	
	public void addUser(Users u) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(u);			
			tx.commit();


		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		}		
		finally {
			if (session != null)
				session.close();			
		}
	}
	
	public void updateUser(Users u) {
		
	}
	
	public void deleteUser(int studentId) {
		
	}
	public void addLevel(LevelsDB Level) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(Level);			
			tx.commit();

		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		}		
		finally {
			if (session != null)
				session.close();			
		}
	}
	
	public void updateLevel(LevelsDB Level) {
		
	}
	
	public void deleteLevel(LevelsDB Level) {
		
	}
	
	
	public void addScores(Scores s) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(s);			
			tx.commit();
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		}		
		finally {
			if (session != null)
				session.close();			
		}
	}
	
	public void updateUser(Scores s) {
		
	}
	
	public void deleteUser(Scores s) {
		
	}
	
	
}
