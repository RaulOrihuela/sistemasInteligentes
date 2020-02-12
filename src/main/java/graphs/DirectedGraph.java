package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class DirectedGraph<T> extends Graph<T> {

    public Integer countEdge() {
        int result = 0;
        //GETTING A LIST OF EACH VERTEX
        for (T vertex : disperseMatrix.keySet())
            //CHECKING HOW MANY ADJACENT NODES ARE ATTACHED TO THAT VERTEX
            result += disperseMatrix.get(vertex).size();
        return result;
    }

    public Integer vertexInDegree(T vertex){
        Integer result = 0;
        //GETTING A LIST OF EACH VERTEX
        for (T otherVertex : disperseMatrix.keySet())
            //CHECKING IF vertex IS IN THE ADJACENT NODES
            //'value' = ArrayList WITH ADJACENT NODES OF TYPE <T>
            if (disperseMatrix.get(otherVertex).contains(vertex)) result ++;
        return result;
    }

    public Boolean isEulerian() {
        if (!this.isConnected())return false;

        //GETTING A LIST OF EACH VERTEX
        for (T vertex : disperseMatrix.keySet()) {
            //CHECKING IF VERTEX HAS SAME OUT-DEGREE AS IN-DEGREE
            if (vertexOutDegree(vertex) != vertexInDegree(vertex)) return false;
        }
        return true;
    }

    public Boolean isConnected(){
        //GETTING A LIST OF EACH VERTEX
        for (T vertex : disperseMatrix.keySet()) {
            //IF AT LEAST ONE VERTEX AS SOURCE YIELDS A CONNECTED PATH, THEN THE GRAPH IS CONNECTED
            if (this.isConnected(vertex)) return true;
        }
        return false;
    }

}
