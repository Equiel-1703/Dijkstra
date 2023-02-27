import java.security.InvalidParameterException;

public class GrafoConsoleInterface {
    private Console c;
    private Grafo g;

    public GrafoConsoleInterface(Console c, Grafo g) {
        this.c = c;
        this.g = g;
    }

    private void mostrarVertices() {
        int noVertices = g.getNumVertices();
        for (int i = 0; i < noVertices; i++)
            System.out.print(g.getVerticeByIndex(i) + " ");
    }

    public void inserirVertice() {
        String nomeVertice = c.promptAndGetString("Insira o nome do vértice que deseja inserir: ").trim().toUpperCase();
        nomeVertice = nomeVertice.substring(0, Math.min(nomeVertice.length(), 10));

        try {
            g.inserirVertice(nomeVertice);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inserirAresta() {
        if(g.getNumVertices() < 2) {
            System.out.println("Você precisa de no mínimo dois vértices para inserir uma aresta!");
            return;
        }

        System.out.println("Vértices inseridos: ");
        mostrarVertices();
        System.out.println();

        String v1 = c.promptAndGetString("Insira o primeiro vértice: ").toUpperCase();
        String v2 = c.promptAndGetString("Insira o segundo vértice: ").toUpperCase();
        byte peso = c.promptAndGetByte("Insira o peso da aresta: ");

        try {
            g.inserirAresta(v1,v2, peso);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dijkstra() {
        if(g.getNumArestas() < 1) {
            System.out.println("Você precisa de no mínimo uma aresta para calcular um caminho!");
            return;
        }

        System.out.println("Vértices inseridos: ");
        mostrarVertices();
        System.out.println();

        String v1 = c.promptAndGetString("Insira o vértice de origem: ").toUpperCase();
        String v2 = c.promptAndGetString("Insira o vértice de destino: ").toUpperCase();

        System.out.println(g.dijkstra(v1, v2));
    }

    public void mostrarMatrizAdjacencia() {
        int size = g.getNumVertices();
        Byte[][] matrix = g.getMatrizAdjacencia();
        String strFormatter = "%-10s ";

        // Espaço em branco
        System.out.printf(strFormatter, " ");
        // Linha com nome dos vertices
        for (int i=0; i < size; i++) {
            System.out.printf(strFormatter, g.getVerticeByIndex(i));
        }
        System.out.println();

        // Resto das linhas
        for (int i=0; i < size; i++) {
            System.out.printf(strFormatter, g.getVerticeByIndex(i));
            for (int j = 0; j < size; j++) {
                System.out.printf("%-10d ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
