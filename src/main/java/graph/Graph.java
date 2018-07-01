package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Weighted directional graph
 * 
 * @author sanket.bharaswadkar
 *
 */
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
        for (Edge edge : edges) {
            if (edge.getFrom().getName().equalsIgnoreCase(from.getName())
                    && edge.getTo().getName().equalsIgnoreCase(to.getName())) {
                return edge.getLength();
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
            builder.append(" ");
            builder.append(node.toString());
            builder.append(", ");

        }

        for (Edge edge : edges) {
            builder.append("Edge:");
            builder.append(edge.getLength());
            builder.append(", ");

        }

        builder.append("] ");
        return builder.toString();
    }

    public Node getNode(String name) {
        for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
            Node node = (Node) iterator.next();
            if (node.getName().equalsIgnoreCase(name)) {
                return node;
            }
        }
        return null;
    }

    /**
     * TODO move to edge class as boolean edge.connects(from, to)
     * 
     * @param from
     * @param to
     * @return
     */
    public Edge getEdge(Node from, Node to) {
        for (Edge edge : edges) {
            if (edge.getFrom().equals(from) && edge.getTo().equals(to)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Get the list of nodes from the node names
     * 
     * @param route
     * @return
     */
    public List<Node> getNodesFromNames(String[] route) {
        List<Node> routeNodes = new ArrayList<>();
        for (int i = 0; i < route.length; i++) {
            Node n = getNode(route[i]);
            if (n != null) {
                routeNodes.add(n);
            } else {
                return null;
            }
        }
        return routeNodes;
    }

    /**
     * Find the distance for the given route
     * 
     * @param route
     * @return
     */
    public String findRouteDistance(String[] route) {
        List<Node> routeNodes = getNodesFromNames(route);
        if (routeNodes == null) {
            return "NO SUCH ROUTE";
        }

        Integer routeDistance = 0;
        // Go till second last node, as # edges = # nodes -1
        for (int i = 0; i < routeNodes.size() - 1; i++) {
            Node from = routeNodes.get(i);
            Node to = routeNodes.get(i + 1);
            if (from.getOut().contains(to)) {
                routeDistance += getEdge(from, to).getLength();
            } else {
                return "NO SUCH ROUTE";
            }
        }

        return routeDistance.toString();
    }
}
