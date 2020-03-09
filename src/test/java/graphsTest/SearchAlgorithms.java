package graphsTest;


import graphs.IGraph;
import graphs.UndirectedGraph;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SearchAlgorithms {
    private static IGraph<Integer> baseGraph;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseGraph = new UndirectedGraph<>();
        baseGraph.addEdge(1, 2);
        baseGraph.addEdge(1, 8);
        baseGraph.addEdge(2, 3);
        baseGraph.addEdge(2, 4);
        baseGraph.addEdge(2, 7);
        baseGraph.addEdge(3, 5);
        baseGraph.addEdge(3, 6);
        baseGraph.addEdge(4, 9);
        baseGraph.addEdge(5, 6);
        baseGraph.addEdge(6, 9);
        baseGraph.addEdge(7, 8);
        baseGraph.addEdge(9, 10);

        System.out.println(
                "Undirected Graph 1\n" +
                        "Number of vertex:\t"+ baseGraph.countVertex().toString() + "\n" +
                        "Number of edges:\t"+ baseGraph.countEdge().toString() + "\n" +
                        "Is connected:\t"+ baseGraph.isConnected().toString() + "\n" +
                        "Is eulerian:\t"+ baseGraph.isEulerian().toString() + "\n" +
                        baseGraph.toString() +
                        "===========================\n"
        );
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        baseGraph = null;
    }


    @Test
    public void Graph_Should_haveBFS(){
        baseGraph.searchBF(1);

        assertThat(
                "Target node not found",
                baseGraph.searchBF(1,6),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_Should_HaveDFS(){
        baseGraph.searchDF(1);

        assertThat(
                "Target node not found",
                baseGraph.searchDF(1,6),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_Should_HaveLDFS(){
        baseGraph.searchLDF(1, 3);

        assertThat(
                "Target node not found limit = 3",
                baseGraph.searchLDF(1,3,6),
                is(equalTo(false))
        );

        System.out.println();

        baseGraph.searchLDF(1, 4);

        assertThat(
                "Target node not found limit = 4",
                baseGraph.searchLDF(1,4,6),
                is(equalTo(true))
        );
    }
}
