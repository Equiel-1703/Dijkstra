public class Main {
    public static void main(String[] args) {
        Console c = new Console();
        Menu m = new Menu("Escolha uma opção:");
        GrafoConsoleInterface grafo = new GrafoConsoleInterface(c, new Grafo(20));

        // Inicializando menus
        m.addMenuOption("1 - Inserir vértice");
        m.addMenuOption("2 - Inserir aresta");
        m.addMenuOption("3 - Mostrar matriz de adjacência");
        m.addMenuOption("4 - Caminho mínimo 2 vértices");
        m.addMenuOption("0 - Sair");

        // Loop do programa
        int op;
        while (true){
            // Mostra menu principal
            m.display();

            // Lê escolha do usuário
            try {
                op = c.promptAndGetInt("", 0, m.getNumberOfOptions());
            } catch (Exception e)
            {
                // Mostra mensagem de erro se ele escolher uma opção inválida e reinicia o loop
                System.out.println(e.getMessage() == null ? "Entrada inválida." : e.getMessage());
                continue;
            }

            switch (op) {
                case 1:
                    grafo.inserirVertice();
                    break;

                case 2:
                    grafo.inserirAresta();
                    break;

                case 3:
                    grafo.mostrarMatrizAdjacencia();
                    break;

                case 4:
                    grafo.dijkstra();
                    break;

                case 0:
                    System.exit(0);
            }
        }
    }
}