package graphs;

import java.util.ArrayList;

public class UndirectedGraph<T> extends Graph<T> {

    public void addEdge(T source, T destination) {
        //ADD DESTINATION TO THE LIST OF ADJACENT NODES OF SOURCE
        super.addEdge(source,destination);
        //AVOIDING DUPLICATES
        if (!this.hasEdge(destination,source)) {
            //FOR UNDIRECTED GRAPH CONNECTIONS ARE BIDIRECTIONAL
            disperseMatrix.get(destination).add(source);
        }
    }

    public Integer countEdge() {
        int differentEdges = 0, selfEdges = 0;
        //GETTING A LIST OF EACH VERTEX
        for (T vertex : disperseMatrix.keySet()) {
            //CHECKING HOW MANY ADJACENT NODES ARE ATTACHED TO THAT VERTEX
            if (this.hasEdge(vertex,vertex)) {
                differentEdges += disperseMatrix.get(vertex).size() - 1;
                selfEdges ++;
            } else differentEdges += disperseMatrix.get(vertex).size();
        }
        //FOR UNDIRECTED GRAPH, EDGES ARE DOUBLED
        return differentEdges/2 + selfEdges;
    }

    public Integer vertexInDegree(T vertex){
        return this.vertexOutDegree(vertex);
    }

    public Boolean isEulerian() {
        if (!this.isConnected()) return false;

        Integer odd = 0;
        //GETTING A LIST OF EACH VERTEX
        for (T vertex : disperseMatrix.keySet()) {
            //CHECK IF even GRADE
            Boolean even = this.vertexOutDegree(vertex)%2 == 0;
            //CHECK IF DOES NOT INCLUDE SELF
            Boolean selfInclude = this.hasEdge(vertex,vertex);

            if ((even && selfInclude) || (!even && !selfInclude)){
                odd ++;
                //IF THERE ARE 2 OR MORE NODES WITH ODD DEGREE, IT IS NOT EULERIAN
                if (odd > 2) return false;
            }
        }
        return true;
    }

    public Boolean isConnected(){
        //THE FIRST VERTEX ON THE KEY SET
        T randomVertex = new ArrayList<>(disperseMatrix.keySet()).get(0);
        //IF ANY SOURCE YIELDS A CONNECTED PATH, THEN THE GRAPH IS CONNECTED
        return (this.isConnected(randomVertex));
    }

}
