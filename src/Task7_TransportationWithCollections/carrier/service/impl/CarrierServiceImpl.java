package Task7_TransportationWithCollections.carrier.service.impl;

import Task7_TransportationWithCollections.carrier.domain.Carrier;
import Task7_TransportationWithCollections.carrier.repo.CarrierRepo;
import Task7_TransportationWithCollections.carrier.service.CarrierService;

public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepo carrierRepo;

    public CarrierServiceImpl(CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        if (carrier != null) {
            carrierRepo.add(carrier);
            System.out.println("Carrier was added:\n" + carrier.toString());
        } else {
            System.out.println("Carrier was not added. Reason: carrier is null.");
        }
    }

    @Override
    public Carrier getById(Long id) {
        if (id != null) {
            return carrierRepo.getById(id);
        }

        System.out.println("Carrier was not found: id is null.");
        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
        if (name == null) {
            System.out.println("Warning: name is null.");
        }
        return carrierRepo.getByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            if (carrierRepo.deleteById(id)) {
                System.out.println("Carrier was deleted.");
                return true;
            }
            System.out.println("Error while deleting carrier: carrier was not found.");
        } else {
            System.out.println("Error while deleting carrier: id is null.");
        }
        return false;
    }

}
