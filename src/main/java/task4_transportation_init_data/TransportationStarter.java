package task4_transportation_init_data;

import java.util.Date;

public class TransportationStarter {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Cargo apple = new Cargo();
        apple.setCargoType(CargoType.FOOD);
        apple.setName("Apple");
        apple.setWeight(20);

        Cargo iMac = new Cargo();
        iMac.setCargoType(CargoType.COMPUTERS);
        iMac.setName("iMac");
        iMac.setWeight(5);

        Cargo skirt = new Cargo();
        skirt.setCargoType(CargoType.CLOTHER);
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
        transportation2.setDate(new Date(2015, 3 ,14));
        transportation2.setDescription("iMac for Karpov A.R.");

        Transportation transportation3 = new Transportation();
        transportation3.setBillTo("Girl scouts");
        transportation3.setCargo(skirt);
        transportation3.setCarrier(theFastestCarrier);
        transportation3.setDate(new Date(2000, 10, 10));
        transportation3.setDescription("Clothes for girl scouts of the USA");

        storage.addCargo(apple);
        storage.addCargo(iMac);
        storage.addCargo(skirt);
        storage.addCarrier(theFastestCarrier);
        storage.addCarrier(theCheapestCarrier);
        storage.addCarrier(theBestCarrier);
        storage.addTransportation(transportation1);
        storage.addTransportation(transportation2);
        storage.addTransportation(transportation3);

        storage.printAllCargoes();
        System.out.println();
        storage.printAllCarriers();
        System.out.println();
        storage.printAllTransportations();
    }
}
