package Task9_TransportationWithException.carrier.service.impl;

import Task9_TransportationWithException.carrier.domain.Carrier;
import Task9_TransportationWithException.carrier.exception.CarrierDeleteException;
import Task9_TransportationWithException.carrier.repo.CarrierRepo;
import Task9_TransportationWithException.carrier.service.CarrierService;
import Task9_TransportationWithException.common.solutions.exception.TransportationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Task9_TransportationWithException.common.solutions.exception.ExceptionMessage.CARRIER_IS_USED_IN_TRANSPORTATION;

public class CarrierServiceImpl implements CarrierService {

    private CarrierRepo carrierRepo;
    private final int CARRIER_DELETE_ERROR_CODE = 101;

    public CarrierServiceImpl(
            CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        carrierRepo.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        if (id != null) {
            return carrierRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Carrier> findByName(String name) {
        Carrier[] found = carrierRepo.findByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);

    }

    @Override
    public List<Carrier> getAll() {
        return carrierRepo.getAll();
    }

    @Override
    public boolean deleteById(Long id) throws TransportationException {
        int cargoTransportationCount = carrierRepo.getById(id).getTransportations().size();

        if (cargoTransportationCount == 0) {
            return carrierRepo.deleteById(id);
        }


        throw new CarrierDeleteException(CARRIER_DELETE_ERROR_CODE, CARRIER_IS_USED_IN_TRANSPORTATION);
    }

    @Override
    public void printAll() {
        List<Carrier> carriers = carrierRepo.getAll();
        for (Carrier carrier : carriers) {
            System.out.println(carrier);
        }
    }

    @Override
    public void update(Carrier carrier) {
        if (carrier != null) {
            carrierRepo.update(carrier);
        }
    }
}
