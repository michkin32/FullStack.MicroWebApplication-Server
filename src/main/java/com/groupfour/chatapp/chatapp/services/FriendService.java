package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.Utils.HibernateUtil;
import com.groupfour.chatapp.chatapp.models.Friend;
import com.groupfour.chatapp.chatapp.repositories.FriendRepository;
import com.groupfour.chatapp.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class FriendService {

    UserRepository userRepository;

    FriendRepository friendRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendService.class);

    @Autowired
    public FriendService(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public FriendRepository getFriendRepository() {
        return friendRepository;
    }

    public void setFriendRepository(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    /* Get Friend */
    public List<Friend> list() {
        List<Friend> friendList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            friendList = session.createCriteria(Friend.class).list();
            session.getTransaction().commit();
            LOGGER.debug("Get Friend");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            LOGGER.error("Error Bung {}", e.getMessage());
        } finally {
            session.close();
            LOGGER.info("Transaction Finish");
        }
        return friendList;
    }

    /* Get Friend By Id */
    public Friend get(String id) {
        Friend friend = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            friend = (Friend) session.get(Friend.class, id);
            session.getTransaction().commit();
            LOGGER.debug("Get Friend By Id");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            LOGGER.error("Error Bung {}", e.getMessage());
        } finally {
            session.close();
            LOGGER.info("Transaction finish");
        }
        return friend;
    }

    /* Create Friend */
    public Friend create(Friend friend) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(friend);
            session.getTransaction().commit();
            LOGGER.debug("Data Saved");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            LOGGER.error("Error Bung {}", e.getMessage());
        } finally {
            session.close();
            LOGGER.info("Transaction finish");
        }
        return friend;
    }

    /* Delete Friend */
    public Friend delete(String id) {
        Friend friend = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            friend = (Friend) session.get(Friend.class, id);
            if (friend != null) {
                session.delete(friend);
                session.getTransaction().commit();
            }
            LOGGER.debug("Data sucessfully deleted!");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            LOGGER.error("Error Bung {}", e.getMessage());
        } finally {
            session.close();
            LOGGER.info("Transaction selesai");
        }
        return friend;
    }

    /* Update Friend */
    public Friend update(Friend friend) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(friend);
            session.getTransaction().commit();
            LOGGER.debug("Data Di Update");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            LOGGER.error("Error Bung {}", e.getMessage());
        } finally {
            session.close();
            LOGGER.info("Transaction finish");
        }
        return friend;
    }

    /* Find Friend By Id */
    public Friend findFriend(String custId) {
        Friend friend = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            friend = (Friend) session.get(Friend.class, custId);
            session.getTransaction().commit();
            LOGGER.debug("Data Di Update");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            LOGGER.error("Error Bung {}", e.getMessage());
        } finally {
            session.close();
            LOGGER.info("Transaction finish");
        }
        return friend;
    }

    /* Find friend by firstname and lastname */
    public List<Friend> findFirstAndLastName(String firstName, String lastName) {
        String query = null;
        List<Friend> friend = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            query = "from Friend WHERE firstName = " + "'"+ firstName + "'" + " AND lastName = " + "'"+ lastName + "'";
            System.out.println(query);
            session.beginTransaction();
            friend = session.createQuery(query).list();
            System.out.println("masuk gan");
            session.getTransaction().commit();
            LOGGER.debug("Friend Size",friend.size());
        } catch (Exception e){
            e.printStackTrace();
        }
        return friend;
    }
    public List<Friend> findFirstName(String firstName) {
        String query = null;
        List<Friend> friend = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            query = "SELECT firstName FROM Friend WHERE firstName = " + "'"+ firstName + "'";
            System.out.println(query);
            session.beginTransaction();
            friend = session.createQuery(query).list();
            System.out.println("Query Firstname");
            session.getTransaction().commit();
            LOGGER.debug("Friend Size",friend.size());
        } catch (Exception e){
            e.printStackTrace();
        }
        return friend;
    }
    public List<Friend> findLastName(String lastName) {
        String query = null;
        List<Friend> friend = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            query = "SELECT lastName FROM Friend WHERE lastName = " + "'"+ lastName + "'";
            System.out.println(query);
            session.beginTransaction();
            friend = session.createQuery(query).list();
            System.out.println("Query LastName");
            session.getTransaction().commit();
            LOGGER.debug("Friend Size",friend.size());
        } catch (Exception e){
            e.printStackTrace();
        }
        return friend;
    }
}