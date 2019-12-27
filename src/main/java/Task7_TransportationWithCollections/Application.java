package main.java.Task7_TransportationWithCollections;

import main.java.Task7_TransportationWithCollections.cargo.domain.Cargo;
import main.java.Task7_TransportationWithCollections.cargo.domain.ClothersCargo;
import main.java.Task7_TransportationWithCollections.cargo.domain.FoodCargo;
import main.java.Task7_TransportationWithCollections.cargo.repo.CargoRepo;
import main.java.Task7_TransportationWithCollections.cargo.repo.impl.CargoCollectionRepoImpl;
import main.java.Task7_TransportationWithCollections.cargo.service.impl.CargoServiceImpl;
import main.java.Task7_TransportationWithCollections.carrier.domain.Carrier;
import main.java.Task7_TransportationWithCollections.carrier.domain.CarrierType;
import main.java.Task7_TransportationWithCollections.carrier.repo.CarrierRepo;
import main.java.Task7_TransportationWithCollections.carrier.repo.impl.CarrierCollectionRepoImpl;
import main.java.Task7_TransportationWithCollections.carrier.service.impl.CarrierServiceImpl;
import main.java.Task7_TransportationWithCollections.transportation.domain.Transportation;
import main.java.Task7_TransportationWithCollections.transportation.repo.TransportationRepo;
import main.java.Task7_TransportationWithCollections.transportation.repo.impl.TransportationCollectionRepoImpl;
import main.java.Task7_TransportationWithCollections.transportation.service.impl.TransportationServiceImpl;

import java.util.Date;

public class Application {
    private static final String SEPARATOR = "======================================================================";

    public static void main(String[] args) {
        ApplicationDemo applicationDemo = new ApplicationDemo();
        applicationDemo.addSampleData();
        System.out.println(SEPARATOR);
        applicationDemo.findByIdDemo();
        System.out.println(SEPARATOR);
        applicationDemo.findByNameDemo();
        System.out.println(SEPARATOR);
        applicationDemo.deleteByIdDemo();
    }

    private static class ApplicationDemo {
        private CargoRepo cargoRepo = new CargoCollectionRepoImpl();
        private CargoServiceImpl cargoService = new CargoServiceImpl(cargoRepo);

        private CarrierRepo carrierRepo = new CarrierCollectionRepoImpl();
        private CarrierServiceImpl carrierService = new CarrierServiceImpl(carrierRepo);

        private TransportationRepo transportationRepo = new TransportationCollectionRepoImpl();
        private TransportationServiceImpl transportationService = new TransportationServiceImpl(transportationRepo);

        public void addSampleData() {
            System.out.println("Adding data...");
            FoodCargo foodCargo = new FoodCargo();
            foodCargo.setName("Apple");
            foodCargo.setExpirationDate(new Date(2021, 10, 15));
            foodCargo.setStoreTemperature(10);
            foodCargo.setWeight(10);

            ClothersCargo clothersCargo = new ClothersCargo();
            clothersCargo.setName("Skirt");
            clothersCargo.setMaterial("Cotton");
            clothersCargo.setWeight(1);
            clothersCargo.setSize("XL");

            Carrier theFastestCarrier = new Carrier();
            theFastestCarrier.setAddress("65, Lenin str.");
            theFastestCarrier.setCarrierType(CarrierType.PLANE);
            theFastestCarrier.setName("The fastest carrier in the world");

            Carrier theCheapestCarrier = new Carrier();
            theCheapestCarrier.setAddress("123, Gagrin str.");
            theCheapestCarrier.setCarrierType(CarrierType.TRAIN);
            theCheapestCarrier.setName("The cheapest carrier in the world");

            Transportation transportation1 = new Transportation();
            transportation1.setBillTo("SeroSoft Inc");
            transportation1.setCargo(foodCargo);
            transportation1.setCarrier(theCheapestCarrier);
            transportation1.setDescription("Food to SeroSoft Inc");

            Transportation transportation2 = new Transportation();
            transportation2.setBillTo("Karpov A.R.");
            transportation2.setCargo(clothersCargo);
            transportation2.setCarrier(theCheapestCarrier);
            transportation2.setDescription("Clothes for Karpov A.R.");

            cargoService.add(foodCargo);
            cargoService.add(clothersCargo);

            carrierService.add(theCheapestCarrier);
            carrierService.add(theFastestCarrier);

            transportationService.add(transportation1);
            transportationService.add(transportation2);
        }

        public void findByIdDemo() {
            System.out.println("GetById examples:");
            System.out.println("Cargo: " + cargoService.getById(1L));

            cargoService.getById(null);
            cargoService.getById(10L);

            System.out.println("Carrier: " + carrierService.getById(3L));

            System.out.println("Transportation: " + transportationService.getById(6L));
        }

        public void findByNameDemo() {
            System.out.println("GetByName examples:");
            for (Cargo cargo : cargoService.getByName("Apple")) {
                System.out.println(cargo);
            }

            carrierService.getByName(null);
            carrierService.getByName("Apple");
        }

        public void deleteByIdDemo() {
            System.out.println("DeleteById examples:");
            cargoService.deleteById(1L);
            cargoService.deleteById(null);
            cargoService.deleteById(10L);

            carrierService.deleteById(4L);

            transportationService.deleteById(6L);
        }
    }
}
