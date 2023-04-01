package jpa;

import Entities.CarEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main1 {
    public static void main(String[] args) {

    //JPA connection
        List<CarEntity> cars;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try (SessionFactory sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(CarEntity.class)
                .buildMetadata()
                .buildSessionFactory()) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(new CarEntity(1, "assets/Terrier-LT-79.jpg", "TERRIER LT-79", 1000));
            session.save(new CarEntity(2, "assets/HUSKY-TSV.jpg", "HUSKY TSV", 1200));
            session.save(new CarEntity(3, "assets/BATT-UMG.jfif", "BATT UMG", 900));

           // CarEntity updatedCar1 = new CarEntity(1, "assets/Terrier-LT-79.jpg", "TERRIER LT-79 UPD", 1000);
           // session.update(updatedCar1);
           // session.delete(new CarEntity(2, "assets/HUSKY-TSV.jpg", "HUSKY TSV", 1200));

            cars = (List<CarEntity>) session.createQuery("from CarEntity").list();

            session.getTransaction().commit();
        }

        System.out.println("---JPA---");
        for(CarEntity car : cars) {
            System.out.println(car);
        }
        System.out.println("SUCCESS!");
    }
}
