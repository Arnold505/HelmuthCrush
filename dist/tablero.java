package helmuthcrush;
public class tablero {

    private int[][] tamaño = new int[7][10];

    public tablero() {
        llenarMatriz();
    }

    public int[][] getTamaño() {
        return tamaño;
    }

    public void llenarMatriz() {
        //*******************DEGENERAR NUMEROS ALEATORIOS************************
        for (int i = 0; i < tamaño.length; i++) {
            for (int j = 0; j < tamaño[i].length; j++) {
                tamaño[i][j] = 1 + (int) (Math.random() * ((3 - 1) + 1));
                //Generando matriz
            }
            removerIgualesHorizontales();
            removerIgualesVerticales();
        }
    }

    //*******************FIN DEGENERAR NUMEROS ALEATORIOS********************
    public void removerIgualesHorizontales() {
        for (int l = 0; l < tamaño.length; l++) {
            for (int k = 0; k < tamaño[l].length - 2; k++) {
                //Comparar si se generan al igual
                if (tamaño[l][k] == tamaño[l][k + 1] && tamaño[l][k] == tamaño[l][k + 2]) {
                    if (tamaño[l][k + 1] == 1) {
                        tamaño[l][k + 1] = 2;
                    } else if (tamaño[l][k + 1] == 2) {
                        tamaño[l][k + 1] = 3;
                    } else {
                        tamaño[l][k + 1] = 1;
                    }
                }
            }
        }
    }
    
    public void removerIgualesVerticales() {
        for (int l = 0; l < tamaño.length-2; l++) {
            for (int k = 0; k < tamaño[l].length; k++) {
                //Comparar si se generan al igual
                if (tamaño[l][k] == tamaño[l+1][k] && tamaño[l][k] == tamaño[l+2][k]) {
                    if (tamaño[l+1][k] == 1) {
                        tamaño[l+1][k] = 2;
                    } else if (tamaño[l+1][k] == 2) {
                        tamaño[l+1][k] = 3;
                    } else {
                        tamaño[l+1][k] = 1;
                    }
                }
            }
        }
    }

    public int[][] getMatriz(){
        return this.tamaño;
    }

}
