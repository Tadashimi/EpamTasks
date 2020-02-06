package task20_transportation_data_base.transportation.service;

import task20_transportation_data_base.transportation.domain.Transportation;
import task20_transportation_data_base.transportation.repo.TransportationRepo;

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
    public Optional<Transportation> findById(Long id) {
        if (id != null) {
            return transportationRepo.findById(id);
        }

        return Optional.empty();
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
