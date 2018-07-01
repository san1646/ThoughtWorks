package graph;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 
 * @author sanket.bharaswadkar
 *
 */
public class GraphTest {
    
  @Test
  public void addNode() {
    //throw new RuntimeException("Test not implemented");
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
      
      //neighbors
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
      
      System.out.println(g.toString());
      
      String[] routeABC = {"A","B","C"};
      Integer distanceForABC = Integer.valueOf(g.findRouteDistance(routeABC));
      System.out.println("Route: "+Arrays.toString(routeABC) + ": "+distanceForABC);
      Assert.assertEquals(distanceForABC.intValue(), 9);
      
      
      
      String[] routeAD = {"A","D"};
      Integer distanceForAD = Integer.valueOf(g.findRouteDistance(routeAD));
      System.out.println("Route: "+Arrays.toString(routeAD) + ": "+distanceForAD);
      Assert.assertEquals(distanceForAD.intValue(), 5);
      
      
      String[] routeADC = {"A","D", "C"};
      Integer distanceForADC = Integer.valueOf(g.findRouteDistance(routeADC));
      System.out.println("Route: "+Arrays.toString(routeADC) + ": "+distanceForADC);
      Assert.assertEquals(distanceForADC.intValue(), 13);
      
      String[] routeAEBCD = {"A","E", "B", "C", "D"};
      Integer distanceForAEBCD = Integer.valueOf(g.findRouteDistance(routeAEBCD));
      System.out.println("Route: "+Arrays.toString(routeAEBCD) + ": "+distanceForAEBCD);
      Assert.assertEquals(distanceForAEBCD.intValue(), 22);
      
      String[] routeAED = {"A","E", "D"};
      String distanceForAED = g.findRouteDistance(routeAED);
      System.out.println("Route: "+Arrays.toString(routeAED) + ": "+distanceForAED);
      Assert.assertEquals(distanceForAED, "NO SUCH ROUTE");

      //untested
      /*System.out.println("find route b/w a-c:");
      Node a1 = g.getNode("A");
      Node b1 = g.getNode("C");
      System.out.println("Nodes: "+a1.getName() + ", "+b1.getName());
      System.out.println(g.isRoute(a1, b1));*/
      
      String start = "C";
      String end= "C";
      System.out.println("#Path between "+start+", and "+end+" is "+g.countPaths(start, end, 1));
  }
}
