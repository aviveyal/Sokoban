package Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

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
			Query query = session.createQuery("from Users where fullname like ?");
			query.setParameter(0, u);
			users = (ArrayList<Users>) query.list();

			if (users.size() > 0) {

				return users.get(0);

			}
			if (users.size() == 0) // if not exist create new
			{
				Users user = new Users(u);
				session.save(user);
				return user;
			}
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return null;

	}

	public void updateUser(Users u) {

	}

	public void deleteUser(int studentId) {

	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public boolean userexists(String username) {
		Session session = null;
		Transaction tx = null;
		ArrayList<Users> users = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<Users> query = session.createQuery("from Users where fullname like ?").setString(0, username);
			query.setParameter(0, username);
			users =  (ArrayList<Users>) query.list();

			if (users.size() > 0) {
				tx.commit();
				return true;
			}
				else{
					tx.commit();
					return false;
					
				}

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public LevelsDB addLevel(String Level) {
		Session session = null;
		Transaction tx = null;
		ArrayList<LevelsDB> levels = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from Levels where LevelName like ?").setString(0, Level);
			query.setParameter(0, Level);
			levels = (ArrayList<LevelsDB>) query.list();

			
			
			if (levels.size() > 0) {
				tx.commit();
				return levels.get(0);
			}
			if (levels.size() == 0) // if not exist create new
			{
				LevelsDB l = new LevelsDB(Level);
				session.save(l);
				return l;
			}
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public boolean levelexists(String Level) {
		Session session = null;
		Transaction tx = null;
		ArrayList<LevelsDB> levels = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query<LevelsDB> query = session.createQuery("from Levels where LevelName like ?").setString(0, Level);
			query.setParameter(0, Level);
			levels = (ArrayList<LevelsDB>) query.list();

			if (levels.size() > 0) {
				tx.commit();
				return true;
			}
				else{
					tx.commit();
					return false;
					
				}

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return false;
	}

	public void updateLevel(LevelsDB Level) {

	}

	public void deleteLevel(LevelsDB Level) {

	}

	@Transactional
	public void addScores(Users u, LevelsDB l, String time, String steps) {
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			// if both exists do update ( same user did the same level twice -
			// user approved to update)

			Query query = session.createQuery("from Scores where levelcode like ? and usercode like ?");
			query.setParameter(0, l.getLevelcode());
			query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();

			if (scores.size() > 0) {
				// update
				tx.commit();
				updateScores(u, l, time, steps);

			} else if (scores.size() == 0) {
				Scores s = new Scores(u.getUsercode(), l.getLevelcode(), time, steps);
				session.save(s);
				tx.commit();

			}

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	@Transactional
	public void updateScores(Users u, LevelsDB l, String time, String steps) {

		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

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
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public void deleteUser(Scores s) {

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Scores> showTopTen(String levelname) {
		//order by time
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		String levelcode;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Query<Integer> query1 = session.createQuery("select levelcode from Levels where LevelName like ? ").setMaxResults(1);
			query1.setParameter(0, levelname);
			
			levelcode =query1.list().get(0).toString();
			
			
			Query<Scores> query = session.createQuery("from Scores where levelcode like ? order by time  ").setMaxResults(10);
			query.setParameter(0,levelcode);
			//query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();
			tx.commit();
		

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		
		return scores;

	}
	@SuppressWarnings("unchecked")
	public ArrayList<Scores> showTopTenSteps(String levelname) {
		//order by time
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		String levelcode;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Query<Integer> query1 = session.createQuery("select levelcode from Levels where LevelName like ? ").setMaxResults(1);
			query1.setParameter(0, levelname);
			
			levelcode =query1.list().get(0).toString();
			
			
			Query<Scores> query = session.createQuery("from Scores where levelcode like ? order by steps*1 ").setMaxResults(10);
			query.setParameter(0,levelcode);
			//query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();
			tx.commit();
		

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		
		return scores;

	}
	@SuppressWarnings("unchecked")
	public ArrayList<String> display(ArrayList <Scores> scores)
	{
		//fix the display
				Session session = null;
				Transaction tx = null;
				ArrayList<String> display = new ArrayList<>();
				String username;
				try {
					session = factory.openSession();
					tx = session.beginTransaction();
					
					for(int i=0; i < scores.size();i++)
					{
					Query<String> query1 = session.createQuery("select fullname from Users where usercode like ? ").setMaxResults(1);
					query1.setParameter(0,scores.get(i).getuserCodes());
					username =query1.list().get(0).toString();
					
				
					display.add(i, "\t"+username+"                    "+scores.get(i).getTime()+"                    "+scores.get(i).getSteps());
					}
					tx.commit();

				} catch (HibernateException ex) {
					if (tx != null)
						tx.rollback();
				} finally {
					if (session != null)
						session.close();
				}
				return display;
				
				

			}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Scores> showTopTenUser(String user) {
		//order by time
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		String usercode;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Query<Integer> query1 = session.createQuery("select usercode from Users where fullname like ? ").setMaxResults(1);
			query1.setParameter(0, user);
			
			usercode =query1.list().get(0).toString();
			
			
			Query<Scores> query = session.createQuery("from Scores where usercode like ? order by time  ").setMaxResults(10);
			query.setParameter(0,usercode);
			//query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();
			tx.commit();
		

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		
		return scores;

	}
	@SuppressWarnings("unchecked")
	public ArrayList<Scores> showTopTenUserSteps(String user) {
		//order by time
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		String usercode;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Query<Integer> query1 = session.createQuery("select usercode from Users where fullname like ? ").setMaxResults(1);
			query1.setParameter(0, user);
			
			usercode =query1.list().get(0).toString();
			
			
			Query<Scores> query = session.createQuery("from Scores where usercode like ? order by steps*1").setMaxResults(10);
			query.setParameter(0,usercode);
			//query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();
			tx.commit();
		

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		
		return scores;

	}
	@SuppressWarnings("unchecked")
	public ArrayList<Scores> showTopTenUserlex(String user) 
	{ 
		//couldnt do query join with embedded id - decided sort the arraylist
		//order by time
		Session session = null;
		Transaction tx = null;
		ArrayList<Scores> scores = null;
		ArrayList<String> sorted=null;
		String usercode;
		String levelname;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Query<Integer> query1 = session.createQuery("select usercode from Users where fullname like ? ").setMaxResults(1);
			query1.setParameter(0, user);
			usercode =query1.list().get(0).toString();

			Query<Scores> query = session.createQuery("from Scores where usercode like ?").setMaxResults(10);
			query.setParameter(0,usercode);
			
						
			//query.setParameter(1, u.getUsercode());
			scores = (ArrayList<Scores>) query.list();
		
			tx.commit();
		

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		
		return scores;

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> displayUser(ArrayList <Scores> scores)
	{
		//fix the display
				Session session = null;
				Transaction tx = null;
				ArrayList<String> display = new ArrayList<>();
				String levelname;
		
				String str;
				try {
					session = factory.openSession();
					tx = session.beginTransaction();
					
					for(int i=0; i < scores.size();i++)
					{
					Query<String> query1 = session.createQuery("select levelname from Levels where levelcode like ? ").setMaxResults(1);
					query1.setParameter(0,scores.get(i).getlevelCodes());
					
					levelname =query1.list().get(0).toString();
					
					str= "\t"+levelname+"                    "+scores.get(i).getTime()+"                    "+scores.get(i).getSteps();
					display.add(i,str);
					
					}
					
					tx.commit();
								

				} catch (HibernateException ex) {
					if (tx != null)
						tx.rollback();
				} finally {
					if (session != null)
						session.close();
				}
				
				return display;
				
				

			}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> displayUserlex(ArrayList <Scores> scores)
	{
		//couldnt do query join with embedded id - decided sort the arraylist
				Session session = null;
				Transaction tx = null;
				ArrayList<String> display = new ArrayList<>();
				String levelname;
				
				String str;
				try {
					session = factory.openSession();
					tx = session.beginTransaction();
					
					for(int i=0; i < scores.size();i++)
					{
					Query<String> query1 = session.createQuery("select levelname from Levels where levelcode like ? ").setMaxResults(1);
					query1.setParameter(0,scores.get(i).getlevelCodes());
					
					levelname =query1.list().get(0).toString();
					
					str= "\t"+levelname+"                    "+scores.get(i).getTime()+"                    "+scores.get(i).getSteps();
					display.add(i,str);
				
					}
					
					Collections.sort(display);
					for(String d: display){
							System.out.println(d);
					}
					tx.commit();
					
								

				} catch (HibernateException ex) {
					if (tx != null)
						tx.rollback();
				} finally {
					if (session != null)
						session.close();
				}
				
				return display;
				
				

			}
	

	
	}

	
	
	
	

