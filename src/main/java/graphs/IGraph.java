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

    void searchBF(T source);
    Boolean searchBF(T source, T search);
    void searchDF(T source);
    Boolean searchDF(T source, T search);
    void searchLDF(T source, Integer limit);
    Boolean searchLDF(T source, Integer limit, T search);
}
