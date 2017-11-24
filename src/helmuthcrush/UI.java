package helmuthcrush;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;



/**
 *
 * @author Cristian
 */
public class UI extends javax.swing.JFrame
        implements MouseListener {

    private boolean amIClicked = false;
    private int x1;
    private int y1;
    private HelmuthTableModel tableModel;
    private int contadoreMovientos = 20;
    protected Almacenamiento store;
    String nombreUsuario;
    /**
     * Creates new form UI
     */
    public UI() {
        //Aquí guardo todo el progreso
         store = new Almacenamiento();
        store.inicializarCrush();
        tableModel = new HelmuthTableModel();
        this.setResizable(false);
        initComponents();
        nombreUsuario = "";

        nombreUsuario = inicializarVaina(store, nombreUsuario);

        ImageIcon icon = new ImageIcon(getClass().getResource("HelmuthCrushIcon.png"));
        this.setIconImage(icon.getImage());

        nombreLabel.setText(nombreUsuario);

        movsLabel.setText("Movs: " + contadoreMovientos);
        puntajeLabel.setText("puntaje obtenido: " + HelmuthTableModel.puntaje);

        Tablero.setModel(tableModel);
        Tablero.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableModel.pintarCarotas();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "Quieres guardar tu juego antes de salir", "Cerrando?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    //store.;
                    //
                    store.guardarEsteMierdero(HelmuthTableModel.puntaje, 
                            nombreUsuario, 
                            contadoreMovientos, 
                            tableModel.getTablero());
                    System.exit(0);
                }
                else {
                    System.exit(0);
                }
            }
        });
        Tablero.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = Tablero.rowAtPoint(evt.getPoint());
                int col = Tablero.columnAtPoint(evt.getPoint());
                if (!amIClicked) {
                    //En caso de que no esté clickeado
                    // tomar x y y 
                    x1 = Tablero.rowAtPoint(evt.getPoint());
                    y1 = Tablero.columnAtPoint(evt.getPoint());
                    amIClicked = true;

                } else {
                    if (puedoMoverme(row, col, x1, y1)) {
                        tableModel.swap(row, col, x1, y1);
                        movsLabel.setText("Movs: " + contadoreMovientos);
                        statusLabel.setText("Movimiento completo");

                        // Analizar colores
                        tableModel.analizarMatriz();
                        contadoreMovientos--;
                        // SUmar puntaje
                        puntajeLabel.setText("puntaje obtenido: " + HelmuthTableModel.puntaje);
                        // cambiar oolores random
                        // Repintar
                        tableModel.fireTableDataChanged();

                    } else {
                        contadoreMovientos--;
                        statusLabel.setText("Debe chocolatina pillin");
                        movsLabel.setText("Movs: " + contadoreMovientos);
                    }
                    finalJuego(contadoreMovientos);
                    if (contadoreMovientos <= 0) {
                        System.exit(0);
                    }
                    //Falta cerrar el juego 
                    amIClicked = false;
                }
            }
        });
    }

    private String inicializarVaina(Almacenamiento store, String nombreUsuario) throws HeadlessException {
        if (store.getMovimientos() == 0
                && store.getNombreUsuario().equals("0")
                && store.getPuntaje() == 0) {
            nombreUsuario = JOptionPane.showInputDialog(this, "Con cual usuario quieres jugar", "Bienvenido a HelmuthCrush", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(nombreUsuario);
            if (nombreUsuario == null || nombreUsuario.equals("")) {
                JOptionPane.showMessageDialog(null, "No anotaste nombre, debe chocolatina");
                System.exit(0);
            }
        } else if (JOptionPane.showConfirmDialog(null, "Quieres Restaurar tu juego?", "Bienvenido a Helmuth Crush",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            nombreUsuario = store.getNombreUsuario();
            contadoreMovientos = store.getMovimientos();
            HelmuthTableModel.puntaje = store.getPuntaje();
            tableModel.setTablero(store.parsearEstado());
        } else {
            nombreUsuario = JOptionPane.showInputDialog(this, "Con cual usuario quieres jugar", "Bienvenido a HelmuthCrush", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(nombreUsuario);
            if (nombreUsuario == null || nombreUsuario.equals("")) {
                JOptionPane.showMessageDialog(null, "No anotaste nombre, debe chocolatina");
                System.exit(0);
            }
        }
        return nombreUsuario;
    }

    public static void finalJuego(int contadorMovimientos) {
        if (contadorMovimientos <= 0) {
            JOptionPane.showMessageDialog(null, "Final del juego");
            JOptionPane.showMessageDialog(null, "Tu puntaje fue: " + HelmuthTableModel.puntaje);
        }
    }

    public boolean puedoMoverme(int row, int col, int x1, int y1) {
        row = row;
        col = col;
        x1 = x1;
        y1 = y1;
        if ((row + col) - (x1 + y1) == 1 || (x1 + y1) - (row + col) == 1) {
            this.statusLabel.setText("Movimiento hecho");
            System.out.println("Se puede mover");
            return true;
        } else {
            this.statusLabel.setText("No es posible mover");
            System.out.println("No se puede mover");
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tablero = new javax.swing.JTable();
        statusLabel = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        movsLabel = new javax.swing.JLabel();
        puntajeLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The One and Only HelmuthCrush");

        Tablero.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Tablero.setModel(new HelmuthTableModel());
        Tablero.setToolTipText("");
        Tablero.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Tablero.setAutoscrolls(false);
        Tablero.setCellSelectionEnabled(true);
        Tablero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tablero.setRowHeight(50);
        jScrollPane1.setViewportView(Tablero);

        statusLabel.setText("Carajo");

        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        movsLabel.setText("jLabel1");

        puntajeLabel.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Bienvenido");

        nombreLabel.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(savebtn, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(movsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(puntajeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(nombreLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreLabel)
                        .addGap(312, 312, 312)
                        .addComponent(movsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(puntajeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(savebtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        store.guardarEsteMierdero(HelmuthTableModel.puntaje, 
                            nombreUsuario, 
                            contadoreMovientos, 
                            tableModel.getTablero());
        JOptionPane.showMessageDialog(this, "Juego Guardado");
        statusLabel.setText("El juego fue guardado");
// TODO add your handling code here:
    }//GEN-LAST:event_savebtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tablero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel movsLabel;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JLabel puntajeLabel;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
