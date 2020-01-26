package task16_transportation_lambda.cargo.repo.impl;


import task16_transportation_lambda.cargo.domain.Cargo;
import task16_transportation_lambda.cargo.search.CargoSearchCondition;
import task16_transportation_lambda.common.solutions.utils.CollectionUtils;
import task16_transportation_lambda.storage.IdGenerator;

import java.util.*;

import static task16_transportation_lambda.storage.Storage.cargoCollection;

public class CargoCollectionRepoImpl extends CommonCargoRepo {

    @Override
    public Cargo getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        List<Cargo> result = new ArrayList<>();

        cargoCollection.forEach((cargo) -> {
            if (Objects.equals(cargo.getName(), name)) {
            result.add(cargo);
        }});
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
    public Cargo findById(Long id) {
        for (Cargo carrier : cargoCollection) {
            if (id != null && id.equals(carrier.getId())) {
                return carrier;
            }
        }

        return null;
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
        Iterator<Cargo> iter = cargoCollection.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (id != null && id.equals(iter.next().getId())) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;
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
