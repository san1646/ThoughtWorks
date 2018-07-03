package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
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
        if (from.adjacencies == null) {
            from.adjacencies = new Edge[]{edge};
        } else {
            int l = from.adjacencies.length;
            from.adjacencies = Arrays.copyOf(from.adjacencies, l + 1);
            from.adjacencies[from.adjacencies.length - 1] = edge;
        }

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

        builder.append("Edges: ");
        for (Edge edge : edges) {
            builder.append(edge.toString());
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
            if (from.getOut().keySet().contains(to)) {
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
            i = first.getOut().keySet().iterator();

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
    int countPathHelper(Node start, Node end, Map<String, Boolean> visited, int count) {
        // current node is visited
        visited.put(start.getName(), true);

        if (start.equals(end)) {
            count++;
        } else {
            Iterator<Node> itr = start.getOut().keySet().iterator();
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

    /**
     * Counts the paths between start and end nodes
     * 
     * @param s
     * @param e
     * @param stops
     * @return
     */
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
            start = start.getOut().keySet().iterator().next();
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

    public int countPathsWithStops(String s, String e, int stop, Map<String, Boolean> visited) {
        // Base cases
        if (stop == 0 && s.equalsIgnoreCase(e)) {
            return 1;
        }

        if (stop <= 0) {
            return 0;
        }

        Node start = getNode(s);
        Node end = getNode(e);

        int count = 0;

        visited.put(start.getName(), true);

        if (start.equals(end)) {
            count++;
        } else {
            Iterator<Node> itr = start.getOut().keySet().iterator();
            while (itr.hasNext()) {
                Node n = itr.next();
                if (!visited.containsKey(n.getName()) || !visited.get(n.getName())) {
                    count += countPathsWithStops(n.getName(), end.getName(), stop - 1, visited);
                }
            }
        }

        visited.put(start.getName(), false);

        return count;
    }

    /**
     * This uses Djikstra's algorithm
     * 
     * @param source
     * @return
     */
    public void calculateShortestPath(Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Integer> nodeVal : currentNode.getOut().entrySet()) {
                Node adjNode = nodeVal.getKey();
                // get the length of edge from current node to the nodeVal
                Integer edgeLength = getEdge(currentNode, nodeVal.getKey()).getLength();
                if (!settledNodes.contains(adjNode)) {
                    calculateMinimumDistance(adjNode, edgeLength, currentNode);
                    unsettledNodes.add(adjNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node closestNode = unsettledNodes.iterator().next();
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int distance = node.getDistance();
            if (distance < lowestDistance) {
                lowestDistance = distance;
                closestNode = node;
            }
        }
        return closestNode;
    }

    private static void calculateMinimumDistance(Node currNode,
            Integer edgeLength, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeLength < currNode.getDistance()) {
            currNode.setDistance(sourceDistance + edgeLength);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            currNode.setShortestPath(shortestPath);
        }
    }

    public int getLengthOfShortestPath(String start, String end) {
        Node a = getNode("A");
        calculateShortestPath(a);

        // LinkedList<Node> path = new LinkedList<Node>();
        Node startNode = getNode(start);
        Node n = startNode;
        if (!n.getOut().keySet().iterator().hasNext()) {
            return 0;
        }
        // path.add(n);
        Integer distance = 0;

        // while node has outgoing connections...
        Node endNode = getNode(end);
        while (!n.getOut().entrySet().isEmpty()) {
            Node min = null;
            Set<Node> outs = n.getOut().keySet();
            if (outs.contains(endNode)) {
                distance += endNode.getDistance();
                return distance;
            } else {
                for (Node node : outs) {
                    if (min == null || node.getDistance() < distance) {
                        min = node;
                    }
                }
            }

            distance += min.getDistance();
            n = min;

            if (n.equals(startNode)) {
                return Integer.MAX_VALUE;
            }
        }

        return distance;
    }

    public void setDijkstras(String s) {
        Node source = getNode(s);
        source.setDistance(0);
        PriorityQueue<Node> nodeQ = new PriorityQueue<Node>();
        nodeQ.add(source);

        while (!nodeQ.isEmpty()) {
            Node u = nodeQ.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Node n = e.getTo();
                Integer weight = e.getLength();
                Integer distanceThroughU = u.getDistance() + weight;
                if (distanceThroughU < n.getDistance()) {
                    nodeQ.remove(n);

                    n.setDistance(distanceThroughU);
                    n.previous = u;
                    nodeQ.add(n);
                }
            }
        }
    }

    /**
     * Finds the path to the target using {@link #setDijkstras(String)}
     * 
     * @param target
     * @return
     */
    public List<Node> getShortestPathTo(String t) {
        Node target = getNode(t);
        List<Node> path = new ArrayList<Node>();
        for (Node node = target; node != null; node = node.previous)
            path.add(node);

        Collections.reverse(path);
        return path;
    }

    public List<Node> pathsWithMaxCost(String s, String e) {

        List<Node> paths = new ArrayList<>();
        Node start = getNode(s);
        Node end = getNode(e);
        /*
         * if (s.equals(e)) {
         * paths.add(start);
         * paths.add(end);
         * return paths;
         * }
         */

        /*
         * if (start.equals(end)) {
         * start = start.getOut().keySet().iterator().next();
         * }
         */

        // Mark all the nodes as not visited
        Map<String, Boolean> visited = new HashMap<>();

        paths = printPathsHelper(start, end, visited, paths);
        return paths;
    }

    /**
     * Print the number of paths between 2 nodes
     * 
     * @param start
     * @param end
     * @return
     */
    private List<Node> printPathsHelper(Node start, Node end, Map<String, Boolean> visited, List<Node> paths) {
        // current node is visited
        visited.put(start.getName(), true);

        Integer cost = 0;
        if (start.equals(end)) {
            System.out.print("Path:::");
            for (Node p : paths) {
                System.out.print(" " + p.getName());
                cost += p.getDistance();
            }
        }

        if (cost >= 30) {
            return paths;
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Node n : start.getOut().keySet()) {
            if (!visited.containsKey(n.getName()) || !visited.get(n.getName())) {

                // store current node
                paths.add(n);
                printPathsHelper(n, end, visited, paths);

                // remove current node
                paths.remove(n);
            }
        }

        // Mark the current node
        visited.put(start.getName(), false);
        return paths;
    }

}
