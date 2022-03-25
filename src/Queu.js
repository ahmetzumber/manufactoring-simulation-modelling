class Node {
    constructor(element, priority) {
        this.element = element;
        this.priority = priority;
    }
}
class PriorityQueue {
    constructor() {
        this.items = [];
    }
    enqueu(item,priority){
        let node = new Node(item, priority);
        let contain = false;

        for (let i = 0; i < this.items.length; i++) {
            if (this.items[i].priority > node.priority) {
                this.items.splice(i, 0, node);
                contain = true;
                break;
            }
        }
        if (!contain) {
            this.items.push(node);
        }
    }
    dequeue() {
        if (this.items.length === 0)
            return "Underflow";
        return this.items.shift();
    }

}
export default new PriorityQueue()
