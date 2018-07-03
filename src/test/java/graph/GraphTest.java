package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 
 * @author sanket.bharaswadkar
 *
 */
public class GraphTest {

    private Graph buildGraph() {
        Graph g = new Graph("first");
        Node a = new Node("A");

        g.addNode(a);
        Node b = new Node("B");
        g.addNode(b);
        Node c = new Node("C");
        g.addNode(c);
        Node d = new Node("D");
        g.addNode(d);
        Node e = new Node("E");
        g.addNode(e);

        // neighbors
        a.addOut(b);
        b.addOut(c);
        c.addOut(d);
        d.addOut(c);
        d.addOut(e);
        a.addOut(d);
        c.addOut(e);
        e.addOut(b);
        a.addOut(e);

        g.addToGraph(a, b, 5);
        g.addToGraph(b, c, 4);
        g.addToGraph(c, d, 8);
        g.addToGraph(d, c, 8);
        g.addToGraph(d, e, 6);
        g.addToGraph(a, d, 5);
        g.addToGraph(c, e, 2);
        g.addToGraph(e, b, 3);
        g.addToGraph(a, e, 7);

        return g;
    }

    @Test
    public void addNode() {
        // throw new RuntimeException("Test not implemented");

        Graph graph = buildGraph();
        System.out.println(graph.toString());

        String[] routeABC = {"A", "B", "C"};
        Integer distanceForABC = Integer.valueOf(graph.findRouteDistance(routeABC));
        System.out.print(" 1. The distance of the route A-B-C :::");
        System.out.println("Route: " + Arrays.toString(routeABC) + ": " + distanceForABC);
        Assert.assertEquals(distanceForABC.intValue(), 9);

        String[] routeAD = {"A", "D"};
        Integer distanceForAD = Integer.valueOf(graph.findRouteDistance(routeAD));
        System.out.print(" 2. The distance of the route A-D :::");
        System.out.println("Route: " + Arrays.toString(routeAD) + ": " + distanceForAD);
        Assert.assertEquals(distanceForAD.intValue(), 5);

        String[] routeADC = {"A", "D", "C"};
        Integer distanceForADC = Integer.valueOf(graph.findRouteDistance(routeADC));
        System.out.print(" 3. The distance of the route A-D-C :::");
        System.out.println("Route: " + Arrays.toString(routeADC) + ": " + distanceForADC);
        Assert.assertEquals(distanceForADC.intValue(), 13);

        String[] routeAEBCD = {"A", "E", "B", "C", "D"};
        Integer distanceForAEBCD = Integer.valueOf(graph.findRouteDistance(routeAEBCD));
        System.out.print(" 4. The distance of the route A-E-B-C- :::");
        System.out.println("Route: " + Arrays.toString(routeAEBCD) + ": " + distanceForAEBCD);
        Assert.assertEquals(distanceForAEBCD.intValue(), 22);

        String[] routeAED = {"A", "E", "D"};
        String distanceForAED = graph.findRouteDistance(routeAED);
        System.out.print(" 5. The distance of the route A-E-D :::");
        System.out.println("Route: " + Arrays.toString(routeAED) + ": " + distanceForAED);
        Assert.assertEquals(distanceForAED, "NO SUCH ROUTE");

        // untested
        /*
         * System.out.println("find route b/w a-c:");
         * Node a1 = g.getNode("A");
         * Node b1 = g.getNode("C");
         * System.out.println("Nodes: "+a1.getName() + ", "+b1.getName());
         * System.out.println(g.isRoute(a1, b1));
         */

        String start = "A";
        String end = "A";
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " is " + graph.countPaths(start, end, 1));

        start = "B";
        end = "B";
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " is " + graph.countPaths(start, end, 1));

        start = "C";
        end = "C";
        System.out.println(
                " 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).");
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " is " + graph.countPaths(start, end, 1));

        start = "D";
        end = "D";
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " is " + graph.countPaths(start, end, 1));

        start = "E";
        end = "E";
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " is " + graph.countPaths(start, end, 1));

        start = "A";
        end = "C";
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " is " + graph.countPaths(start, end, 1));

        start = "A";
        end = "C";
        Map<String, Boolean> visited = new HashMap<>();
        System.out.println(
                "7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).");
        System.out.println(
                " -- Number of paths between " + start + ", and " + end + " with max 4 stops- "
                        + graph.countPathsWithStops(start, end, 4, visited));
    }

    /**
     * Problem 8
     */
    @Test
    public void graphShortestPath() {
        System.out.println("***");
        Graph graph = buildGraph();
        Node source = graph.getNode("A");
        // graph.calculateShortestPath(source);
        // System.out.println(graph.toString());

        String start = "A";
        String end = "C";
        // Integer minDist = graph.getLengthOfShortestPath(start, end);
        graph.setDijkstras(start);
        System.out.println(graph.toString());
        List<Node> path = graph.getShortestPathTo(end);
        System.out.println("8. The length of the shortest route (in terms of distance to travel) from A to C.");
        Integer minDist = 0;
        for (Node p : path) {
            System.out.print(" " + p.getName());
            minDist += p.getDistance();
        }
        System.out.println();
        System.out.println("Min distance from " + start + " to " + end + " is " + minDist);
    }

    /**
     * Problem 9
     */
    @Test
    public void graphShortestPathBtoB() {
        System.out.println("***");
        Graph graph = buildGraph();

        String start = "B";
        String end = "B";
        graph.setDijkstras(start);
        System.out.println(graph.toString());
        List<Node> path = graph.getShortestPathTo(end);
        System.out.println("9. The length of the shortest route (in terms of distance to travel) from B to B.");
        Integer minDist = 0;
        for (Node p : path) {
            System.out.print(" " + p.getName());
            minDist += p.getDistance();
        }
        System.out.println();
        System.out.println("Min distance from " + start + " to " + end + " is " + minDist);
    }

    /**
     * Problem 10
     */
    @Test
    public void testGetAllRoutesWithDistance() {
        System.out.println("***");
        Graph graph = buildGraph();

        String start = "A";
        String end = "C";

        // set distances
        graph.setDijkstras(start);
        System.out.println(graph.toString());
        List<Node> paths = graph.pathsWithMaxCost(start, end);
        System.out.println(
                " -- 10 Number of paths between " + start + ", and " + end + " is ");

        for (Node p : paths) {
            System.out.print(" " + p.getName());
        }

    }
}
