package task17_transportation_optional.carrier.service;

import task17_transportation_optional.carrier.domain.Carrier;
import task17_transportation_optional.carrier.exception.unchecked.CarrierDeleteConstraintViolationException;
import task17_transportation_optional.carrier.repo.CarrierRepo;
import task17_transportation_optional.common.business.exception.unchecked.ElementNotFoundException;
import task17_transportation_optional.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CarrierServiceImpl implements CarrierService {

    private CarrierRepo carrierRepo;

    public CarrierServiceImpl(
            CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void save(Carrier carrier) {
        carrierRepo.save(carrier);
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        if (id != null) {
            return carrierRepo.findById(id);
        }

        return Optional.empty();
    }

    @Override
    public Carrier getByIdFetchingTransportations(Long id) {
        if (id != null) {
            Optional<Carrier> carrier = carrierRepo.getByIdFetchingTransportations(id);
            return carrier.orElseThrow(() -> new ElementNotFoundException("Cannot find carrier by id: '" + id + "'"));
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
    public int countAll() {
        return this.carrierRepo.countAll();
    }

    @Override
    public boolean deleteById(Long id) {
        Carrier carrier = this.getByIdFetchingTransportations(id);

        if (carrier != null) {
            List<Transportation> transportations = carrier.getTransportations();
            boolean hasTransportations = transportations != null && transportations.size() > 0;
            if (hasTransportations) {
                throw new CarrierDeleteConstraintViolationException(id);
            }

            return carrierRepo.deleteById(id);
        } else {
            return false;
        }
    }

    @Override
    public void printAll() {
        List<Carrier> carriers = carrierRepo.getAll();
        carriers.forEach(System.out::println);
    }

    @Override
    public boolean update(Carrier carrier) {
        if (carrier != null) {
            carrierRepo.update(carrier);
        }

        return false;
    }
}
