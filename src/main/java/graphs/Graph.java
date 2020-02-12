package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Graph<T> implements IGraph<T>{
    //'key' = vertex OF TYPE <T>
    //'value' = ArrayList WITH ADJACENT NODES OF TYPE <T>
    protected HashMap<T, ArrayList<T>> disperseMatrix;

    public Graph(){
        disperseMatrix = new HashMap<>();
    }


    public void addVertex(T vertex) {
        //AVOIDING DUPLICATES
        if (!this.hasVertex(vertex))
            //ADDING vertex WITH AN EMPTY ArrayList OF ADJACENT NODES
            disperseMatrix.put(vertex, new ArrayList<T>());
    }

    public void addEdge(T source, T destination) {
        //AVOIDING DUPLICATES
        if (!this.hasEdge(source,destination)) {
            //CREATE NODES IF THEY DID NOT EXIST
            if (!disperseMatrix.containsKey(source)) addVertex(source);
            if (!disperseMatrix.containsKey(destination)) addVertex(destination);

            //ADD DESTINATION TO THE LIST OF ADJACENT NODES OF SOURCE
            disperseMatrix.get(source).add(destination);
        }
    }

    public Integer countVertex() {
        //RETURNS THE AMOUNT OF VERTEX IN GRAPH
        //'key' = vertex OF TYPE <T>
        return disperseMatrix.keySet().size();
    }

    public abstract Integer countEdge();

    public Boolean hasVertex(T vertex) {
        //CHECK IF KEY EXISTS
        //'key' = vertex OF TYPE <T>
        return disperseMatrix.containsKey(vertex);
    }

    public Boolean hasEdge(T source, T destination) {
        //CHECK IF KEY EXISTS
        //'key' = vertex OF TYPE <T>
        if (disperseMatrix.containsKey(source)) //Necessary because HashMap.get(K) returns null if key is not mapped
            //CHECKING IF destination IS IN THE ADJACENT NODES
            //'value' = ArrayList WITH ADJACENT NODES OF TYPE <T>
            return disperseMatrix.get(source).contains(destination);
        else return false;
    }

    public Integer vertexOutDegree(T vertex){
        //CHECK IF KEY EXISTS
        //'key' = vertex OF TYPE <T>
        if (disperseMatrix.containsKey(vertex))
            //CHECKING HOW MANY ADJACENT NODES ARE ATTACHED TO THAT VERTEX
            return disperseMatrix.get(vertex).size();
        else return 0;
    }

    public abstract Integer vertexInDegree(T vertex);

    public abstract Boolean isEulerian();

    public abstract Boolean isConnected();

    public Boolean isConnected(T source) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        ArrayDeque<T> exploring = new ArrayDeque<>();

        if (!remaining.contains(source))return false;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.addLast(source);

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.removeFirst();

            //GET THE ADJACENT NODES
            for (T adjacent : disperseMatrix.get(head)) {
                //CHECK IF REMAINS UNDISCOVERED
                if (remaining.contains(adjacent)) {
                    //REMOVE FROM UNDISCOVERED
                    remaining.remove(adjacent);
                    //ADD TO NEWLY DISCOVERED NODES
                    exploring.addLast(adjacent);
                }
            }
        }

        //EMPTY IF THERE ARE NO UNDISCOVERED NODES
        return remaining.isEmpty();
    }

    @Override
    public String toString(){
        String result = "Nodes:\n";
        //GETTING A LIST OF EACH VERTEX
        for (T vertex : disperseMatrix.keySet()){
            result+= vertex.toString() + ": ";
            //GETTING A LIST OF ADJACENT NODES
            for (T adjacentNode : disperseMatrix.get(vertex))
                result += adjacentNode.toString() + " ";
            result += "\n";
        }
        return result;
    }

}
