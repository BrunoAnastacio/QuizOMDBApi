package org.quizapi.util.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

//    private static final EntityManagerFactory FACTORY = Persistence
//            .createEntityManagerFactory("quizapi");
//
//    public static EntityManager getEntityManager() {
//        System.out.println("pegando a entidade na factory");
//        return FACTORY.createEntityManager();
//    }

    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("quizapi");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

}
