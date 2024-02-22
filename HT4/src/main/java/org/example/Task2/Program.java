package org.example.Task2;

import org.example.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Program {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            //Save data to DB
            Course course = Course.createCourse();
            session.save(course);
            System.out.println("Object saved");

            //Read data from DB
            Course courseFromDB = session.get(Course.class, course.getId());
            System.out.println("Object read from DB");
            System.out.println(courseFromDB);

            //Update data
            courseFromDB.updateTitle();
            courseFromDB.updateDuration();
            session.update(courseFromDB);
            System.out.println("Data updated.");

            session.delete(courseFromDB);
            System.out.println(String.format("Object %s deleted", courseFromDB.getClass().getSimpleName()));

            session.getTransaction().commit();


        }
        finally {
            sessionFactory.close();
        }

    }
}
