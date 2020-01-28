package task17_transportation_optional.transportation.service;

import task17_transportation_optional.common.business.exception.unchecked.ElementNotFoundException;
import task17_transportation_optional.transportation.domain.Transportation;
import task17_transportation_optional.transportation.repo.TransportationRepo;

import java.util.List;
import java.util.Optional;

public class TransportationServiceImpl implements TransportationService {

    private TransportationRepo transportationRepo;

    public TransportationServiceImpl(
            TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
    }

    @Override
    public boolean deleteById(Long id) {
        return transportationRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Transportation> allTransportations = transportationRepo.getAll();
        allTransportations.forEach(System.out::println);
    }

    @Override
    public void save(Transportation transportation) {
        transportationRepo.save(transportation);
    }

    @Override
    public Transportation findById(Long id) {
        if (id != null) {
            Optional<Transportation> transportation = transportationRepo.findById(id);
            return transportation.orElseThrow(() -> new ElementNotFoundException("Cannot find transportation by id: '" + id + "'"));
        }

        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return transportationRepo.getAll();
    }

    @Override
    public boolean update(Transportation transportation) {
        if (transportation != null) {
            return transportationRepo.update(transportation);
        }

        return false;
    }

    @Override
    public int countAll() {
        return transportationRepo.countAll();
    }
}
