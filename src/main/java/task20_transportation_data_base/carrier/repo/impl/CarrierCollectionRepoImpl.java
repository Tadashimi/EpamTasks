package task20_transportation_data_base.carrier.repo.impl;


import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.repo.CarrierRepo;
import task20_transportation_data_base.storage.IdGenerator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static task20_transportation_data_base.storage.Storage.carrierCollection;

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
        return carrierCollection.stream()
                .filter(cargo -> Objects.equals(cargo.getName(), name))
                .toArray(Carrier[]::new);
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
