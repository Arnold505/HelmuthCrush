package helmuthcrush;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Almacenamiento {

    private String nombreUsuario;
    private int puntaje;
    private Integer movimientos;
    private String estado;
    private Properties prop;

    public void inicializarCrush() {
        prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("HelmuthCrush.props");
            if (input == null) {
                System.out.println("Sorry, unable to find " + "HelmuthCrush.props");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            nombreUsuario = prop.getProperty("NombreUsuario");
            puntaje = Integer.valueOf(prop.getProperty("puntaje"));
            movimientos = Integer.valueOf(prop.getProperty("movimientos"));
            estado = prop.getProperty("estado");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String convertirMatrizAString(Integer[][] matriz) {
        StringBuilder nuevo = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                nuevo.append(matriz[i][j]);
                nuevo.append(",");
            }
            nuevo.deleteCharAt(nuevo.length()-1);
            nuevo.append("-");
        }
        nuevo.deleteCharAt(nuevo.length()-1);
        return nuevo.toString();
    }

    public void guardarEsteMierdero(int puntaje, String nombreUsuario, int contadorMovimientos, Integer[][] estadoActual) {
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("HelmuthCrush.props");
            prop.setProperty("NombreUsuario", nombreUsuario);
            prop.setProperty("puntaje", String.valueOf(puntaje));
            prop.setProperty("movimientos", String.valueOf(contadorMovimientos));
            prop.setProperty("estado", this.convertirMatrizAString(estadoActual));
            prop.store(output, "Propiedades guardadas");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Almacenamiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Almacenamiento.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer[][] parsearEstado() {
        Integer[][] result = new Integer[10][7];
        String[] lineas = estado.split("-");

        for (int i = 0; i < lineas.length; i++) {
            String[] columnita = lineas[i].split(",");
            for (int j = 0; j < columnita.length; j++) {
                result[i][j] = Integer.valueOf(columnita[j]);
            }
        }
        return result;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Integer movimientos) {
        this.movimientos = movimientos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
