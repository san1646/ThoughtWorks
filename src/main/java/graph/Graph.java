package graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    String name;
    Set<Node> nodes = new HashSet<Node>();
    Set<Edge> edges = new HashSet<Edge>();

    public void addNode(Node node) {
	if (this.nodes == null) {
	    this.nodes = new HashSet<Node>();
	}
	nodes.add(node);
    }

    public Graph(String name) {
	this.name = name;
    }

    public void addToGraph(Node from, Node to, Integer length) {
	Edge edge = new Edge();
	edge.setFrom(from);
	edge.setTo(to);
	edge.setLength(length);
	edges.add(edge);
    }
    
    public Integer findEdgeLength(Node from, Node to) {
	for(Edge edge: edges) {
	    if(edge.from.getName().equalsIgnoreCase(from.getName())
		    && edge.to.getName().equalsIgnoreCase(to.getName())) {
		return edge.length;
	    }
	}
	return null;
    }
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	// TODO Auto-generated method stub
	builder.append(this.name);
	builder.append(" [");
	
	for (Node node : nodes) {
	    builder.append(node.getName());
	    builder.append(", ");
	    
	}
	
	for (Edge edge : edges) {
	    builder.append(edge);
	    builder.append(", ");
	    
	}
	
	
	builder.append("] ");
	return builder.toString();
    }
}
