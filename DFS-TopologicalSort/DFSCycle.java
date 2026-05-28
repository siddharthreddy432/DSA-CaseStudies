import java.util.*;

public class DFSCycle {

    static final int WHITE = 0;
    static final int GREY = 1;
    static final int BLACK = 2;

    static boolean dfs(
            String u,
            Map<String, List<String>> adj,
            Map<String, Integer> color,
            List<String> order) {

        color.put(u, GREY);

        for (String v : adj.getOrDefault(u, Collections.emptyList())) {

            if (color.get(v) == WHITE) {

                if (dfs(v, adj, color, order))
                    return true;

            } else if (color.get(v) == GREY) {

                System.out.println("Cycle Detected at: " + v);
                return true;
            }
        }

        color.put(u, BLACK);
        order.add(u);

        return false;
    }

    public static void main(String[] args) {

        Map<String, List<String>> adj = new HashMap<>();

        adj.put("auth", Arrays.asList("ledger"));
        adj.put("payments", Arrays.asList("auth", "fraud"));
        adj.put("kyc", Arrays.asList("auth"));
        adj.put("ledger", Arrays.asList("fraud"));
        adj.put("fraud", Arrays.asList("notify", "ledger"));
        adj.put("admin-ui", Arrays.asList("payments", "kyc"));
        adj.put("customer-ui", Arrays.asList("payments"));
        adj.put("gateway", Arrays.asList("admin-ui", "customer-ui"));
        adj.put("notify", Arrays.asList("gateway"));

        Map<String, Integer> color = new HashMap<>();
        List<String> order = new ArrayList<>();

        for (String v : adj.keySet()) {
            color.put(v, WHITE);
        }

        boolean cycle = false;

        for (String u : adj.keySet()) {

            if (color.get(u) == WHITE) {

                if (dfs(u, adj, color, order)) {
                    cycle = true;
                    break;
                }
            }
        }

        if (!cycle) {

            Collections.reverse(order);

            System.out.println("Topological Order:");
            System.out.println(order);

        } else {

            System.out.println("Topological Sort not possible.");
        }
    }
}