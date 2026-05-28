import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}

public class Kruskal {
    static int[] parent = new int[7];

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return find(parent[x]);
    }

    static void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    public static void main(String[] args) {

        String[] vertices = {"M","K","W","S","E","Y","H"};

        Edge[] edges = {
            new Edge(3,4,4),
            new Edge(1,2,5),
            new Edge(2,3,6),
            new Edge(0,4,7),
            new Edge(0,1,8),
            new Edge(4,5,8),
            new Edge(0,5,9),
            new Edge(5,6,9),
            new Edge(0,3,10),
            new Edge(0,6,11),
            new Edge(0,2,12),
            new Edge(6,1,14)
        };

        Arrays.sort(edges);

        for(int i=0;i<7;i++)
            parent[i]=i;

        int total = 0;

        System.out.println("Edges in MST:");

        for(Edge e : edges) {

            int x = find(e.src);
            int y = find(e.dest);

            if(x != y) {
                union(x,y);

                System.out.println(
                    vertices[e.src] + " - " +
                    vertices[e.dest] + " : " +
                    e.weight
                );

                total += e.weight;
            }
        }

        System.out.println("Total Cost = " + total);
    }
}