package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author sanket.bharaswadkar
 *
 */
public class Node implements Comparable<Node> {
    private String name;
    List<Node> in = new ArrayList<>();
    Map<Node, Integer> out = new HashMap<>();
    public Edge[] adjacencies;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    public Node previous;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInNodes(List<Node> inComingNodes) {
        this.in = inComingNodes;
    }

    @Override
    public String toString() {
        String temp = "";
        temp += "[Node: " + name + " : ";
        temp += " In: ";
        for (Node inNode : in)
            temp += " Name=" + inNode.name + ", dist=" + inNode.getDistance();
        temp += "Out: ";
        for (Node outNode : out.keySet())
            temp += " Name=" + outNode.name + ", dist=" + outNode.getDistance();
        temp += " ]";
        return temp;
    }

    public List<Node> getIn() {
        return in;
    }

    public void setIn(List<Node> in) {
        this.in = in;
    }

    public void addIn(Node in) {
        this.in.add(in);
    }

    public Map<Node, Integer> getOut() {
        return out;
    }

    public void setOut(Map<Node, Integer> out) {
        this.out = out;
    }

    public void addOut(Node in, Integer distance) {
        this.out.put(in, distance);
    }

    public void addOut(Node in) {
        addOut(in, Integer.MAX_VALUE);
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return Integer.compare(distance, o.distance);
    }

    // TODO equals and hashcode

}
