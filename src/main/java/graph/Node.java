package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sanket.bharaswadkar
 *
 */
public class Node {
    private String name;
    Map<Node, Integer> adjacentNodes = new HashMap<Node, Integer>();

    public Node(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Map<Node, Integer> getAdjacentNodes() {
	return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
	this.adjacentNodes = adjacentNodes;
    }

    public void addAdjacentNode(Node node, Integer weight) {
	if (this.adjacentNodes == null) {
	    this.adjacentNodes = new HashMap<Node, Integer>();
	}
	this.adjacentNodes.put(node, weight);
    }

}
