package helmuthcrush;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author USUARIO
 */
public class HelmuthTableModel extends AbstractTableModel {

    public static int puntaje = 0;
    //Declaración de una matriz de enteros que se ecargar de hacer la magia
    private Integer[][] tablero;
    //Declaración de una matriz de objetos que se encarga de cargar las imágenes
    private Object[][] tableroUI;

    //Utilizo imágenes
    private ImageIcon foreverAlone = new ImageIcon(getClass().getResource("ForeverAlone.jpg"));
    private ImageIcon arcoiris = new ImageIcon(getClass().getResource("arcoiris.jpg"));
    private ImageIcon fap = new ImageIcon(getClass().getResource("fap.jpg"));
    private ImageIcon challenge = new ImageIcon(getClass().getResource("challenge.jpg"));
    private ImageIcon yao = new ImageIcon(getClass().getResource("yao.png"));

//        ImageIcon paola = new ImageIcon(getClass().getResource("paola.png"));
//        ImageIcon edwin = new ImageIcon(getClass().getResource("edwin.png"));
    public HelmuthTableModel() {
        tablero = new Integer[10][7];
        tableroUI = new Object[10][7];
        //Este método llena la matriz de números aleatorios de 1 a 5
        llenarMatriz();
        //Este método asigna una cara específica a un número
        pintarCarotas();
    }

