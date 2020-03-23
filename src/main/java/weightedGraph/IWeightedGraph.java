package weightedGraph;

public interface IWeightedGraph<T>{
    void addVertex(HeuristicNode<T> vertex);
    void addEdge(HeuristicNode<T> source, HeuristicNode<T> destination, Double cost);
    Boolean hasVertex(HeuristicNode<T> vertex);
    Boolean hasEdge(HeuristicNode<T> source, HeuristicNode<T> destination);

    String greedySearch(HeuristicNode<T> source, HeuristicNode<T> destination);
    String aStarSearch(HeuristicNode<T> source, HeuristicNode<T> destination);
}
