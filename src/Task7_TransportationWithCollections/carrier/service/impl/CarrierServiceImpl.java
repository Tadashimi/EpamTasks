package Task7_TransportationWithCollections.carrier.service.impl;

import Task7_TransportationWithCollections.carrier.domain.Carrier;
import Task7_TransportationWithCollections.carrier.repo.impl.CarrierCollectionRepoImpl;
import Task7_TransportationWithCollections.carrier.service.CarrierService;

import java.util.List;

public class CarrierServiceImpl implements CarrierService {

    private final CarrierCollectionRepoImpl carrierCollectionRepo;

    public CarrierServiceImpl() {
        carrierCollectionRepo = new CarrierCollectionRepoImpl();
    }

    public CarrierServiceImpl(CarrierCollectionRepoImpl carrierCollectionRepo) {
        this.carrierCollectionRepo = carrierCollectionRepo;
    }

    @Override
    public void add(Carrier carrier) {
        if (carrier != null) {
            carrierCollectionRepo.add(carrier);
            System.out.println("Carrier was added:\n" + carrier.toString());
        } else {
            System.out.println("Carrier was not added. Reason: carrier is null.");
        }
    }

    @Override
    public Carrier getById(Long id) {
        if (id != null) {
            return carrierCollectionRepo.getById(id);
        }

        System.out.println("Carrier was not found: id is null.");
        return null;
    }

    @Override
    public List<Carrier> getByName(String name) {
        if (name == null) {
            System.out.println("Warning: name is null.");
        }
        return carrierCollectionRepo.getByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            if (carrierCollectionRepo.deleteById(id)) {
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
