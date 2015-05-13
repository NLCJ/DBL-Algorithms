
import java.util.*;

/**
 *
 * @author Stefan Habets
 */
public final class TwoSat {

    /**
     * Given as input a list of clauses representing a 2-CNF formula, returns
     * whether that formula is satisfiable.
     *
     * @param formula The input 2-CNF formula.
     * @return Whether the formula has a satisfying assignment.
     */
    public static <T> Literal isSatisfiable(List<Clause<T>> formula) {
        /* Begin by populating a set of all the variables in this formula. */
        Set<T> variables = new HashSet<T>();
        for (Clause<T> clause : formula) {
            variables.add(clause.first().value());
            variables.add(clause.second().value());
        }

        System.out.println(variables.toString());

        /* Construct the directed graph of implications.  Begin by creating the
         * nodes.
         */
        DirectedGraph<Literal<T>> implications = new DirectedGraph<Literal<T>>();
        for (T variable : variables) {
            /* Add both the variable and its negation. */
            implications.addNode(new Literal<T>(variable, true));
            implications.addNode(new Literal<T>(variable, false));
        }

        /* From each clause (A or B), add two clauses - (~A -> B) and (~B -> A)
         * to the graph as edges.
         */
        for (Clause<T> clause : formula) {
            implications.addEdge(clause.first().negation(), clause.second());
            implications.addEdge(clause.second().negation(), clause.first());
        }

        /* Compute the SCCs of this graph using Kosaraju's algorithm. */
        Map<Literal<T>, Integer> scc = SCC.stronglyConnectedComponents(implications);

        /* Finally, check whether any literal and its negation are in the same
         * strongly connected component.
         */
        for (T variable : variables) {
            if (scc.get(new Literal<T>(variable, true)).equals(scc.get(new Literal<T>(variable, false)))) {
                return new Literal(variable, true);
            }
        }
        /* If not, the formula must be satisfiable. */
        return null;
    }
}
