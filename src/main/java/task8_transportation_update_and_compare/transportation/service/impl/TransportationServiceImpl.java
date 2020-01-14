package  task8_transportation_update_and_compare.transportation.service.impl;

import  task8_transportation_update_and_compare.common.solutions.utils.ArrayUtils;
import  task8_transportation_update_and_compare.transportation.domain.Transportation;
import  task8_transportation_update_and_compare.transportation.repo.TransportationRepo;
import  task8_transportation_update_and_compare.transportation.service.TransportationService;

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
    public Transportation update(Long id, Transportation transportation) {
        if (id == null) {
            System.out.println("Error while updating transportation: id is null.");
        } else if (transportation == null) {
            System.out.println("Error while updating transportation: transportation is null.");
        } else {
            return transportationRepo.update(id, transportation);
        }
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

    @Override
    public void printAll() {
        Transportation[] transportations = transportationRepo.getAll();
        if (transportations != null && transportations.length != 0) {
            ArrayUtils.printArray(transportations);
        } else {
            System.out.println("There is no any transportation in repo.");
        }
    }

}
