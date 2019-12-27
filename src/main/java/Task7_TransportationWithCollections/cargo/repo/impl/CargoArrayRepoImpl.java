package main.java.Task7_TransportationWithCollections.cargo.repo.impl;

import main.java.Task7_TransportationWithCollections.cargo.domain.Cargo;
import main.java.Task7_TransportationWithCollections.cargo.repo.CargoRepo;
import main.java.Task7_TransportationWithCollections.common.solutions.utils.ArrayUtils;
import main.java.Task7_TransportationWithCollections.storage.IdGenerator;

import java.util.Objects;

import static main.java.Task7_TransportationWithCollections.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static main.java.Task7_TransportationWithCollections.storage.Storage.*;

public class CargoArrayRepoImpl implements CargoRepo {

    private static final Cargo[] EMPTY_CARGO_ARRAY = new Cargo[0];

    @Override
    public void add(Cargo cargo) {
        if (cargoIndex == cargos.length) {
            Cargo[] newCargos = new Cargo[cargos.length * 2];
            ArrayUtils.copyArray(cargos, newCargos);
            cargos = newCargos;
        }

        cargo.setId(IdGenerator.generateId());
        cargos[cargoIndex] = cargo;
        cargoIndex++;
    }

    @Override
    public Cargo getById(long id) {
        for (Cargo cargo : cargos) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }

        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
        Cargo[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
        if (searchResultWithNullableElems == null
                || searchResultWithNullableElems.length == 0) {
            return EMPTY_CARGO_ARRAY;
        } else {
            return excludeNullableElementsFromArray(searchResultWithNullableElems);
        }
    }

    private Cargo[] getByNameIncludingNullElements(String name) {
        Cargo[] result = new Cargo[cargos.length];

        int curIndex = 0;
        for (Cargo carrier : cargos) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
            }
        }

        return result;
    }

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
        Integer indexToDelete = findEntityIndexInArrayStorageById(carriers, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(cargos, indexToDelete);
            return true;
        }
    }

}
