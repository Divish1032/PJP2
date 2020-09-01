package com.sapient.calculator.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.sapient.calculator.web.CommonMethod;
import com.sapient.calculator.web.persistance.Queries;
//import com.sapient.calculator.web.persistance.Query;
import com.sapient.calculator.web.persistance.Sessions;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.joda.time.DateTime;

public class SessionHistory extends CommonMethod{
    
    static Session session;

    public void init() {
        Configuration con = new Configuration().configure().addAnnotatedClass(Query.class).addAnnotatedClass(Sessions.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        session = sf.openSession();
        session.beginTransaction();

        /* Query query = session.createQuery("from Queries"); 
        
        List<Queries> ss =(List<Queries>) query.list();

        sop(ss.size() + ""); */

        sop("Currently there is no history stored in your database");
        sop("------------------------------------------------------------");

        session.getTransaction().commit();
        session.close();
    }
}