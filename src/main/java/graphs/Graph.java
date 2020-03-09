package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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

    public Boolean searchBF(T source, T search) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        ArrayDeque<T> exploring = new ArrayDeque<>();

        if (!remaining.contains(source))return false;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.addLast(source);

        System.out.print("Breadth first search for node: "+ search + "\nStart");

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.removeFirst();

            //PRINT NODE VALUE
            System.out.print(" => "+head.toString());

            //FOUND
            if (head.equals(search)) {
                System.out.println("\nNode: " + search.toString() + " found");
                return true;
            }

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

        System.out.println();

        //IF NOT FOUND, RETURN 0
        return false;
    }

    public void searchBF(T source) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        ArrayDeque<T> exploring = new ArrayDeque<>();

        if (!remaining.contains(source))return;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.addLast(source);

        System.out.print("Breadth first search\nStart");

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.removeFirst();

            //PRINT NODE VALUE
            System.out.print(" => "+head.toString());

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
        System.out.println();
    }

    public Boolean searchDF(T source, T search) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        Stack<T> exploring = new Stack<>();

        if (!remaining.contains(source))return false;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.push(source);

        System.out.print("Depth first search for node: "+ search + "\nStart");

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.pop();

            //PRINT NODE VALUE
            System.out.print(" => "+head.toString());

            //FOUND
            if (head.equals(search)) {
                System.out.println("\nNode: " + search.toString() + " found");
                return true;
            }

            //GET THE ADJACENT NODES
            for (T adjacent : disperseMatrix.get(head)) {
                //CHECK IF REMAINS UNDISCOVERED
                if (remaining.contains(adjacent)) {
                    //REMOVE FROM UNDISCOVERED
                    remaining.remove(adjacent);
                    //ADD TO NEWLY DISCOVERED NODES
                    exploring.push(adjacent);
                }
            }
        }

        System.out.println();

        //IF NOT FOUND, RETURN 0
        return false;
    }

    public void searchDF(T source) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        Stack<T> exploring = new Stack<>();

        if (!remaining.contains(source))return;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.push(source);

        System.out.print("Depth first search\nStart");

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.pop();

            //PRINT NODE VALUE
            System.out.print(" => "+head.toString());

            //GET THE ADJACENT NODES
            for (T adjacent : disperseMatrix.get(head)) {
                //CHECK IF REMAINS UNDISCOVERED
                if (remaining.contains(adjacent)) {
                    //REMOVE FROM UNDISCOVERED
                    remaining.remove(adjacent);
                    //ADD TO NEWLY DISCOVERED NODES
                    exploring.push(adjacent);
                }
            }
        }
        System.out.println();
    }

    public void searchLDF(T source, Integer limit) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        Stack<T> exploring = new Stack<>();
        Stack<Integer> level = new Stack<>();

        if (!remaining.contains(source))return;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.push(source);
        //ADD LEVEL VALUE
        level.push(1);

        System.out.print("Limited depth first search with limit "+ limit+"\nStart");

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.pop();
            int tempLevel = level.pop();

            //PRINT NODE VALUE
            System.out.print(" => "+head.toString() + " (L="+ tempLevel + ")");

            //GET THE ADJACENT NODES
            for (T adjacent : disperseMatrix.get(head)) {
                //CHECK IF REMAINS UNDISCOVERED && WITHIN LIMIT SEARCH
                if (remaining.contains(adjacent) && tempLevel+1<=limit) {
                    //REMOVE FROM UNDISCOVERED
                    remaining.remove(adjacent);
                    //ADD TO NEWLY DISCOVERED NODES
                    exploring.push(adjacent);
                    //ADD LEVEL VALUE
                    level.push(tempLevel + 1);
                }
            }
        }
        System.out.println();
    }

    public Boolean searchLDF(T source, Integer limit, T search) {
        //LIST OF UNDISCOVERED VERTEXES
        ArrayList<T> remaining = new ArrayList<>(disperseMatrix.keySet());
        //PREPARING A QUEUE FOR NEWLY DISCOVERED VERTEX
        Stack<T> exploring = new Stack<>();
        Stack<Integer> level = new Stack<>();

        if (!remaining.contains(source))return false;

        //REMOVE FROM UNDISCOVERED
        remaining.remove(source);
        //ADD TO NEWLY DISCOVERED NODES
        exploring.push(source);
        //ADD LEVEL VALUE
        level.push(1);

        System.out.print("Limited depth first search for node: "+ search + " with limit "+ limit+"\nStart");

        //STOP IF THERE ARE NO NEWLY DISCOVERED NODES
        while (!exploring.isEmpty()) {

            //SELECT THE HEAD OF THE QUEUE
            T head = exploring.pop();
            int tempLevel = level.pop();

            //PRINT NODE VALUE
            System.out.print(" => "+head.toString() + " (L="+ tempLevel + ")");

            //FOUND
            if (head.equals(search)) {
                System.out.println("\nNode: " + search.toString() + " found");
                return true;
            }

            //GET THE ADJACENT NODES
            for (T adjacent : disperseMatrix.get(head)) {
                //CHECK IF REMAINS UNDISCOVERED && WITHIN LIMIT SEARCH
                if (remaining.contains(adjacent) && tempLevel+1<=limit) {
                    //REMOVE FROM UNDISCOVERED
                    remaining.remove(adjacent);
                    //ADD TO NEWLY DISCOVERED NODES
                    exploring.push(adjacent);
                    //ADD LEVEL VALUE
                    level.push(tempLevel + 1);
                }
            }
        }
        //IF NOT FOUND RETURN 0
        System.out.println();
        return false;
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
