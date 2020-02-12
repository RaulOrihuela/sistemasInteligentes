package graphs;

public interface IGraph<T> {
    void addVertex(T vertex);
    void addEdge(T source, T destination);
    Integer countVertex();
    Integer countEdge();
    Boolean hasVertex(T vertex);
    Boolean hasEdge(T source, T destination);

    Integer vertexOutDegree(T vertex);
    Integer vertexInDegree(T vertex);
    Boolean isEulerian();

    Boolean isConnected();
    Boolean isConnected(T source);
}
