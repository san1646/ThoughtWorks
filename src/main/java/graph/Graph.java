package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Weighted directional graph
 * 
 * @author sanket.bharaswadkar
 *
 */
public class Graph {
    static String NO_ROUTE = "NO SUCH ROUTE";
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
            return NO_ROUTE;
        }

        Integer routeDistance = 0;
        // Go till second last node, as # edges = # nodes -1
        for (int i = 0; i < routeNodes.size() - 1; i++) {
            Node from = routeNodes.get(i);
            Node to = routeNodes.get(i + 1);
            if (from.getOut().contains(to)) {
                routeDistance += getEdge(from, to).getLength();
            } else {
                return NO_ROUTE;
            }
        }

        return routeDistance.toString();
    }

    public boolean isRoute(Node start, Node end) {

        // Mark all the nodes as not visited
        // TODO get size of the graph
        Map<String, Boolean> visited = new HashMap<>();

        // Create a queue for Breadth First Search
        LinkedList<Node> queue = new LinkedList<Node>();

        // Mark the current node as visited and enqueue it
        visited.put(start.getName(), true);
        queue.add(start);

        // 'i' will be used to get all adjacent vertices of a vertex
        Iterator<Node> i;
        String route = start.getName();
        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            Node first = queue.poll();
            System.out.println("in FindRoute : queue: " + first.getName());
            Node n;
            i = first.getOut().iterator();

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            while (i.hasNext()) {
                n = i.next();

                // If this adjacent node is the destination node,
                // then return true
                if (n.equals(end)) {
                    // route +=n.getName();
                    return true;
                }

                // Else, continue to do BFS
                if (!visited.containsKey(n.getName())) {
                    visited.put(n.getName(), true);
                    queue.add(n);
                    route += n.getName();
                }
            }
        }

        return false;
    }

    /**
     * The number of trips starting at start and ending at end
     * with max 3 stops
     * 
     * @param start
     * @param end
     * @return
     */
    public int routesStartingAndEnding(Node start, Node end) {

        return 0;
    }

    /**
     * Count the number of paths between 2 nodes
     * 
     * @param start
     * @param end
     * @return
     */
    public int countPathHelper(Node start, Node end, Map<String, Boolean> visited, int count) {

        // current node is visited
        visited.put(start.getName(), true);

        if (start.equals(end)) {
            count++;
        } else {
            Iterator<Node> itr = start.getOut().iterator();
            while (itr.hasNext()) {
                Node n = itr.next();
                if (!visited.containsKey(n.getName()) || !visited.get(n.getName())) {
                    count = countPathHelper(n, end, visited, count);
                }
            }
        }

        visited.put(start.getName(), false);
        return count;
    }

    public int countPaths(String s, String e, int stops) {

        if (stops == 0 && s.equals(e)) {
            return 1;
        }
        if (stops < 0) {
            return 0;
        }

        Node start = getNode(s);
        Node end = getNode(e);
        if (start.equals(end)) {
            start = start.getOut().get(0);
        }

        // Mark all the vertices
        // as not visited
        Map<String, Boolean> visited = new HashMap<>();

        // Call the recursive method
        // to count all paths
        int count = 0;
        count = countPathHelper(start, end, visited, count);
        return count;
    }
}
