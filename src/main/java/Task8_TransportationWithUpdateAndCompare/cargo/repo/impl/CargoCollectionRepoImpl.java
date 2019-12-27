package main.java.Task8_TransportationWithUpdateAndCompare.cargo.repo.impl;

import main.java.Task8_TransportationWithUpdateAndCompare.cargo.domain.Cargo;
import main.java.Task8_TransportationWithUpdateAndCompare.cargo.repo.CargoRepo;
import main.java.Task8_TransportationWithUpdateAndCompare.storage.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static main.java.Task8_TransportationWithUpdateAndCompare.storage.Storage.cargoList;

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
    public Cargo[] getAll() {
        return cargoList.toArray(new Cargo[0]);
    }

    @Override
    public Cargo update(long id, Cargo cargo) {
        Cargo cargoFromRepo = getById(id);
        if (cargo != null && cargoFromRepo != null) {
            int index = cargoList.indexOf(cargoFromRepo);
            cargo.setId(id);
            cargoList.set(index, cargo);
            return cargo;
        }
        return null;
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
