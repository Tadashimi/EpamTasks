package task18_transportation_local_date_and_streams.cargo.repo.impl;


import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.cargo.search.CargoSearchCondition;
import task18_transportation_local_date_and_streams.common.solutions.utils.CollectionUtils;
import task18_transportation_local_date_and_streams.storage.IdGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static task18_transportation_local_date_and_streams.storage.Storage.cargoCollection;

public class CargoCollectionRepoImpl extends CommonCargoRepo {

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        return cargoCollection.stream()
                .filter(cargo -> Objects.equals(cargo.getName(), name))
                .toArray(Cargo[]::new);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {
        List<Cargo> cargos = getAll();

        if (CollectionUtils.isNotEmpty(cargos)) {
            if (searchCondition.needSorting()) {
                Comparator<Cargo> cargoComparator = createCargoComparator(searchCondition);
                cargos.sort(searchCondition.isAscOrdering() ? cargoComparator : cargoComparator.reversed());
            }
        }

        return cargos;
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        for (Cargo cargo : cargoCollection) {
            if (Objects.equals(cargo.getId(), id)) {
                return Optional.of(cargo);
            }
        }

        return Optional.empty();
    }

    @Override
    public void save(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargoCollection.add(cargo);
    }

    @Override
    public boolean update(Cargo entity) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        return cargoCollection.removeIf(cargo -> Objects.equals(cargo.getId(), id));
    }

    @Override
    public List<Cargo> getAll() {
        return cargoCollection;
    }

    @Override
    public int countAll() {
        return cargoCollection.size();
    }


}
