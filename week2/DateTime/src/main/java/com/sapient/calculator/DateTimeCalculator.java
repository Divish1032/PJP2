package com.sapient.calculator;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Scanner;

import com.sapient.calculator.models.AutomatedQuery;
import com.sapient.calculator.operations.DayWeek;
import com.sapient.calculator.operations.NDays;
import com.sapient.calculator.operations.NLP;
import com.sapient.calculator.operations.TwoDate;
import com.sapient.calculator.operations.WeekNumber;
import com.sapient.calculator.persistance.Queries;
import com.sapient.calculator.persistance.Sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.joda.time.DateTime;


public class DateTimeCalculator extends CommonMethod {

    com.sapient.calculator.persistance.Sessions ses;
    Scanner d;
    Session session;
    static AutomatedQuery aq = null;

    public DateTimeCalculator(Scanner d){
        this.ses = new Sessions();
        this.d = d;
        Configuration con = new Configuration().configure().addAnnotatedClass(Queries.class).addAnnotatedClass(Sessions.class);
        
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        this.session = sf.openSession();
    }

    public void start(){
        
        this.session.beginTransaction();


        // Session started 
        this.ses.setStartTime(new Timestamp( new DateTime().getMillis()));
        this.ses.setStart(true);
        
        this.session.save(this.ses);
        while (this.ses.getStart() == true) {
            init();
        }

        this.ses.setEndTime(new Timestamp( new DateTime().getMillis()));

        this.session.getTransaction().commit();
        this.d.close();
        this.session.close();
    }

    public void init(){
        // Display introductory messages
        introDisplay();
        
        String choice;
        
        // Get input from user for his choice of the operation
        choice = this.d.next();
        
        // clearing the console for better visualization
        //clearConsole();

        if(choice.equals("quit")){
            sop("Session ended");
            this.ses.setStart(false);
            return;
        }
    
        try {
            int ch = Integer.parseInt(choice);
            checkOperation(ch, d);
            sop("--------------------------------------------------------");
            sop("Restarting....");
        } catch (NumberFormatException e) {
            sop("Enter a valid input!!!");
            sop("--------------------------------------------------------");
            sop("Restarting....");
        }
        return;
    }

    public void checkOperation(int choice, Scanner d) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        
        map.put(1, () -> {
            Queries query = new Queries();
            query.setSession(this.ses);
            TwoDate operation = new TwoDate(d, query, aq);
            query = operation.init();
            query.setQueryEndTime(new Timestamp( new DateTime().getMillis()));
            session.save(query);
        });
        map.put(2, () -> {
            Queries query = new Queries();
            query.setSession(this.ses);
            NDays ndays = new NDays(d, query, aq);
            query = ndays.init();
            query.setQueryEndTime(new Timestamp( new DateTime().getMillis()));
            session.save(query);
        });
        map.put(3, () -> {
            Queries query = new Queries();
            query.setSession(this.ses);
            DayWeek dw = new DayWeek(d, query, aq);
            query =  dw.init(); 
            query.setQueryEndTime(new Timestamp( new DateTime().getMillis()));
            session.save(query);
        });
        map.put(4, () -> {
            Queries query = new Queries();
            query.setSession(this.ses);
            WeekNumber wn = new WeekNumber(d, query, aq);
            query =  wn.init(); 
            query.setQueryEndTime(new Timestamp( new DateTime().getMillis()));
            session.save(query);
        });
        map.put(5, () -> {
            Queries query = new Queries();
            query.setSession(this.ses);
            NLP nlp = new NLP(d, query, aq);
            query = nlp.translator();
            query.setQueryEndTime(new Timestamp( new DateTime().getMillis()));
            session.save(query);
        });
        
        Runnable action = map.get(choice);
        if (action != null) {
            action.run();
        } else {
            sop("Invalid input for operation. Restart the app if you want to use it again");
            sop("--------------------------------------------------------");
            return;
        }
    }

    public static void setAutomatedQuery(AutomatedQuery temp) {
        aq = temp;
    }

    public static void introDisplay() {
        sop("Welcome to Date Time calculator");
        sop("Choose a menu item from the list of given operations.");
        sop("Enter 1 for addition/subtraction of two dates.");
        sop("Enter 2 for addition/subtraction of n days/weeks/months to a given date.");
        sop("Enter 3 to determine day of the week of a given date.");
        sop("Enter 4 to determine week number of a given date.");
        sop("Enter 5 to translate natural language phrases");
        sop("Enter quit to exit the session");
    }

}