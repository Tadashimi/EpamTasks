package Task11_TransportationWithFileReader.application;

import Task11_TransportationWithFileReader.cargo.domain.Cargo;
import Task11_TransportationWithFileReader.cargo.domain.CargoType;
import Task11_TransportationWithFileReader.storage.initor.FileParcer;
import Task11_TransportationWithFileReader.transportation.domain.Transportation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tmpClass {
    public static void main(String[] args) {
        List<String> cargoStrList = new ArrayList<>();
        List<String> carrierStrList = new ArrayList<>();
        List<String> transportationStrList = new ArrayList<>();
        String cargoBegin = "-----CARGOES BEGIN-----";
/*
        String[] qqq = "Fruits|1000|FOOD|10.01.2020|10".split("\\|");
        for (String a: qqq) {
            System.out.println(a);
        }
        System.out.println(qqq[2]);
        try {
            CargoType cargoType = CargoType.valueOf(qqq[2]);
        } catch (IllegalArgumentException e) {
            System.out.println("Bad");
        }*/
        File fileWithData = new File("E:\\TasksEpam\\Task1\\src\\main\\resources\\Task11_TransportationWithFileReader\\sampleInputData.txt");
        String str = "E:\\TasksEpam\\Task1\\src\\main\\resources\\Task11_TransportationWithFileReader\\sampleInputData.txt";
        FileParcer.parseFile(str);
        for (Transportation cargo : FileParcer.getTransportationListFromFile()) {
            System.out.println(cargo);
        }

    }
}
