import java.security.InvalidParameterException;
import java.util.*;

public class Grafo {
    private Byte[][] matrizAdjacencia;
    private final int sizeMatrix;
    private int numArestas;
    private final HashList<String,Integer> vertices = new HashList<>();



    public Grafo(int size)
    {
        this.matrizAdjacencia = new Byte[size][size];

        // Inicializa a matriz com 0
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                matrizAdjacencia[i][j] = 0;
            }
        }

        this.sizeMatrix = size;

        // Cria um grafo de teste (remover depois)
        inserirVertice("A");
        inserirVertice("B");
        inserirVertice("C");
        inserirVertice("D");
        inserirVertice("E");
        inserirVertice("F");
        inserirVertice("G");

        inserirAresta("A", "B", (byte)8);
        inserirAresta("A", "D", (byte)8);

        inserirAresta("B", "D", (byte)8);
        inserirAresta("B", "F", (byte)3);
        inserirAresta("B", "C", (byte)5);

        inserirAresta("C", "F", (byte)4);
        inserirAresta("C", "G", (byte)2);

        inserirAresta("D", "F", (byte)3);
        inserirAresta("D", "E", (byte)3);

        inserirAresta("E", "F", (byte)1);

        inserirAresta("F", "G", (byte)5);
    }

    public void inserirVertice(String nomeVertice) {
        if(vertices.size() == sizeMatrix)
            throw new ArrayIndexOutOfBoundsException(String.format("O número de vértices não pode ser maior que %d.", sizeMatrix));

        // Cria um novo vértice
        vertices.addElement(nomeVertice, vertices.size());
    }

    public void inserirAresta(String v1, String v2, byte peso) {
        Integer x = vertices.getValueByKey(v1);
        Integer y = vertices.getValueByKey(v2);

        if(x == null || y == null)
            throw new InvalidParameterException("Um dos vértices não existe.");

        matrizAdjacencia[x][y] = peso;
        numArestas++;
    }

    public List<String> dijkstra(String fonte, String destino) {
        Integer fonteCoord = vertices.getValueByKey(fonte);
        Integer destCoord = vertices.getValueByKey(destino);

        if(fonteCoord == null || destCoord == null)
            throw new InvalidParameterException("Um dos vértices não existe.");

        class EtiquetasList {
            class Etiqueta {
                int custoCaminho;
                String verticeAnterior;
                String verticeAtual;

                public Etiqueta(int custoCaminho, String verticeAnterior, String verticeAtual) {
                    this.custoCaminho = custoCaminho;
                    this.verticeAnterior = verticeAnterior;
                    this.verticeAtual = verticeAtual;
                }

                @Override
                public String toString() {
                    return "[" + custoCaminho + "," + verticeAnterior + "," + verticeAtual + "]";
                }
            }
            private final HashMap<String,Etiqueta> etiquetas = new HashMap<>();

            public EtiquetasList(String fonte) {
                etiquetas.put(fonte, new Etiqueta(0,null,fonte)); // Etiqueta inicial
            }

            public void addEtiqueta(String verticeAnterior, String verticeVinculado) {
                // -> Pegando peso da aresta entre o vértice vinculado e o vértice anterior
                int coordVerticeAnterior = vertices.getValueByKey(verticeAnterior);
                int coordVerticeVinculado = vertices.getValueByKey(verticeVinculado);

                int pesoAnterior = matrizAdjacencia[coordVerticeAnterior][coordVerticeVinculado];

                // -> Calcula o custo do caminho para a nova etiqueta
                var vertAntEtiqueta = etiquetas.get(verticeAnterior);

                int custoCaminho = vertAntEtiqueta.custoCaminho + pesoAnterior;
                var novaEtiqueta = new Etiqueta(custoCaminho, verticeAnterior, verticeVinculado);

                // -> Vê se já tem uma etiqueta para esse vértice
                etiquetas.merge(verticeVinculado, novaEtiqueta, (a, b) -> b.custoCaminho < a.custoCaminho ? b : a);
            }

            public Etiqueta getEtiqueta(String vertice) {
                return etiquetas.get(vertice);
            }

            @Override
            public String toString() {
                return etiquetas.toString();
            }
        }

        EtiquetasList etiquetasList = new EtiquetasList(fonte);
        int i = fonteCoord;
        int k = 0;

        // -> Vai percorrer as linhas da matriz
        while(k < sizeMatrix) {

            // Vai percorrer as colunas da matriz na linha i
            for(int j = 0; j < sizeMatrix; j++) {
                // Se encontrar alguma aresta vai criar uma etiqueta para ela
                if(matrizAdjacencia[i][j] != 0)
                    etiquetasList.addEtiqueta(vertices.getKeyByIndex(i), vertices.getKeyByIndex(j));
            }
            i = i + 1 < sizeMatrix ? i + 1 : 0;
            k++;
        }

        String verticePercorrido = destino;
        List<String> caminhoFinal = new LinkedList<>();

        while(!verticePercorrido.equals(fonte)) {
            caminhoFinal.add(verticePercorrido);
            var etiquetaAtual = etiquetasList.getEtiqueta(verticePercorrido);
            verticePercorrido = etiquetasList.getEtiqueta(etiquetaAtual.verticeAnterior).verticeAtual;
        }
        caminhoFinal.add(fonte);

        System.out.println(etiquetasList);
        Collections.reverse(caminhoFinal);
        return caminhoFinal;
    }

    public Byte[][] getMatrizAdjacencia() {
        return matrizAdjacencia;
    }

    public int getNumVertices() {
        return vertices.size();
    }

    public int getNumArestas() {
        return numArestas;
    }

    public String getVerticeByIndex(int i) {
        return vertices.getKeyByIndex(i);
    }

}
