package task5_transportation_abstract_cass;

import task5_transportation_abstract_cass.cargo.domain.Cargo;
import task5_transportation_abstract_cass.cargo.domain.CargoType;
import task5_transportation_abstract_cass.carrier.domain.Carrier;
import task5_transportation_abstract_cass.carrier.domain.CarrierType;
import task5_transportation_abstract_cass.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Date;

public class TransportationStarter {
    public static void main(String[] args) {
        Cargo apple = new Cargo();
        apple.setCargoType(CargoType.FOOD);
        apple.setName("Apple");
        apple.setWeight(20);

        Cargo iMac = new Cargo();
        iMac.setCargoType(CargoType.COMPUTERS);
        iMac.setName("iMac");
        iMac.setWeight(5);

        Cargo skirt = new Cargo();
        skirt.setCargoType(CargoType.CLOTHES);
        skirt.setName("Skirt");
        skirt.setWeight(1);

        Carrier theFastestCarrier = new Carrier();
        theFastestCarrier.setAddress("65, Lenin str.");
        theFastestCarrier.setCarrierType(CarrierType.PLANE);
        theFastestCarrier.setName("The fastest carrier in the world");

        Carrier theCheapestCarrier = new Carrier();
        theCheapestCarrier.setAddress("123, Gagrin str.");
        theCheapestCarrier.setCarrierType(CarrierType.TRAIN);
        theCheapestCarrier.setName("The cheapest carrier in the world");

        Carrier theBestCarrier = new Carrier();
        theBestCarrier.setAddress("50/4, Moscow str.");
        theBestCarrier.setCarrierType(CarrierType.SHIP);
        theBestCarrier.setName("The best carrier in the world");

        Transportation transportation1 = new Transportation();
        transportation1.setBillTo("SeroSoft Inc");
        transportation1.setCargo(apple);
        transportation1.setCarrier(theBestCarrier);
        transportation1.setDate(new Date(1998, 2, 3));
        transportation1.setDescription("Apples to SeroSoft Inc");

        Transportation transportation2 = new Transportation();
        transportation2.setBillTo("Karpov A.R.");
        transportation2.setCargo(iMac);
        transportation2.setCarrier(theCheapestCarrier);
        transportation2.setDate(new Date(2015, 3, 14));
        transportation2.setDescription("iMac for Karpov A.R.");

        Transportation transportation3 = new Transportation();
        transportation3.setBillTo("Girl scouts");
        transportation3.setCargo(skirt);
        transportation3.setCarrier(theFastestCarrier);
        transportation3.setDate(new Date(2000, 10, 10));
        transportation3.setDescription("Clothes for girl scouts of the USA");

        Storage.addCargo(apple);
        Storage.addCargo(iMac);
        Storage.addCargo(skirt);
        Storage.addCarrier(theFastestCarrier);
        Storage.addCarrier(theCheapestCarrier);
        Storage.addCarrier(theBestCarrier);
        Storage.addTransportation(transportation1);
        Storage.addTransportation(transportation2);
        Storage.addTransportation(transportation3);

        Storage.printAllCargoes();
        System.out.println();
        Storage.printAllCarriers();
        System.out.println();
        Storage.printAllTransportations();

        Long cargoIdForSearch = 3L;
        Cargo foundCargo = Storage.getCargoById(cargoIdForSearch);
        if (foundCargo == null) {
            System.out.println("\nCargo was not found by id: " + cargoIdForSearch);
        } else {
            System.out.println("\nCargo with id " + cargoIdForSearch + ":\n" + foundCargo);
        }

        String cargoNameForSearch = "Apple";
        Cargo[] foundCargoes = Storage.getCargoByName(cargoNameForSearch);
        System.out.println("\nCargoes with name " + cargoNameForSearch + ":");
        for (Cargo cargo : foundCargoes) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }

        System.out.println("\nGET ALL:");
        System.out.println(Arrays.toString(Storage.getAll()));
    }
}
