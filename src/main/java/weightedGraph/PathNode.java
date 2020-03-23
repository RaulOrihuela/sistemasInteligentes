package weightedGraph;

public class PathNode<T> implements Comparable<PathNode>{
    private HeuristicNode<T> currentNode, previousNode;
    private Double cost, priority;

    public PathNode(HeuristicNode<T> currentNode){
        this(currentNode,null, Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
    }

    public PathNode(HeuristicNode<T> currentNode, HeuristicNode<T> previousNode, Double cost, Double priority){
        this.currentNode = currentNode;
        this.previousNode = previousNode;
        this.cost = cost;
        this.priority = priority;
    }

    public HeuristicNode<T> getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(HeuristicNode<T> currentNode) {
        this.currentNode = currentNode;
    }

    public HeuristicNode<T> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(HeuristicNode<T> previousNode) {
        this.previousNode = previousNode;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(PathNode o) {
        if (this.priority > o.priority) return 1;
        else if (this.priority < o.priority) return -1;
        else return 0;
    }


    @Override
    public String toString() {
        return "PathNode{" +
                previousNode +
                "\t=>\t" + currentNode +
                ", cost=" + cost +
                ", priority=" + priority +
                '}';
    }
}
