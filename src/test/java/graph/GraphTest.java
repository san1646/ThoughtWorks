package graph;

import org.testng.annotations.Test;

public class GraphTest {

  @Test
  public void addNode() {
    //throw new RuntimeException("Test not implemented");
      Graph g = new Graph("first");
      Node a = new Node("A");
      Node b = new Node("B");
      Node c = new Node("C");
      
      a.addAdjacentNode(c, 5);
      
      g.addToGraph(a, b, 5);
      g.addToGraph(b, c, 4);
      //g.addNode(a.adjacentNodes.keySet().iterator().next());
      System.out.println(g.toString());
  }
}
