package Task7_TransportationWithCollections.transportation.service.impl;

import Task7_TransportationWithCollections.transportation.domain.Transportation;
import Task7_TransportationWithCollections.transportation.repo.TransportationRepo;
import Task7_TransportationWithCollections.transportation.service.TransportationService;

public class TransportationServiceImpl implements TransportationService {

    private final TransportationRepo transportationRepo;

    public TransportationServiceImpl(TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
    }

    @Override
    public void add(Transportation transportation) {
        if (transportation != null) {
            transportationRepo.add(transportation);
            System.out.println("Transportation was added:\n" + transportation.toString());
        } else {
            System.out.println("Transportation was not added. Reason: transportation is null.");
        }
    }

    @Override
    public Transportation getById(Long id) {
        if (id != null) {
            return transportationRepo.getById(id);
        }

        System.out.println("Warning: Transportation was not found: id is null.");
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            if (transportationRepo.deleteById(id)) {
                System.out.println("Transportation was deleted.");
                return true;
            }
            System.out.println("Error while deleting transportation: transportation was not found.");
        } else {
            System.out.println("Error while deleting transportation: id is null.");
        }
        return false;
    }

}
