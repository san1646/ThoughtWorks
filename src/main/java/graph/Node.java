package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author sanket.bharaswadkar
 *
 */
public class Node {
    private String name;
    List<Node> in = new ArrayList<>();
    List<Node> out = new ArrayList<>();

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
            temp += inNode.name + " ";
        temp += "Out: ";
        for (Node outNode : out)
            temp += outNode.name + " ";
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

    public List<Node> getOut() {
        return out;
    }

    public void setOut(List<Node> out) {
        this.out = out;
    }

    public void addOut(Node in) {
        this.out.add(in);
    }
    
    //TODO equals and hashcode

}
