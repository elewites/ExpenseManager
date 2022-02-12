package ui.gui;

import model.Event;
import model.EventLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Represents a class that can be used to access EventLog;
//from here you are able to print the Events in your EventLog to the console
//Note: EventPrinter is not used to add Events to EventLog,
//      this only happens in classes found in the model package,
//      check out ExpenseManger.addExpense()
public class EventPrinter {

    private EventLog log;             //instance of EventLog
    private List<Event> events;       //Events

    //EFFECTS: constructs an even printer;
    //         assigns fields log and events
    public EventPrinter() {
        log = EventLog.getInstance();
        events = new ArrayList<>();
    }

    //EFFECTS: prints all Events in EventLog
    public void printEvents() {
        Iterator<Event> it = log.iterator();
        System.out.println("Event log: ");
        int eventOrder = 1;
        while (it.hasNext()) {
            Event currEvent = it.next();
            System.out.println(Integer.toString(eventOrder) + ". " + currEvent);
            eventOrder++;
        }
    }

    //EFFECTS: prints the Event that was last added to the EventLog
    public void printMostRecentEvent() {
        List<Event> events = new ArrayList<>();
        Iterator<Event> it = log.iterator();
        while (it.hasNext()) {
            events.add(it.next());
        }
        Event recentEvent = events.get(events.size() - 1);
        System.out.println(recentEvent);
        System.out.println("");
    }

    //MODIFIES: this
    //EFFECTS: populates events with new Events added to EventLog
    private void populateEvents() {
        Iterator<Event> it = log.iterator();
        while (it.hasNext()) {
            Event currEvent = it.next();
            events.add(currEvent);
        }
    }
}
