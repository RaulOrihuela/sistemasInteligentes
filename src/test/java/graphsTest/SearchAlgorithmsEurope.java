package graphsTest;

import graphs.IGraph;
import graphs.UndirectedGraph;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SearchAlgorithmsEurope {
    private static IGraph<String> europe_2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        europe_2 = new UndirectedGraph<>();
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
        europe_2 = null;
    }


    @Test
    public void Graph_Should_haveBFS(){
        europe_2.searchBF("Italy");

        assertThat(
                "Target node not found",
                europe_2.searchBF("Italy","Portugal"),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_Should_HaveDFS(){
        europe_2.searchDF("Italy");

        assertThat(
                "Target node not found",
                europe_2.searchDF("Italy","Portugal"),
                is(equalTo(true))
        );
    }

    @Test
    public void Graph_Should_HaveLDFS(){
        europe_2.searchLDF("Italy", 3);

        assertThat(
                "Target node not found limit = 3",
                europe_2.searchLDF("Italy",3,"Portugal"),
                is(equalTo(false))
        );

        System.out.println();

        europe_2.searchLDF("Italy", 4);

        assertThat(
                "Target node not found limit = 4",
                europe_2.searchLDF("Italy",4,"Portugal"),
                is(equalTo(true))
        );
    }
}
