package Database;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;





public class SokobanDBManager {

private SessionFactory factory;

	
	public SokobanDBManager() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); 
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();
		
	}
	
	@SuppressWarnings("unchecked")
	public Users addUser(String u) {
		Session session = null;
		Transaction tx = null;
		ArrayList<Users> users = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			users =  (ArrayList<Users>) session.createQuery("from Users where fullname like ?").setString(0,u).list();
			tx.commit();
			
			if(users.size()>0)
			{
				
				return users.get(0);
				
			}
			if(users.size()==0) //if not exist create new
			{
				session.save(new Users(u));
			}

		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		}		
		finally {
			if (session != null)
				session.close();			
		}
		
		return new Users(u);
	}
	
	public void updateUser(Users u) {
		
	}
	
	public void deleteUser(int studentId) {
		
	}
	
	@SuppressWarnings("unchecked")
	public LevelsDB addLevel(String Level) {
		Session session = null;
		Transaction tx = null;
		ArrayList<LevelsDB> levels = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			levels =  (ArrayList<LevelsDB>) session.createQuery("from Levels where LevelName like ?").setString(0, Level).list();
			tx.commit();
			
			if(levels.size()>0)
			{
				return levels.get(0);
			}
			if(levels.size()==0) //if not exist create new
			{
				session.save(new LevelsDB(Level));
			}
			

		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		}		
		finally {
			if (session != null)
				session.close();			
		}
		return new LevelsDB(Level);
	}
	
	public void updateLevel(LevelsDB Level) {
		
	}
	
	public void deleteLevel(LevelsDB Level) {
		
	}
	
	@Transactional
	public void addScores(Users u , LevelsDB l,String time,String steps) {
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			//if both exists do update ( same user did the same level twice - user approved to update)			
			
			Query query = session.createQuery("from Scores where levelcode like ? and usercode like ?");
			query.setParameter(0, l.getLevelcode());
			query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();
			//tx.commit();
			
			if(scores.size()>0)
			{
				Scores s = new Scores(u.getUsercode(),l.getLevelcode(), time, steps);
				Query query2 = session.createQuery("update Scores set time = ? where levelcode like ? and usercode like ?");
				query2.setParameter(0, time);
				query2.setParameter(1, l.getLevelcode());
				query2.setParameter(2, u.getUsercode());
				Query query3 = session.createQuery("update Scores set steps = ? where levelcode like ? and usercode like ?");
				query3.setParameter(0, steps);
				query3.setParameter(1, l.getLevelcode());
				query3.setParameter(2, u.getUsercode());
				
								
				int rowCount = query2.executeUpdate();
				query3.executeUpdate();
		        System.out.println("Rows affected: " + rowCount);
		        tx.commit();
			}
			else
			{
			Scores s = new Scores(u.getUsercode(),l.getLevelcode(), time, steps);
			session.save(s);			
			}		

	         
			
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
	
	public void updateScores(Scores s) {
		
	}
	
	public void deleteUser(Scores s) {
		
	}
	
	
}
