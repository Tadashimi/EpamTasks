package task6_transportation_repo_and_service.cargo.repo.impl;

import task6_transportation_repo_and_service.cargo.domain.Cargo;
import task6_transportation_repo_and_service.cargo.repo.CargoRepo;
import task6_transportation_repo_and_service.common.utils.ArrayUtils;
import task6_transportation_repo_and_service.storage.IdGenerator;

import java.util.Objects;

import static task6_transportation_repo_and_service.storage.Storage.cargoIndex;
import static task6_transportation_repo_and_service.storage.Storage.cargos;

public class CargoRepoImpl implements CargoRepo {

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
        Cargo[] result = new Cargo[cargos.length];

        int curIndex = 0;
        for (Cargo cargo : cargos) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                result[curIndex++] = cargo;
            }
        }

        ArrayUtils.trimArray(result);
        return result;
    }

    @Override
    public boolean deleteById(long id) {
        int curIndex = 0;
        while (curIndex < cargos.length) {
            Cargo cargo = cargos[curIndex];
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                ArrayUtils.removeElementFromArray(cargos, curIndex);
                return true;
            }
            curIndex++;
        }
        return false;
    }
}
