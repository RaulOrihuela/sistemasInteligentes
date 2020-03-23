package weightedGraphTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import weightedGraph.GeoNode;
import weightedGraph.HeuristicNode;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GeoNodeTest {
    private static HeuristicNode<String> cuernavaca, puebla, hermosillo, cuernavacaRepeat;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        cuernavaca = new GeoNode<>("Cuernavaca",18.805735, -99.221660);
        puebla = new GeoNode<>("Puebla", 19.018408, -98.242226);
        hermosillo = new GeoNode<>("Hermosillo", 29.1698845,-110.9105424);
        cuernavacaRepeat = new GeoNode<>("Cuernavaca",18.805735, -99.221660);
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        cuernavaca = null;
        puebla = null;
        hermosillo = null;
        cuernavacaRepeat = null;
    }


    @Test
    public void Node_Should_NotCreateSymmetricPair(){
        assertThat(
                "Symmetry found",
                cuernavaca.edgeString(puebla),
                is(not(equalTo(puebla.edgeString(cuernavaca))))
        );

        assertThat(
                "Symmetry found",
                cuernavaca.edgeString(hermosillo),
                is(not(equalTo(hermosillo.edgeString(cuernavaca))))
        );

        assertThat(
                "Symmetry found",
                hermosillo.edgeString(puebla),
                is(not(equalTo(puebla.edgeString(hermosillo))))
        );
    }

    @Test
    public void Node_Should_beHashMapCompatible(){
        assertThat(
                "Not equal",
                cuernavaca,
                is(equalTo(cuernavacaRepeat))
        );

        assertThat(
                "Not equal",
                cuernavaca.hashCode(),
                is(equalTo(cuernavacaRepeat.hashCode()))
        );
    }

    @Test
    public void Node_Should_evaluateDistance(){

        assertThat(
                "Heuristic value should be symmetrical",
                cuernavaca.heuristicValue(hermosillo),
                is(equalTo(hermosillo.heuristicValue(cuernavaca)))
        );

        assertThat(
                "Hermosillo should have greater heuristic value",
                cuernavaca.heuristicValue(hermosillo),
                is(greaterThan(cuernavaca.heuristicValue(puebla)))
        );

        assertThat(
                "Hermosillo should have greater heuristic value",
                puebla.heuristicValue(hermosillo),
                is(greaterThan(puebla.heuristicValue(cuernavaca)))
        );

        assertThat(
                "Same node should have heuristic value of 0",
                cuernavaca.heuristicValue(cuernavaca),
                is(equalTo(0.0))
        );
    }


}
