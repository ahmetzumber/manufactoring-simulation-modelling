import PriorityQueue from "./Queu";

// const system variables
const INTERVAL = 5
const FAULTY_PARTS = 100
const SERVICE_TIME = Math.floor(Math.random() * 8) + 2; // 6 +- 4 service time

// js enum usage
const eventTypes = { BUSY: 1, IDLE: 0 }
Object.freeze(eventTypes)

let FEL = PriorityQueue
let isFinished = true


// it gives next event type which has high priority
function timeAdvance(){
    return FEL.dequeue()
}

function eventHandling(event){}

function generateReport(){}

function initialization(timeInterval, endingCriteria, startTime, finishTime){
    let interval = timeInterval
    let criteria = endingCriteria
    let sTime = startTime
    let fTime = finishTime
}

function startSimulation(){
    console.log('Simulation starts..')
    console.log()
    initialization(5, 100, 0, 60)
    console.log("CLOCK\t\tListinQueu\t\tStatus\t\t")
    while(!isFinished){

        let nextEventType = timeAdvance()
        eventHandling(nextEventType)
    }
    generateReport()
}



startSimulation()
