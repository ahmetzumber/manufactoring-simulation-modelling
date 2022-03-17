import PriorityQueu from "./PriorityQueu";

let queu;
let isFinished = true

// (önceliği yüksek) sıradaki eventi verir
function timeAdvance(){
    return queu.dequeue()
}

function eventHandling(event){}

function generateReport(){}

function initialization(timeInterval, endingCriteria, startTime, finishTime){
    let interval = timeInterval
    let criteria = endingCriteria
    let sTime = startTime
    let fTime = finishTime
    queu = new PriorityQueu()
}

function startSimulation(){
    initialization(5, 100, 0, 60)
    while(!isFinished){
        let eventType = timeAdvance()
        eventHandling(eventType)
    }
    generateReport()
}



startSimulation()