    public void pintarCarotas() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                //Aquí  le asigno las caras dentro del ciclo que contiene el tamaño de la matriz
                pintarCaritaEnMatriz(i, j);
            }
        }
    }

    private void pintarCaritaEnMatriz(int i, int j) {
        //Cargar desde propiedades
        //Asigno a un número una cara específica
        int whichIcon = tablero[i][j];
        switch (whichIcon) {
            case 1:
                if (tablero[i][j] == 1) {
                    tableroUI[i][j] = fap;
                    break;
                }
            case 2:
                if (tablero[i][j] == 2) {
                    tableroUI[i][j] = arcoiris;
                    break;
                }
            case 3:
                if (tablero[i][j] == 3) {
                    tableroUI[i][j] = yao;
                    break;
                }
            case 4:
                if (tablero[i][j] == 4) {
                    tableroUI[i][j] = challenge;
                    break;
                }
            case 5:
                if (tablero[i][j] == 5) {
                    tableroUI[i][j] = foreverAlone;
                    break;
                }
        }
    }

    public void swap(int x1, int y1, int x2, int y2) {
        int temp = tablero[x1][y1]; //Se dan las posiciones para hacer el swap
        tablero[x1][y1] = tablero[x2][y2]; //Se cambian
        tablero[x2][y2] = temp; //Se lleva al vacío
        //VerificarTablero
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Object temp1 = tableroUI[x1][y1]; //Se dan las posiciones para hacer el swap
        tableroUI[x1][y1] = tableroUI[x2][y2]; //Se cambian
        tableroUI[x2][y2] = temp1; //Se lleva al vacío
        //VerificarTablero
    }

    public void llenarMatriz() {
        //*******************DEGENERAR NUMEROS ALEATORIOS************************
        if (tablero[0][0] == null){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = (int) (Math.random() * 5) + 1;
                //Generando matriz
                System.out.print(tablero[i][j]);
            }
            System.out.println("");

        }
        }
        System.out.println("___________________");
        //Al generar la matriz revisa si hay horizontales y verticales iguales y las elimina y genera nuevas
        removerIgualesHorizontales();
        removerIgualesVerticales();
    }

    //*******************FIN DEGENERAR NUMEROS ALEATORIOS********************
    public void removerIgualesHorizontales() {
        for (int l = 0; l < tablero.length; l++) {
            for (int k = 0; k < tablero[l].length - 3; k++) {
                //Comparar si se generan al igual
                if (tablero[l][k] == tablero[l][k + 1] && tablero[l][k] == tablero[l][k + 2]) {
                    if (tablero[l][k + 1] == 1) {
                        tablero[l][k + 1] = 2;
                    } else if (tablero[l][k + 1] == 2) {
                        tablero[l][k + 1] = 3;
                    } else {
                        tablero[l][k + 1] = 1;
                    }
                }
            }
        }
    }

    public void removerIgualesVerticales() {
        for (int l = 0; l < tablero.length - 3; l++) {
            for (int k = 0; k < tablero[l].length; k++) {
                //Comparar si se generan al igual

                if (tablero[l][k] == tablero[l + 1][k] && tablero[l][k] == tablero[l + 2][k]) {
                    if (tablero[l + 1][k] == 1) {
                        tablero[l + 1][k] = 2;
                    } else if (tablero[l + 1][k] == 2) {
                        tablero[l + 1][k] = 3;
                    } else {
                        tablero[l + 1][k] = 1;
                    }
                }
            }
        }
    }

    public void analizarMatriz() {
        //Aquí busco después del swap, las que sean iguals para eliminarlas
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[1].length; j++) {
                analizarIgualesHorizontales(i, j, 0, 0);
                analizarIgualesVerticales(i, j, 0, 0);
            }
        }
    }

    public void analizarIgualesVerticales(int row, int col, int x1, int y1) {
        // si tengo más de 4 pa arriba
        if (row >= 3) {
            if (sonCuatroIgualesArriba(row, col)) {
                HelmuthTableModel.puntaje += 20;
            }
        }
        if (row <= 6) {
            if (sonCuatroIgualesAbajo(row, col)) {
                HelmuthTableModel.puntaje += 20;
            }
        }

        if (row >= 2) {
            // Si tengo más de 3 pa arriba
            if (sonTresIgualesArriba(row, col)) {
                HelmuthTableModel.puntaje += 10;
            }
        }
        if (row <= 7) {
            if (sonTresIgualesAbajo(row, col)) {
                HelmuthTableModel.puntaje += 10;
            }
        }

    }

    public void analizarIgualesHorizontales(int row, int col, int x1, int y1) {
        if (col >= 3) {
            //Si tengo más de 4 pa a izquierda
            if (sonCuatroIgualesPaLaIzquierda(row, col)) {
                HelmuthTableModel.puntaje += 20;
            }
        }
        if (col <= 3) {
            //Si tengo más de 4 pa a derecha
            if (sonCuatroIgualesPaLaDerecha(row, col)) {
                HelmuthTableModel.puntaje += 20;
            }
        }

        if (col >= 2) {
            //Si tengo 3 pa la izquierda
            if (sonTresIgualesPaLaIzquierda(row, col)) {
                HelmuthTableModel.puntaje += 10;
            }
        }
        if (col <= 4) {
            //Si tengo 3 pa la derecha
            if (sonTresIgualesPaLaDerecha(row, col)) {
                HelmuthTableModel.puntaje += 10;
            }
        }
    }

    private boolean sonTresIgualesArriba(int row, int col) {
        boolean sonIgualesPaArriba = tablero[row][col] == tablero[row - 1][col]
                && tablero[row][col] == tablero[row - 2][col];
        if (sonIgualesPaArriba) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row - 1][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row - 1, col);
            tablero[row - 2][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row - 2, col);
        }
        return sonIgualesPaArriba;
    }

    private boolean sonCuatroIgualesArriba(int row, int col) {
        boolean sonIgualesPaArriba = tablero[row][col] == tablero[row - 1][col]
                && tablero[row][col] == tablero[row - 2][col]
                && tablero[row][col] == tablero[row - 3][col];
        if (sonIgualesPaArriba) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row - 1][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row - 1, col);
            tablero[row - 2][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row - 2, col);
            tablero[row - 3][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row - 3, col);
        }
        return sonIgualesPaArriba;
    }

    private boolean sonTresIgualesAbajo(int row, int col) {
        boolean sonIgualesPaAbajo = tablero[row][col] == tablero[row + 1][col]
                && tablero[row][col] == tablero[row + 2][col];
        if (sonIgualesPaAbajo) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row + 1][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row + 1, col);
            tablero[row + 2][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row + 2, col);
        }
        return sonIgualesPaAbajo;
    }

    private boolean sonCuatroIgualesAbajo(int row, int col) {
        boolean sonIgualesPaAbajo
                = tablero[row][col] == tablero[row + 1][col]
                && tablero[row][col] == tablero[row + 2][col]
                && tablero[row][col] == tablero[row + 3][col];
        if (sonIgualesPaAbajo) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row + 1][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row + 1, col);
            tablero[row + 2][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row + 2, col);
            tablero[row + 3][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row + 3, col);
        }
        return sonIgualesPaAbajo;
    }

    private boolean sonTresIgualesPaLaIzquierda(int row, int col) {
        boolean sonIgualesPaLaIzquierda = tablero[row][col] == tablero[row][col - 1]
                && tablero[row][col] == tablero[row][col - 2];
        if (sonIgualesPaLaIzquierda) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row][col - 1] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col - 1);
            tablero[row][col - 2] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col - 2);
        }
        return sonIgualesPaLaIzquierda;
    }

    private boolean sonCuatroIgualesPaLaIzquierda(int row, int col) {
        boolean sonIgualesPaLaIzquierda = tablero[row][col] == tablero[row][col - 1]
                && tablero[row][col] == tablero[row][col - 2]
                && tablero[row][col] == tablero[row][col - 3];
        if (sonIgualesPaLaIzquierda) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row][col - 1] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col - 1);
            tablero[row][col - 2] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col - 2);
            tablero[row][col - 3] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col - 3);
        }
        return sonIgualesPaLaIzquierda;
    }

    private boolean sonTresIgualesPaLaDerecha(int row, int col) {
        boolean sonIgualesPaLaIzquierda = tablero[row][col] == tablero[row][col + 1]
                && tablero[row][col] == tablero[row][col + 2];
        if (sonIgualesPaLaIzquierda) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row][col + 1] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row + 1, col);
            tablero[row][col + 2] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col + 2);
        }
        return sonIgualesPaLaIzquierda;
    }

    private boolean sonCuatroIgualesPaLaDerecha(int row, int col) {
        boolean sonIgualesPaLaIzquierda = tablero[row][col] == tablero[row][col + 1]
                && tablero[row][col] == tablero[row][col + 2]
                && tablero[row][col] == tablero[row][col + 3];
        if (sonIgualesPaLaIzquierda) {
            tablero[row][col] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col);
            tablero[row][col + 1] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col + 1);
            tablero[row][col + 2] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col + 2);
            tablero[row][col + 3] = (int) (Math.random() * 5) + 1;
            pintarCaritaEnMatriz(row, col + 3);
        }
        return sonIgualesPaLaIzquierda;
    }

    public Class getColumnClass(int c) {
        return ImageIcon.class;
    }

    public int getColumnCount() {
        return 7;
    }

    public int getRowCount() {
        return tableroUI.length;
    }

    public String getColumnName(int col) {
        return "";
    }

    public Object getValueAt(int row, int col) {
        return tableroUI[row][col];
    }

    public Integer[][] getTablero() {
        return tablero;
    }

    public void setTablero(Integer[][] tablero) {
        this.tablero = tablero;
    }
}
