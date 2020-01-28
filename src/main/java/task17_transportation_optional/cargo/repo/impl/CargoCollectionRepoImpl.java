package task17_transportation_optional.cargo.repo.impl;


import task17_transportation_optional.cargo.domain.Cargo;
import task17_transportation_optional.cargo.search.CargoSearchCondition;
import task17_transportation_optional.common.solutions.utils.CollectionUtils;
import task17_transportation_optional.storage.IdGenerator;

import java.util.*;

import static task17_transportation_optional.storage.Storage.cargoCollection;

public class CargoCollectionRepoImpl extends CommonCargoRepo {

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        List<Cargo> result = new ArrayList<>();

        cargoCollection.forEach((cargo) -> {
            if (Objects.equals(cargo.getName(), name)) {
                result.add(cargo);
            }
        });
        return result.toArray(new Cargo[0]);
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
