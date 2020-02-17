package graphsTest;

import graphs.IGraph;
import graphs.UndirectedGraph;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GraphTestsInClass {
    private static IGraph<Integer> baseGraph, graph2, graph3, graph4, graph5, graph6;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseGraph = new UndirectedGraph<>();
        baseGraph.addEdge(1, 2);
        baseGraph.addEdge(1, 5);
        baseGraph.addEdge(1, 8);
        baseGraph.addEdge(1, 9);
        baseGraph.addEdge(2, 0);
        baseGraph.addEdge(2, 3);
        baseGraph.addEdge(2, 9);
        baseGraph.addEdge(3, 0);
        baseGraph.addEdge(3, 4);
        baseGraph.addEdge(3, 7);
        baseGraph.addEdge(4, 5);
        baseGraph.addEdge(4, 6);
        baseGraph.addEdge(4, 7);
        baseGraph.addEdge(5, 6);
        baseGraph.addEdge(5, 8);
        baseGraph.addEdge( 6,7);
        baseGraph.addEdge( 6,8);
        baseGraph.addEdge( 7,0);
        baseGraph.addEdge( 8,9);
        baseGraph.addEdge( 9,0);

        graph2 = new UndirectedGraph<>();
        graph2.addEdge(1, 2);
        graph2.addEdge(1, 5);
        graph2.addEdge(1, 8);
        graph2.addEdge(1, 9);
        graph2.addEdge(2, 0);
        graph2.addEdge(2, 3);
        graph2.addEdge(2, 9);
        graph2.addEdge(3, 0);
        graph2.addEdge(3, 4);
        graph2.addEdge(4, 5);
        graph2.addEdge(4, 6);
        graph2.addEdge(5, 6);
        graph2.addEdge(5, 8);
        graph2.addEdge( 6,8);
        graph2.addVertex(7);
        graph2.addEdge( 8,9);
        graph2.addEdge( 9,0);

        graph3 = new UndirectedGraph<>();
        graph3.addEdge(1, 2);
        graph3.addEdge(1, 5);
        graph3.addEdge(1, 8);
        graph3.addEdge(1, 9);
        graph3.addEdge(2, 0);
        graph3.addEdge(2, 3);
        graph3.addEdge(2, 9);
        graph3.addEdge(3, 0);
        graph3.addEdge(3, 4);
        graph3.addEdge(3, 7);
        graph3.addEdge(4, 5);
        graph3.addEdge(4, 6);
        graph3.addEdge(4, 7);
        graph3.addEdge(5, 6);
        graph3.addEdge(5, 8);
        graph3.addEdge( 6,7);
        graph3.addEdge( 6,8);
        graph3.addEdge( 7,0);
        graph3.addEdge( 9,0);

        graph4 = new UndirectedGraph<>();
        graph4.addEdge(1, 2);
        graph4.addEdge(1, 5);
        graph4.addEdge(1, 8);
        graph4.addEdge(1, 9);
        graph4.addEdge(2, 0);
        graph4.addEdge(2, 3);
        graph4.addEdge(2, 9);
        graph4.addEdge(3, 0);
        graph4.addEdge(3, 4);
        graph4.addEdge(3, 7);
        graph4.addEdge(4, 5);
        graph4.addEdge(4, 6);
        graph4.addEdge(4, 7);
        graph4.addEdge(5, 6);
        graph4.addEdge(5, 8);
        graph4.addEdge( 6,8);
        graph4.addEdge( 7,0);
        graph4.addEdge( 9,0);


        graph5 = new UndirectedGraph<>();
        graph5.addEdge(1, 2);
        graph5.addEdge(1, 5);
        graph5.addEdge(1, 8);
        graph5.addEdge(2, 0);
        graph5.addEdge(2, 3);
        graph5.addEdge(3, 0);
        graph5.addEdge(3, 7);
        graph5.addEdge(5, 6);
        graph5.addEdge(5, 8);
        graph5.addEdge( 6,8);
        graph5.addEdge( 7,0);

        graph6 = new UndirectedGraph<>();
        graph6.addEdge(3, 0);
        graph6.addEdge(3, 4);
        graph6.addEdge(3, 7);
        graph6.addEdge(4, 5);
        graph6.addEdge(5, 6);
        graph6.addEdge(5, 8);
        graph6.addEdge( 6,7);
        graph6.addEdge( 6,8);
        graph6.addEdge( 7,0);
        graph6.addEdge( 8,9);
        graph6.addEdge( 9,0);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        baseGraph = null;
    }


    @Test
    public void Graph_01(){
        System.out.println(
                "Undirected Graph 1\n" +
                        "Number of vertex:\t"+ baseGraph.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ baseGraph.countEdge().toString() + "\n" +
                        "Is connected:\t"+ baseGraph.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ baseGraph.isEulerian().toString() + "\n" +
                        baseGraph.toString() +
                        "===========================\n"
        );

        assertThat(
                "The graph is not connected",
                baseGraph.isConnected(),
                is(equalTo(true))
        );
        assertThat(
                "The graph is not eulerian",
                baseGraph.isEulerian(),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_02(){
        System.out.println(
                "Undirected Graph 2\n" +
                        "Number of vertex:\t"+ graph2.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ graph2.countEdge().toString() + "\n" +
                        "Is connected:\t"+ graph2.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ graph2.isEulerian().toString() + "\n" +
                        graph2.toString() +
                        "===========================\n"
        );

        assertThat(
                "The graph is not connected",
                graph2.isConnected(),
                is(equalTo(false))
        );
        assertThat(
                "The graph is not eulerian",
                graph2.isEulerian(),
                is(equalTo(false))
        );
    }

    @Test
    public void Graph_03(){
        System.out.println(
                "Undirected Graph 3\n" +
                        "Number of vertex:\t"+ graph3.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ graph3.countEdge().toString() + "\n" +
                        "Is connected:\t"+ graph3.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ graph3.isEulerian().toString() + "\n" +
                        graph3.toString() +
                        "===========================\n"
        );

        assertThat(
                "The graph is not connected",
                graph3.isConnected(),
                is(equalTo(true))
        );
        assertThat(
                "The graph is not eulerian",
                graph3.isEulerian(),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_04(){
        System.out.println(
                "Undirected Graph 4\n" +
                        "Number of vertex:\t"+ graph4.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ graph4.countEdge().toString() + "\n" +
                        "Is connected:\t"+ graph4.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ graph4.isEulerian().toString() + "\n" +
                        graph4.toString() +
                        "===========================\n"
        );

        assertThat(
                "The graph is not connected",
                graph4.isConnected(),
                is(equalTo(true))
        );
        assertThat(
                "The graph is not eulerian",
                graph4.isEulerian(),
                is(equalTo(false))
        );
    }

    @Test
    public void Graph_05(){
        System.out.println(
                "Undirected Graph 5\n" +
                        "Number of vertex:\t"+ graph5.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ graph5.countEdge().toString() + "\n" +
                        "Is connected:\t"+ graph5.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ graph5.isEulerian().toString() + "\n" +
                        graph5.toString() +
                        "===========================\n"
        );

        assertThat(
                "The graph is not connected",
                graph5.isConnected(),
                is(equalTo(true))
        );
        assertThat(
                "The graph is not eulerian",
                graph5.isEulerian(),
                is(equalTo(false))
        );
    }
    @Test
    public void Graph_06(){
        System.out.println(
                "Undirected Graph 6\n" +
                        "Number of vertex:\t"+ graph6.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ graph6.countEdge().toString() + "\n" +
                        "Is connected:\t"+ graph6.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ graph6.isEulerian().toString() + "\n" +
                        graph6.toString() +
                        "===========================\n"
        );

        assertThat(
                "The graph is not connected",
                graph6.isConnected(),
                is(equalTo(true))
        );
        assertThat(
                "The graph is not eulerian",
                graph6.isEulerian(),
                is(equalTo(false))
        );
    }
}
