import javax.swing.*;

public class Person {
    int age;
    int height;
    double weight;
    int shoeNo;

    public Person(int age, int height, double weight, int shoeNo) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.shoeNo = shoeNo;
    }

    public TypeEnum getChoose() {
        if (age == 0 && height != 0 && weight != 0 && shoeNo != 0) {
            return TypeEnum.age;
        } else if (age != 0 && height == 0 && weight != 0 && shoeNo != 0) {
            return TypeEnum.height;
        } else if (age != 0 && height != 0 && weight == 0 && shoeNo != 0) {
            return TypeEnum.weight;
        } else if (age != 0 && height != 0 && weight != 0 && shoeNo == 0) {
            return TypeEnum.shoeNo;
        } else {
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog(null, "İşlem yapılmaz");
        }
        return null;
    }
}
