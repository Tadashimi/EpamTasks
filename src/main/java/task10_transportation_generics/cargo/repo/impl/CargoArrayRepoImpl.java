package  task10_transportation_generics.cargo.repo.impl;


import  task10_transportation_generics.cargo.domain.Cargo;
import  task10_transportation_generics.cargo.search.CargoSearchCondition;
import  task10_transportation_generics.common.solutions.utils.ArrayUtils;
import  task10_transportation_generics.common.solutions.utils.CollectionUtils;
import  task10_transportation_generics.storage.IdGenerator;

import java.util.*;

import static  task10_transportation_generics.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static  task10_transportation_generics.storage.Storage.cargoArray;
import static  task10_transportation_generics.storage.Storage.cargoIndex;

public class CargoArrayRepoImpl extends CommonCargoRepo {

    private static final Cargo[] EMPTY_CARGO_ARRAY = new Cargo[0];

    @Override
    public void add(Cargo cargo) {
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
    public Cargo getById(long id) {
        for (Cargo cargo : cargoArray) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }

        return null;
    }

    @Override
    public Cargo getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        Cargo[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
        if (searchResultWithNullableElems == null
                || searchResultWithNullableElems.length == 0) {
            return EMPTY_CARGO_ARRAY;
        } else {
            return excludeNullableElementsFromArray(searchResultWithNullableElems);
        }
    }

    private Cargo[] getByNameIncludingNullElements(String name) {
        Cargo[] result = new Cargo[cargoArray.length];

        int curIndex = 0;
        for (Cargo carrier : cargoArray) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
            }
        }

        return result;
    }

    /**
     * [A,B,C, null, null] [A,B,C, null, null, null, D] [A,B,C]
     * <p>
     * new String[3]
     */
    private Cargo[] excludeNullableElementsFromArray(Cargo[] cargos) {
        int sizeOfArrWithNotNullElems = 0;

        for (Cargo cargo : cargos) {
            if (cargo != null) {
                sizeOfArrWithNotNullElems++;
            }
        }

        if (sizeOfArrWithNotNullElems == 0) {
            return EMPTY_CARGO_ARRAY;
        } else {
            Cargo[] result = new Cargo[sizeOfArrWithNotNullElems];
            int index = 0;
            for (Cargo cargo : cargos) {
                if (cargo != null) {
                    result[index++] = cargo;
                }
            }

            return result;
        }
    }

    @Override
    public boolean deleteById(long id) {
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
        List<Cargo> cargos = Arrays.asList(cargoArray);
        Iterator<Cargo> iter = cargos.iterator();

        while (iter.hasNext()) {
            if (iter.next() == null) {
                iter.remove();
            }
        }

        return cargos;
    }

    @Override
    public void update(Cargo cargo) {

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

}
