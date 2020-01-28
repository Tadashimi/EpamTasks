package task17_transportation_optional.carrier.repo.impl;


import task17_transportation_optional.carrier.domain.Carrier;
import task17_transportation_optional.carrier.repo.CarrierRepo;
import task17_transportation_optional.storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static task17_transportation_optional.storage.Storage.carrierCollection;

public class CarrierCollectionRepoImpl implements CarrierRepo {

    @Override
    public void save(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierCollection.add(carrier);
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        for (Carrier carrier : carrierCollection) {
            if (Objects.equals(carrier.getId(), id)) {
                return Optional.of(carrier);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Carrier[] findByName(String name) {
        List<Carrier> result = new ArrayList<>();

        carrierCollection.forEach((carrier) -> {
            if (Objects.equals(carrier.getName(), name)) {
                result.add(carrier);
            }
        });

        return result.toArray(new Carrier[0]);
    }

    @Override
    public boolean deleteById(Long id) {
        return carrierCollection.removeIf(carrier -> Objects.equals(carrier.getId(), id));
    }

    @Override
    public List<Carrier> getAll() {
        return carrierCollection;
    }

    @Override
    public int countAll() {
        return carrierCollection.size();
    }

    @Override
    public boolean update(Carrier carrier) {
        return true;
    }

}
