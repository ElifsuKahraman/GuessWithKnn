import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.dnd.*;
import java.io.File;

public class formShoeSize extends JFrame {
     private JTextField textFieldHeight;
     private JTextField textFieldAge;
     private JTextField textFieldWeight;
     private JButton btnGuess;
     private JLabel lblAge;
     private JLabel lblHeight;
     private JLabel lblWeight;
     private JPanel panel1;
     private JTextField textFieldayakNo;
     private JButton veriSeçEkleButton;
     private Data data;
     private JTextArea txtContent = new JTextArea(3, 3);
     private File[] file = new File[1];
     private boolean fileSelected = false;
     private File selectedFile;


    public formShoeSize() {
        add(panel1);
        setSize(800, 600);
        setTitle("KNN ile Tahmin Algoritması");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(txtContent);
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        SurukleBirak surukle = new SurukleBirak(txtContent, this);
        new DropTarget(txtContent, surukle);

        btnGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileSelected || file != null) {
                    JOptionPane pane = new JOptionPane();
                    data.checkData();
                    data.parseXml();
                    Person person = new Person(getAge(), hgetHeight(), wgetWeight(), getShoeNo());
                    KnnAlgorithm knn = new KnnAlgorithm(person, data);
                    double tahmin = knn.toGuess(person.getChoose());
                    String mesaj = " ";
                    if (person.getChoose() == TypeEnum.age) {
                        mesaj = "Tahmin edilen yaş: " + tahmin;
                    } else if (person.getChoose() == TypeEnum.height) {
                        mesaj = "Tahmin edilen boy: " + tahmin;
                    } else if (person.getChoose() == TypeEnum.weight) {
                        mesaj = "Tahmin edilen kilo: " + tahmin;
                    } else if (person.getChoose() == TypeEnum.shoeNo) {
                        mesaj = "Tahmin edilen ayak numarası: " + tahmin;
                    }
                    pane.showMessageDialog(null,
                            "Yaşınız:" + getAge() + "\n"
                                    + "Boyunuz: " + hgetHeight() + "\n"
                                    + "Kilonuz: " + wgetWeight() + "\n"
                                    + "Ayak no " + getShoeNo() + "\n"
                                    + mesaj);
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen önce bir dosya seçin veya sürükleyip bırakın.");
                }
            }
        });
        veriSeçEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fileSelected) {
                    JFileChooser files = new JFileChooser();
                    int returnVal = files.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        selectedFile = files.getSelectedFile();
                        txtContent.setText("");
                        JOptionPane.showMessageDialog(null, "Dosya başarıyla eklendi: " + selectedFile.getName());
                        fileSelected = true;
                        veriSeçEkleButton.setEnabled(false); // Butonu devre dışı bırak
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Zaten bir dosya seçildi. Sürükleyip bırakma yöntemi kullanılamaz.");
                }
            }
        });
    }
    public int getShoeNo() {
        String shoeNo = textFieldayakNo.getText();
        if (shoeNo.trim().isEmpty()) {
            shoeNo = "0";
        }
        return Integer.parseInt(shoeNo);
    }

    public int hgetHeight() {
        String height1 = textFieldHeight.getText();
        if (height1.trim().isEmpty()) {
            height1 = "0";
        }
        return Integer.parseInt(height1);
    }

    public int wgetWeight() {

        String weight1 = textFieldWeight.getText();
        if (weight1.trim().isEmpty()) {
            weight1 = "0";
        }
        return Integer.parseInt(weight1);
    }

    public int getAge() {
        String age1 = textFieldAge.getText();
        if (age1.trim().isEmpty()) {
            age1 = "0";
        }
        return Integer.parseInt(age1);
    }
    public void setData(Data data) {
        this.data = data;
    }
    public void setFile(File file) {
        this.selectedFile = file;
        this.fileSelected = true;
        this.data = new Data(file);
    }
    public void disableVeriSecButton() {
        veriSeçEkleButton.setEnabled(false);
    }
    public boolean isFileSelected() {
        return fileSelected;
    }


}







