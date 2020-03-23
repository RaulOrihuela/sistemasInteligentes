package weightedGraph;

import java.util.*;

public class WeightedGraph<T> implements IWeightedGraph<T> {
    private HashMap<HeuristicNode<T>, ArrayList<HeuristicNode<T>>> disperseMatrix;
    private HashMap<String, Double> edgeCosts;
    private Boolean bidirectional;

    public WeightedGraph(Boolean bidirectional){
        disperseMatrix = new HashMap<>();
        edgeCosts = new HashMap<>();
        this.bidirectional = bidirectional;
    }

    @Override
    public void addVertex(HeuristicNode<T> vertex) {
        //AVOIDING DUPLICATES
        if (!this.hasVertex(vertex))
            //ADDING vertex WITH AN EMPTY ArrayList OF ADJACENT NODES
            disperseMatrix.put(vertex, new ArrayList<HeuristicNode<T>>());
    }

    @Override
    public void addEdge(HeuristicNode<T> source, HeuristicNode<T> destination, Double cost) {
        //AVOIDING DUPLICATES
        if (!this.hasEdge(source,destination)) {
            //CREATE NODES IF THEY DID NOT EXIST
            if (!disperseMatrix.containsKey(source)) addVertex(source);
            if (!disperseMatrix.containsKey(destination)) addVertex(destination);

            //ADD DESTINATION TO THE LIST OF ADJACENT NODES OF SOURCE
            disperseMatrix.get(source).add(destination);

            // ADD COSTS
            edgeCosts.put(source.edgeString(destination), cost);
        }

        if (bidirectional) {
            //FOR UNDIRECTED GRAPH CONNECTIONS ARE BIDIRECTIONAL
            if (!this.hasEdge(destination, source)) {
                disperseMatrix.get(destination).add(source);
                edgeCosts.put(destination.edgeString(source), cost);
            }
        }
    }

    @Override
    public Boolean hasVertex(HeuristicNode<T> vertex) {
        //CHECK IF KEY EXISTS
        //'key' = vertex OF TYPE <T>
        return disperseMatrix.containsKey(vertex);
    }

    @Override
    public Boolean hasEdge(HeuristicNode<T> source, HeuristicNode<T> destination) {
        //CHECK IF KEY EXISTS
        //'key' = vertex OF TYPE <T>
        if (disperseMatrix.containsKey(source)) //Necessary because HashMap.get(K) returns null if key is not mapped
            //CHECKING IF destination IS IN THE ADJACENT NODES
            //'value' = ArrayList WITH ADJACENT NODES OF TYPE <T>
            return disperseMatrix.get(source).contains(destination);
        else return false;
    }

    @Override
    public String greedySearch(HeuristicNode<T> source, HeuristicNode<T> destination) {
        String result = "Greedy Search order\n========================\n";
        Queue<PathNode<T>> exploring = new PriorityQueue<>();
        Map<T,PathNode<T>> discovered = new HashMap<>();

        PathNode<T> start = new PathNode<>(source,null,0.0, 0.0);
        exploring.add(start);
        discovered.put(source.name,start);

        while(!exploring.isEmpty()){
            PathNode<T> head = exploring.poll();

            result += head.toString() + "\n";

            //FOUND DESTINATION
            if (head.getCurrentNode().name.equals(destination.name)){
                result += "\n========================\nRoute\n========================\n";
                //GO IN REVERSE ORDER
                Stack<String> path = new Stack<>();
                PathNode<T> temp = head;
                while (temp.getCurrentNode().name != source.name){
                    path.push(temp.toString());
                    temp = discovered.get(temp.getPreviousNode().name);
                }
                path.add(temp.toString());
                while (!path.empty()) result += path.pop() + "\n";
                return result;
            }

            //GET THE ADJACENT NODES
            for (HeuristicNode<T> adjacent : disperseMatrix.get(head.getCurrentNode())){
                PathNode temp = discovered.getOrDefault(adjacent.name, new PathNode<>(adjacent));
                Double newCost = head.getCost() + edgeCosts.get(head.getCurrentNode().edgeString(adjacent));

                //UPDATE VALUES
                if (newCost < temp.getCost()){
                    temp.setPreviousNode(head.getCurrentNode());
                    temp.setCost(newCost);
                    temp.setPriority(newCost);
                    exploring.add(temp);
                }

                //ADD OR UPDATE VALUES
                discovered.put(adjacent.name,temp);
            }
        }

        return destination.name.toString() + " was not found";
    }

    @Override
    public String aStarSearch(HeuristicNode<T> source, HeuristicNode<T> destination) {
        String result = "A* Search order\n========================\n";
        Queue<PathNode<T>> exploring = new PriorityQueue<>();
        Map<T,PathNode<T>> discovered = new HashMap<>();

        PathNode<T> start = new PathNode<>(source,null,0.0, source.heuristicValue(destination));
        exploring.add(start);
        discovered.put(source.name,start);

        while(!exploring.isEmpty()){
            PathNode<T> head = exploring.poll();

            result += head.toString() + "\n";

            //FOUND DESTINATION
            if (head.getCurrentNode().name.equals(destination.name)){
                result += "\n========================\nRoute\n========================\n";
                //GO IN REVERSE ORDER
                Stack<String> path = new Stack<>();
                PathNode<T> temp = head;
                while (temp.getCurrentNode().name != source.name){
                    path.push(temp.toString());
                    temp = discovered.get(temp.getPreviousNode().name);
                }
                path.add(temp.toString());
                while (!path.empty()) result += path.pop() + "\n";
                return result;
            }

            //GET THE ADJACENT NODES
            for (HeuristicNode<T> adjacent : disperseMatrix.get(head.getCurrentNode())){
                PathNode temp = discovered.getOrDefault(adjacent.name, new PathNode<>(adjacent));
                Double newCost = head.getCost() + edgeCosts.get(head.getCurrentNode().edgeString(adjacent));

                //UPDATE VALUES
                if (newCost < temp.getCost()){
                    temp.setPreviousNode(head.getCurrentNode());
                    temp.setCost(newCost);
                    temp.setPriority(newCost + adjacent.heuristicValue(destination));
                    exploring.add(temp);
                }

                //ADD OR UPDATE VALUES
                discovered.put(adjacent.name,temp);
            }
        }

        return destination.name.toString() + " was not found";
    }

    @Override
    public String toString(){
        String result = "===================================\nNodes:\n===================================\n";
        //GETTING A LIST OF EACH VERTEX
        for (HeuristicNode<T> vertex : disperseMatrix.keySet()){
            result+= vertex.toString() + ": ";
            //GETTING A LIST OF ADJACENT NODES
            for (HeuristicNode<T> adjacentNode : disperseMatrix.get(vertex))
                result += adjacentNode.toString() + " ";
            result += "\n";
        }

        result+= "===================================\nEdges:\n===================================\n";

        for (String edge: edgeCosts.keySet()){
            result += edgeCosts.get(edge).toString()+ "\t<=\t" + edge + "\n";
        }

        return result;
    }
}
