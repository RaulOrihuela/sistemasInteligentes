package graphsTest;

import graphs.IGraph;
import graphs.DirectedGraph;
import org.junit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DirectedGraphTest {
    private static IGraph<String> europe;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        europe = new DirectedGraph<>();
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
                "Directed Graph 1\n" +
                        europe.toString() +
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
        assertThat(
                "Added Duplicate Vertex",
                europe.countVertex(),
                is(equalTo(8))
        );
        assertThat(
                "Added Duplicate Nodes",
                europe.countEdge(),
                is(equalTo(14))
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
                "Incorrect number of edges",
                europe.countEdge(),
                is(equalTo(14))
        );
    }
}
