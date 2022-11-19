import java.util.ArrayList;
class set{
    Integer Dest, Weight;
    public set(Integer destination, Integer weight) {
        this.Dest = destination;
        this.Weight = weight;
    }
    Integer Dest(){
        return this.Dest;
    }
    Integer Weight(){
        return this.Weight;
    }
}
class createGraph {
    ArrayList<ArrayList<set>> g = new ArrayList<>();
    int vertices;
    createGraph(Integer vertices) {
        for (int i = 0; i <= 5; i++) {
            this.g.add(new ArrayList<set>());
            this.vertices = vertices;
        }
    }
    void createEdges(Character source, Character destination, int weight) {
        Integer intSource = source - 'A';
        Integer intDestination = destination - 'A';
        this.g.get(intSource).add(new set(intDestination, weight));
    }
}

public class Graph {
    public static void main(String[] args) {
        calculateAverageDistanceBetweenTwoPoints("A",  "C");
    }
    public static void calculateAverageDistanceBetweenTwoPoints(String X, String Y){
        Integer vertices = 5;
        createGraph givenGraph = new createGraph(vertices);
        givenGraph.createEdges('A', 'B', 12);
        givenGraph.createEdges('A', 'C', 13);
        givenGraph.createEdges('A', 'E', 8);
        givenGraph.createEdges('A', 'D', 11);
        givenGraph.createEdges('D', 'E', 7);
        givenGraph.createEdges('E', 'C', 4);
        givenGraph.createEdges('B', 'C', 3);
        Integer source = X.charAt(0)- 'A';
        Integer destination = Y.charAt(0) - 'A';
        ArrayList<ArrayList<set>> totalPath = new ArrayList<ArrayList<set>>();
        ArrayList<set> path = new ArrayList<set>();
        dfs(givenGraph, totalPath, path, source, destination, 0 );
        int pathCount = totalPath.size();
        int distance = 0;
        for(ArrayList<set> it: totalPath){
            for(set it1 : it) {
                distance += it1.Weight();
            }
        }
        double averageDistance = (double)distance /pathCount;
        System.out.println("Average Distance Between A and C is: "+averageDistance);
    }
    private static void dfs(createGraph givenGraph, ArrayList<ArrayList<set>> totalPath, ArrayList<set> path, Integer source, Integer destination, Integer weight) {
        path.add(new set(source, weight));
        if(source.equals(destination)){
            totalPath.add(new ArrayList<set>(path));
        }
        for(set p : givenGraph.g.get(source)){
            int newSource = p.Dest;
            int newWeight = p.Weight;
            dfs(givenGraph, totalPath, path, newSource, destination, newWeight);
        }
        path.remove(path.size() - 1);
    }
}
