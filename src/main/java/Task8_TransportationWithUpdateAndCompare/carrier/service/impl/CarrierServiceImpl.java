package Task8_TransportationWithUpdateAndCompare.carrier.service.impl;

import Task8_TransportationWithUpdateAndCompare.carrier.domain.Carrier;
import Task8_TransportationWithUpdateAndCompare.carrier.repo.CarrierRepo;
import Task8_TransportationWithUpdateAndCompare.carrier.service.CarrierService;
import Task8_TransportationWithUpdateAndCompare.common.solutions.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public List<Carrier> getByName(String name) {
        if (name == null) {
            System.out.println("Warning: name is null.");
        }
        Carrier[] found = carrierRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public Carrier update(Long id, Carrier carrier) {
        if (id == null) {
            System.out.println("Error while updating carrier: id is null.");
        } else if (carrier == null) {
            System.out.println("Error while updating carrier: carrier is null.");
        } else {
            return carrierRepo.update(id, carrier);
        }
        return null;
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

    @Override
    public void printAll() {
        Carrier[] carriers = carrierRepo.getAll();
        if (carriers != null && carriers.length != 0) {
            ArrayUtils.printArray(carriers);
        } else {
            System.out.println("There is no any carrier in repo.");
        }
    }

}