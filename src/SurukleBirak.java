import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.*;
import java.util.List;
import javax.swing.*;

public class SurukleBirak extends DropTargetAdapter {
    private JTextArea txtContent;
    private formShoeSize form;

    public SurukleBirak(JTextArea txtContent, formShoeSize form) {
        this.txtContent = txtContent;
        this.form = form;
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            if (form.isFileSelected()) {
                JOptionPane.showMessageDialog(null, "Zaten bir dosya seçildi. Sürükleyip bırakma yöntemi kullanılamaz.");
                dtde.rejectDrop();
                return;
            }
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (DataFlavor flavor : flavors) {
                if (flavor.isFlavorJavaFileListType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    List<File> files = (List<File>) tr.getTransferData(flavor);
                    for (File file : files) {
                        form.setData(new Data(file));
                        form.setFile(file);
                        JOptionPane.showMessageDialog(null, "Dosya başarıyla eklendi: " + file.getName());
                        form.disableVeriSecButton();
                    }
                    dtde.dropComplete(true);
                    return;
                }
            }
            dtde.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }
}

