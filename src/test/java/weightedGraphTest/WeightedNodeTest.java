package weightedGraphTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import weightedGraph.GeoNode;
import weightedGraph.HeuristicNode;
import weightedGraph.IWeightedGraph;
import weightedGraph.WeightedGraph;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class WeightedNodeTest {
    private static HeuristicNode<String>
            hermosillo, chihuahua,  aguascalientes,
            pachuca,    guanajuato, puebla,
            acapulco,   cuernavaca, guadalajara,
            tepic,      reynosa,    veracruz,
            zacatecas;
    private static IWeightedGraph<String> roadMap;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        hermosillo = new GeoNode<>("Hermosillo",	29.1698845,-110.9105424);
        chihuahua = new GeoNode<>("Chihuahua",28.673958, -106.077674);
        aguascalientes = new GeoNode<>("Aguascalientes",21.934298, -102.340528);
        pachuca = new GeoNode<>("Pachuca",20.096212, -98.767404);
        guanajuato = new GeoNode<>("Guanajuato",21.167245, -101.714404);
        puebla = new GeoNode<>("Puebla",19.018408, -98.242226);
        acapulco = new GeoNode<>("Acapulco",16.846157, -99.915183);
        cuernavaca = new GeoNode<>("Cuernavaca",18.805735, -99.221660);
        guadalajara = new GeoNode<>("Guadalajara",20.735288, -103.455513);
        tepic = new GeoNode<>("Tepic",21.512014, -104.891528);
        reynosa = new GeoNode<>("Reynosa",25.916868, -97.569279);
        veracruz = new GeoNode<>("Veracruz",18.891930, -96.979178);
        zacatecas = new GeoNode<>("Zacatecas",22.762637, -102.534873);

        roadMap = new WeightedGraph<>(true);
        roadMap.addEdge(hermosillo,chihuahua,690.0);
        roadMap.addEdge(hermosillo,aguascalientes,1533.0);
        roadMap.addEdge(aguascalientes,chihuahua,946.0);
        roadMap.addEdge(aguascalientes,pachuca,510.0);
        roadMap.addEdge(guanajuato, pachuca, 373.0);
        roadMap.addEdge(puebla,acapulco,425.0);
        roadMap.addEdge(acapulco, cuernavaca, 290.0);
        roadMap.addEdge(pachuca, cuernavaca, 180.0);
        roadMap.addEdge(guadalajara, tepic, 204.0);
        roadMap.addEdge(reynosa, chihuahua, 1025.0);
        roadMap.addEdge(reynosa, guanajuato, 841.0);
        roadMap.addEdge(reynosa, pachuca, 869.0);
        roadMap.addEdge(hermosillo, zacatecas, 1420.0);
        roadMap.addEdge(zacatecas, aguascalientes, 118.0);
        roadMap.addEdge(aguascalientes,guadalajara,220.0);
        roadMap.addEdge(cuernavaca, puebla, 158.0);
        roadMap.addEdge(cuernavaca, veracruz, 435.0);
        roadMap.addEdge(veracruz, puebla, 274.0);
        roadMap.addEdge(zacatecas, guanajuato, 300.0);
        roadMap.addEdge(tepic, zacatecas, 543.0);
        roadMap.addEdge(pachuca, veracruz, 382.0);


        //System.out.println("Map\n" + roadMap.toString() + "===========================\n");
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        roadMap = null;
    }


    @Test
    public void Graph_Should_implementGreedySearch(){
        String result = roadMap.greedySearch(cuernavaca, reynosa);

        //System.out.println(result);

        assertThat(
                "Reynosa was node not found",
                result,
                is(not(equalTo("Reynosa was not found")))
        );
    }

    @Test
    public void Graph_Should_implementAStarSearch(){
        String result = roadMap.aStarSearch(cuernavaca, reynosa);

        //System.out.println(result);

        assertThat(
                "Reynosa was node not found",
                result,
                is(not(equalTo("Reynosa was not found")))
        );
    }

}
