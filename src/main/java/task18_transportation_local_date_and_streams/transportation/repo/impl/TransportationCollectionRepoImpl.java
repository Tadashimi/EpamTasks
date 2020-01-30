package task18_transportation_local_date_and_streams.transportation.repo.impl;


import task18_transportation_local_date_and_streams.storage.IdGenerator;
import task18_transportation_local_date_and_streams.transportation.domain.Transportation;
import task18_transportation_local_date_and_streams.transportation.repo.TransportationRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static task18_transportation_local_date_and_streams.storage.Storage.transportationCollection;

public class TransportationCollectionRepoImpl implements TransportationRepo {

    @Override
    public void save(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportationCollection.add(transportation);
    }

    @Override
    public Optional<Transportation> findById(Long id) {
        for (Transportation transportation : transportationCollection) {
            if (Objects.equals(transportation.getId(), id)) {
                return Optional.of(transportation);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Transportation> getAll() {
        return transportationCollection;
    }

    @Override
    public boolean update(Transportation transportation) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        return transportationCollection.removeIf(transportation -> Objects.equals(transportation.getId(), id));
    }

    @Override
    public int countAll() {
        return transportationCollection.size();
    }
}
