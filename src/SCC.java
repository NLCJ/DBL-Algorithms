
import java.util.*;

/**
 *
 * @author Stefan Habets
 */
public final class SCC {

    /**
     * Given a directed graph, returns a map from the nodes in that graph to
     * integers representing the strongly connected components. Each node's
     * number will be the same as the numbers of each other node in its strongly
     * connected component. The enumeration will start at zero and increase by
     * one for each strongly connected component.
     *
     * @param g A directed graph.
     * @return A map from the nodes of that graph to integers indicating to
     * which connected component they belong.
     */
    public static <T> Map<T, Integer> stronglyConnectedComponents(DirectedGraph<T> g) {
        /* Run a depth-first search in the reverse graph to get the order in
         * which the nodes should be processed.
         */
        Stack<T> visitOrder = dfsVisitOrder(graphReverse(g));

        /* Now we can start listing connected components.  To do this, we'll
         * create the result map, as well as a counter keeping track of which
         * DFS iteration this is.
         */
        Map<T, Integer> result = new HashMap<T, Integer>();
        int iteration = 0;

        /* Continuously process the nodes from the queue by running a DFS
         * from each unmarked node we encounter.
         */
        while ( ! visitOrder.isEmpty()) {
            /* Grab the last node.  If we've already labeled it, skip it and
             * move on.
             */
            T startPoint = visitOrder.pop();
            if (result.containsKey(startPoint)) {
                continue;
            }

            /* Run a DFS from this node, recording everything we visit as being
             * at the current level.
             */
            markReachableNodes(startPoint, g, result, iteration);

            /* Bump up the number of the next SCC to label. */
             ++ iteration;
        }

        return result;
    }

    /**
     * Given a directed graph, returns the reverse of that graph.
     *
     * @param g The graph to reverse.
     * @return The reverse graph.
     */
    public static <T> DirectedGraph<T> graphReverse(DirectedGraph<T> g) {
        DirectedGraph<T> result = new DirectedGraph<T>();

        /* Copy over the nodes. */
        for (T node : g) {
            result.addNode(node);
        }

        /* Flip all the edges. */
        for (T node : g) {
            for (T endpoint : g.edgesFrom(node)) {
                result.addEdge(endpoint, node);
            }
        }

        return result;
    }

    /**
     * Given a graph, returns a queue containing the nodes of that graph in the
     * order in which a DFS of that graph finishes expanding the nodes.
     *
     * @param g The graph to explore.
     * @return A stack of nodes in the order in which the DFS finished exploring
     * them.
     */
    public static <T> Stack<T> dfsVisitOrder(DirectedGraph<T> g) {
        /* The resulting ordering of the nodes. */
        Stack<T> result = new Stack<T>();

        /* The set of nodes that we've visited so far. */
        Set<T> visited = new HashSet<T>();

        /* Fire off a DFS from each node. */
        for (T node : g) {
            recExplore(node, g, result, visited);
        }

        return result;
    }

    /**
     * Recursively explores the given node with a DFS, adding it to the output
     * list once the exploration is complete.
     *
     * @param node The node to start from.
     * @param g The graph to explore.
     * @param result The final listing of the node ordering.
     * @param visited The set of nodes that have been visited so far.
     */
    private static <T> void recExplore(T node, DirectedGraph<T> g,
            Stack<T> result, Set<T> visited) {
        /* If we've already been at this node, don't explore it again. */
        if (visited.contains(node)) {
            return;
        }

        /* Otherwise, mark that we've been here. */
        visited.add(node);

        /* Recursively explore all the node's children. */
        for (T endpoint : g.edgesFrom(node)) {
            recExplore(endpoint, g, result, visited);
        }

        /* We're done exploring this node, so add it to the list of visited
         * nodes.
         */
        result.push(node);
    }

    /**
     * Recursively marks all nodes reachable from the given node by a DFS with
     * the current label.
     *
     * @param node The starting point of the search.
     * @param g The graph in which to run the search.
     * @param result A map in which to associate nodes with labels.
     * @param label The label that we should assign each node in this SCC.
     */
    public static <T> void markReachableNodes(T node, DirectedGraph<T> g,
            Map<T, Integer> result,
            int label) {
        /* If we've visited this node before, stop the search. */
        if (result.containsKey(node)) {
            return;
        }

        /* Otherwise label the node with the current label, since it's
         * trivially reachable from itself.
         */
        result.put(node, label);

        /* Explore all nodes reachable from here. */
        for (T endpoint : g.edgesFrom(node)) {
            markReachableNodes(endpoint, g, result, label);
        }
    }
}
