package task17_transportation_optional.transportation.repo.impl;


import task17_transportation_optional.storage.IdGenerator;
import task17_transportation_optional.transportation.domain.Transportation;
import task17_transportation_optional.transportation.repo.TransportationRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static task17_transportation_optional.storage.Storage.transportationCollection;

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
