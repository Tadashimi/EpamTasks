package Task7_TransportationWithCollections;

import Task7_TransportationWithCollections.cargo.domain.ClothersCargo;
import Task7_TransportationWithCollections.cargo.domain.FoodCargo;
import Task7_TransportationWithCollections.cargo.service.impl.CargoServiceImpl;
import Task7_TransportationWithCollections.carrier.domain.Carrier;
import Task7_TransportationWithCollections.carrier.domain.CarrierType;
import Task7_TransportationWithCollections.carrier.service.impl.CarrierServiceImpl;
import Task7_TransportationWithCollections.storage.Storage;
import Task7_TransportationWithCollections.transportation.domain.Transportation;
import Task7_TransportationWithCollections.transportation.service.impl.TransportationServiceImpl;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        ApplicationDemo applicationDemo = new ApplicationDemo();
        applicationDemo.addSampleData();
        applicationDemo.findByIdDemo();
        applicationDemo.findByNameDemo();
        applicationDemo.deleteByIdDemo();
    }

    private static class ApplicationDemo {
        private Storage storage = new Storage();
        private CargoServiceImpl cargoService = new CargoServiceImpl();
        private CarrierServiceImpl carrierService = new CarrierServiceImpl();
        private TransportationServiceImpl transportationService = new TransportationServiceImpl();

        public void addSampleData() {
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

        public void findByIdDemo(){
            cargoService.getById(1L);
            cargoService.getById(null);
            cargoService.getById(10L);

            carrierService.getById(3L);

            transportationService.getById(6L);
        }

        public void findByNameDemo() {
            cargoService.getByName("Apple");

            carrierService.getByName(null);
            carrierService.getByName("Apple");
        }

        public void deleteByIdDemo() {
            cargoService.deleteById(1L);
            cargoService.deleteById(null);
            cargoService.deleteById(10L);

            carrierService.deleteById(4L);

            transportationService.deleteById(6L);
        }
    }
}
