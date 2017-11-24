package helmuthcrush;


import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.TableModel;

class TransferHelper extends TransferHandler {

    private static final long serialVersionUID = 1L;

    public TransferHelper() {
    }

    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent source) {
        ImageIcon data = (ImageIcon)((JTable) source).getModel().getValueAt(((JTable) source).getSelectedRow(), ((JTable) source).getSelectedColumn());
        return new DataHandler(data, "");
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        ((JTable) source).getModel().setValueAt("", ((JTable) source).getSelectedRow(), ((JTable) source).getSelectedColumn());
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return true;
    }

    @Override
    public boolean importData(TransferSupport support) {
        JTable jt = (JTable) support.getComponent();
        try {
            jt.setValueAt(support.getTransferable().getTransferData(DataFlavor.imageFlavor), jt.getSelectedRow(), jt.getSelectedColumn());
        } catch (UnsupportedFlavorException ex) {

        } catch (IOException ex) {

        }
        return super.importData(support);
    }

    public static void main(String[] args) {
        TableModel model = null;
        JTable table_1 = new JTable(model);
        table_1.setPreferredScrollableViewportSize(new Dimension(300, 120));
        table_1.setDragEnabled(true);
        table_1.setDropMode(DropMode.USE_SELECTION);
        table_1.setTransferHandler(new TransferHelper());
        table_1.setRowSelectionAllowed(false);
        table_1.setCellSelectionEnabled(true);
    }
}
