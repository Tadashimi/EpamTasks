package task7_transportation_collections;

import task7_transportation_collections.cargo.domain.Cargo;
import task7_transportation_collections.cargo.domain.ClothersCargo;
import task7_transportation_collections.cargo.domain.FoodCargo;
import task7_transportation_collections.cargo.repo.CargoRepo;
import task7_transportation_collections.cargo.repo.impl.CargoCollectionRepoImpl;
import task7_transportation_collections.cargo.service.impl.CargoServiceImpl;
import task7_transportation_collections.carrier.domain.Carrier;
import task7_transportation_collections.carrier.domain.CarrierType;
import task7_transportation_collections.carrier.repo.CarrierRepo;
import task7_transportation_collections.carrier.repo.impl.CarrierCollectionRepoImpl;
import task7_transportation_collections.carrier.service.impl.CarrierServiceImpl;
import task7_transportation_collections.transportation.domain.Transportation;
import task7_transportation_collections.transportation.repo.TransportationRepo;
import task7_transportation_collections.transportation.repo.impl.TransportationCollectionRepoImpl;
import task7_transportation_collections.transportation.service.impl.TransportationServiceImpl;

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
