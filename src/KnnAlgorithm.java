import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class KnnAlgorithm {
    Person person;
    Data data;

    public KnnAlgorithm(Person person, Data data) {
    this.person = person;
    this.data = data;
   }
    public double toGuess(TypeEnum typeEnum){
        ArrayList<Double> knnArrayList= new ArrayList<>();
        HashMap<Double, Person> knnHashMap= new HashMap<>();
        for(Person person: data.personArrayList) {
            double square=Math.sqrt(distanceCalculate(person,typeEnum));
            knnHashMap.put(square, person);
        }
        knnArrayList=new ArrayList<>(knnHashMap.keySet());
        Collections.sort(knnArrayList);
        double total=0;
        for(int i=0;i<3;i++){
            switch (typeEnum){
                case weight -> total+=knnHashMap.get(knnArrayList.get(i)).weight;
                case age -> total+=knnHashMap.get(knnArrayList.get(i)).age;
                case height -> total+=knnHashMap.get(knnArrayList.get(i)).height;
                case shoeNo -> total+=knnHashMap.get(knnArrayList.get(i)).shoeNo;
            }

        }
        System.out.println(total/3);
        return total/3;
    }

   private double distanceCalculate(Person dataPerson,TypeEnum typeEnum) {
//       if (typeEnum == null) {
//           System.out.println("İşlem gerçekleştirilmiyor.Yeniden giriniz");
//
//       }
       double distance =0;
       switch (typeEnum){
           case weight -> distance =(Math.pow(person.height-dataPerson.height,2)) + (Math.pow(person.shoeNo-dataPerson.shoeNo,2)) + (Math.pow(person.age-dataPerson.age,2)) ;
           case age -> distance =(Math.pow(person.height-dataPerson.height,2)) + (Math.pow(person.weight-dataPerson.weight,2)) + (Math.pow(person.shoeNo-dataPerson.shoeNo,2)) ;
           case height -> distance =(Math.pow(person.shoeNo-dataPerson.shoeNo,2)) + (Math.pow(person.weight-dataPerson.weight,2)) + (Math.pow(person.age-dataPerson.age,2)) ;
           case shoeNo -> distance =(Math.pow(person.height-dataPerson.height,2)) + (Math.pow(person.weight-dataPerson.weight,2)) + (Math.pow(person.age-dataPerson.age,2)) ;
       }
      return distance;
    }
}

