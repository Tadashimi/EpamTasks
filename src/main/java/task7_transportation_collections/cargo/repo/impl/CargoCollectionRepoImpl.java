package task7_transportation_collections.cargo.repo.impl;

import task7_transportation_collections.cargo.domain.Cargo;
import task7_transportation_collections.cargo.repo.CargoRepo;
import task7_transportation_collections.storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static task7_transportation_collections.storage.Storage.cargoList;

public class CargoCollectionRepoImpl implements CargoRepo {

    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargoList.add(cargo);
    }

    @Override
    public Cargo getById(long id) {
        for (Cargo cargo : cargoList) {
            if (cargo != null && Objects.equals(cargo.getId(), id)) {
                return cargo;
            }
        }

        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
        List<Cargo> result = new ArrayList<>();
        for (Cargo cargo : cargoList) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                result.add(cargo);
            }
        }
        return result.toArray(new Cargo[0]);
    }

    @Override
    public boolean deleteById(long id) {
        Cargo cargo = getById(id);

        if (cargo != null) {
            cargoList.remove(cargo);
            return true;
        }

        return false;
    }

}
