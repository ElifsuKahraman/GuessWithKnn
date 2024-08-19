import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Data {
    File file;
    public ArrayList<Person> personArrayList = new ArrayList<>();

    public Data(File file) {
        this.file = file;
    }

    public void checkData() {
        if (file != null && !file.exists()) {
            file.isDirectory();
        }
    }

    public void parseXml() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            Element kokElement = document.getDocumentElement();
            System.out.println(kokElement.getNodeName());

            NodeList personList = kokElement.getElementsByTagName("kişi");
            System.out.println("Kişi sayısı: " + personList.getLength());
            for (int i = 0; i < personList.getLength(); i++) {
                Node PersonNode = personList.item(i);
                Element personElement = (Element) PersonNode;

                int height=Integer.parseInt(personElement.getElementsByTagName("boy").item(0).getTextContent());
                int weight=Integer.parseInt(personElement.getElementsByTagName("kilo").item(0).getTextContent());
                int age=Integer.parseInt(personElement.getElementsByTagName("yaş").item(0).getTextContent());
                int shoeNo=Integer.parseInt(personElement.getElementsByTagName("ayak_numarası").item(0).getTextContent());
                Person person=new Person(age,height,weight,shoeNo);
                personArrayList.add(person);
            }
            System.out.println(personArrayList.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
