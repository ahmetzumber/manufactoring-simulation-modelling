
export default class Event {
    constructor(willBeInspect=true, arrivalTime, delay, inspectStartTime) {
        this.willBeInspect = willBeInspect
        this.arrivalTime = arrivalTime
        this.delay = delay
        this.inspectStartTime = inspectStartTime
    }
    priority(){
        return this.arrivalTime
    }
}


