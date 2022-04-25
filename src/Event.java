package src;

public class Event {

    String type;
    int arrivalTime;
    int delay;
    int finishTime;

    public Event(String type, int arrivalTime, int delay, int finishTime) {
        this.type = type;
        this.arrivalTime = arrivalTime;
        this.delay = delay;
        this.finishTime = finishTime;
    }

    public int getPriority(){
        return this.finishTime;
    }

    @Override
    public String toString() {
        return "Event {"+"\n"+"\ttype: "+this.type+"\n"+"\tarrivalTime: "+this.arrivalTime+"\n"+"\tdelay: "+this.delay+"\n"+"\tfinishTime: "+this.finishTime+"\n"+"}";
    }
}
