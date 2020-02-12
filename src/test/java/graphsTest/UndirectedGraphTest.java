package graphsTest;

import graphs.IGraph;
import graphs.UndirectedGraph;
import org.junit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UndirectedGraphTest {
    private static IGraph<String> europe, europe_2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        europe = new UndirectedGraph<>();
        europe.addEdge("Portugal", "Portugal");
        europe.addEdge("Portugal", "Spain");
        europe.addEdge("Spain", "France");
        europe.addEdge("France", "Germany");
        europe.addEdge("France", "Switzerland");
        europe.addEdge("Switzerland", "Germany");
        europe.addEdge("Switzerland", "Italy");
        europe.addEdge("Germany", "Belgium");
        europe.addEdge("Belgium", "Germany");
        europe.addEdge("Belgium", "Belgium");
        europe.addEdge("Belgium", "France");
        europe.addEdge("Germany", "Netherlands");
        europe.addEdge("Belgium", "Netherlands");
        europe.addEdge("Netherlands", "Netherlands");

        System.out.println(
                "Undirected Graph 1\n" +
                europe.toString() +
                "===========================\n"
        );

        europe_2 = new UndirectedGraph<>();
        europe_2.addEdge("Portugal", "Portugal");
        europe_2.addEdge("Portugal", "Spain");
        europe_2.addEdge("Spain", "France");
        europe_2.addEdge("France", "Germany");
        europe_2.addEdge("France", "Switzerland");
        europe_2.addEdge("France", "Italy");
        europe_2.addEdge("Germany", "Switzerland");
        europe_2.addEdge("Switzerland", "Italy");

        System.out.println(
                "Undirected Graph 2\n" +
                 europe_2.toString() +
                 "===========================\n"
        );
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        europe = null;
    }


    @Test
    public void Graph_Should_AddNodes(){
        assertThat(
                "The size did not match",
                europe.countVertex(),
                is(equalTo(8))
        );
    }
    @Test
    public void Graph_ShouldNot_DuplicateInformation(){
        europe.addEdge("Germany", "Belgium");
        europe.addEdge("Belgium", "Germany");
        assertThat(
                "Added Duplicate Nodes",
                europe.countVertex(),
                is(equalTo(8))
        );
        assertThat(
                "Added Duplicate Edge",
                europe.countEdge(),
                is(equalTo(13))
        );
    }
    @Test
    public void Graph_Should_AllowEdgeWithSelf(){
        assertThat(
                "Does not contain <Belgium,Belgium>",
                europe.hasEdge("Belgium", "Belgium"),
                is(equalTo(true))
        );
        assertThat(
                "Does not contain <Netherlands,Netherlands>",
                europe.hasEdge("Netherlands", "Netherlands"),
                is(equalTo(true))
        );
    }
    @Test
    public void Graph_Should_CountEdges(){
        assertThat(
                "Self edges are not counted correctly",
                europe.countEdge(),
                is(not(equalTo(11)))
        );
        assertThat(
                "Incorrect number of edges",
                europe.countEdge(),
                is(equalTo(13))
        );
    }
    @Test
    public void Graph_Should_showEulerianPath(){
        assertThat(
                "Detects eulerian path when there is no path",
                europe.isEulerian(),
                is(not(equalTo(true)))
        );
        assertThat(
                "Does not detect eulerian path",
                europe_2.isEulerian(),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_Should_calculateDegree(){
        assertThat(
                "Does not calculate OutDegree",
                europe_2.vertexOutDegree("France"),
                is(equalTo(4))
        );
        assertThat(
                "OutDegree does not match InDegree",
                europe_2.vertexOutDegree("France"),
                is(equalTo(europe_2.vertexInDegree("France")))
        );
    }

    @Test
    public void Graph_Should_detectIfConnected(){
        assertThat(
                "Does not detect if graph is connected",
                europe_2.isConnected("Germany"),
                is(equalTo(true))
        );
        assertThat(
                "Does not detect if graph is connected",
                europe.isConnected("Germany"),
                is(equalTo(true))
        );
    }
}
