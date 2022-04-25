package src;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulation {

    int sCLOCK;
    int TOTAL_PARTS;
    int SERVICE_TIME;
    boolean isFinished;
    boolean isInspectorAvailable;
    PriorityQueue<Event> FEL;
    PriorityQueue<Event> inspectorQueue;
    Statistics statsFEL;
    Statistics statsInspect;
    Random r = new Random();
    public Simulation(){}

    public void initialization(){
        sCLOCK = 0;
        TOTAL_PARTS = 100;
        SERVICE_TIME = r.nextInt(10-2) + 2;
        isFinished = false;
        isInspectorAvailable = true;
        FEL = new PriorityQueue<Event>(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getPriority() > o2.getPriority() ? 1 : -1;
            }
        });
        inspectorQueue = new PriorityQueue<Event>(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getPriority() > o2.getPriority() ? 1 : -1;
            }
        });
        // initial event
        Event initialEvent = new Event("ARRIVE",0,0,5);
        FEL.add(initialEvent);
        statsInspect = new Statistics();
        statsFEL = new Statistics();
        System.out.println("TYPE   "+"ARR.TIME   "+"DELAY   "+"FIN.TIME   ");
        System.out.println("-----------------------------------");
    }

    public Event timeAdvance(){
        Event event = FEL.poll();
        System.out.println(event.type+"\t\t"+event.arrivalTime+"\t\t"+event.delay+"\t\t"+event.finishTime+"\t\t");
        sCLOCK = event.finishTime;
        return event;
    }

    public void eventHandling(Event event){

        switch(event.type){
            case "ARRIVE":
                if (this.isInspectorAvailable) {
                    SERVICE_TIME = r.nextInt(10-2) + 2;
                    int newEventServiceFinishTime = sCLOCK + SERVICE_TIME;
                    Event newEvent = new Event("INSPECT", sCLOCK, 0, newEventServiceFinishTime);
                    this.isInspectorAvailable = false;
                    FEL.add(newEvent);
                } else {
                    inspectorQueue.add(event);
                    System.out.println("InspectorQueue Length: "+inspectorQueue.size());
                    statsInspect.newStats(inspectorQueue.size());
                }
                Event upcomingEvent = new Event("ARRIVE", sCLOCK, 0, sCLOCK+5);
                System.out.println("FEL Length: "+FEL.size());
                FEL.add(upcomingEvent);
                break;
            case "INSPECT":
                // percentage of failure is 10
                int failurePossibility = r.nextInt(100-10);
                // if random number smaller than 10
                if (failurePossibility <= 10){
                    TOTAL_PARTS--;
                    // actually is, controls total part if they failed or not
                    if (TOTAL_PARTS <= 0)
                        isFinished = true;   // if there are 0 unchecked part left simulation finished
                }

                if (inspectorQueue.size() != 0) {
                    Event departureEvent = inspectorQueue.remove();
                    SERVICE_TIME = r.nextInt(10-2) + 2;
                    departureEvent.type = "INSPECT";
                    departureEvent.arrivalTime = sCLOCK;
                    departureEvent.delay = Math.abs(departureEvent.finishTime - departureEvent.arrivalTime);
                    departureEvent.finishTime = sCLOCK + SERVICE_TIME;
                    FEL.add(departureEvent);
                    statsInspect.addDelay(departureEvent.delay);
                    statsFEL.addDelay(departureEvent.delay);
                    break;
                }
                this.isInspectorAvailable = true;
                break;

        }
        statsFEL.newStats(FEL.size());
    }

    public void generateReport(){
        System.out.println("---------- REPORT ----------");
        System.out.println("Simulation Time: "+sCLOCK);
        System.out.println("Average FEL Length: "+statsFEL.getAvgQueueLength());
        System.out.println("Maximum FEL Length: "+statsFEL.maxQueuLength);
        System.out.println("Max Waiting Time: "+statsInspect.maxDelay);
        System.out.println("Average Inspection Queue Length: "+statsInspect.getAvgQueueLength());
        System.out.println("Maximum Inspection Queue Length: "+statsInspect.maxQueuLength);
    }

    public void startSimulation(){
        initialization();
        while(!isFinished){
            Event event = timeAdvance();
            eventHandling(event);
        }
        generateReport();
    }


}
