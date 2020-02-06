package task20_transportation_data_base.cargo.repo.impl;


import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.search.CargoSearchCondition;
import task20_transportation_data_base.common.solutions.utils.ArrayUtils;
import task20_transportation_data_base.common.solutions.utils.CollectionUtils;
import task20_transportation_data_base.storage.IdGenerator;

import java.util.*;
import java.util.stream.Collectors;

import static task20_transportation_data_base.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static task20_transportation_data_base.storage.Storage.cargoArray;
import static task20_transportation_data_base.storage.Storage.cargoIndex;

public class CargoArrayRepoImpl extends CommonCargoRepo {

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        return Arrays.stream(cargoArray)
                .filter(Objects::nonNull)
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
        for (Cargo cargo : cargoArray) {
            if (cargo != null && id != null && id.equals(cargo.getId())) {
                return Optional.of(cargo);
            }
        }

        return Optional.empty();
    }

    @Override
    public void save(Cargo cargo) {
        if (cargoIndex == cargoArray.length) {
            Cargo[] newCargos = new Cargo[cargoArray.length * 2];
            ArrayUtils.copyArray(cargoArray, newCargos);
            cargoArray = newCargos;
        }

        cargo.setId(IdGenerator.generateId());
        cargoArray[cargoIndex] = cargo;
        cargoIndex++;
    }

    @Override
    public boolean update(Cargo entity) {
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(cargoArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(cargoArray, indexToDelete);
            return true;
        }
    }

    @Override
    public List<Cargo> getAll() {
        return Arrays.stream(cargoArray)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

}
