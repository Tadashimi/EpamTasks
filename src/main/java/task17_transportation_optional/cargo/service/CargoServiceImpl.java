package task17_transportation_optional.cargo.service;

import task17_transportation_optional.cargo.domain.Cargo;
import task17_transportation_optional.cargo.exception.unckecked.CargoDeleteConstraintViolationException;
import task17_transportation_optional.cargo.repo.CargoRepo;
import task17_transportation_optional.cargo.search.CargoSearchCondition;
import task17_transportation_optional.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void save(Cargo cargo) {
        cargoRepo.save(cargo);
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        if (id != null) {
            return cargoRepo.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(Long id) {
        if (id != null) {
            return cargoRepo.getByIdFetchingTransportations(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public int countAll() {
        return this.cargoRepo.countAll();
    }

    @Override
    public List<Cargo> findByName(String name) {
        Cargo[] found = cargoRepo.findByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Cargo> cargo = this.getByIdFetchingTransportations(id);

        if (cargo.isPresent()) {
            List<Transportation> transportations = cargo.get().getTransportations();
            boolean hasTransportations = transportations != null && transportations.size() > 0;
            if (hasTransportations) {
                throw new CargoDeleteConstraintViolationException(id);
            }

            return cargoRepo.deleteById(id);
        } else {
            return false;
        }
    }

    @Override
    public void printAll() {
        List<Cargo> allCargos = cargoRepo.getAll();
        allCargos.forEach(System.out::println);
    }

    @Override
    public boolean update(Cargo cargo) {
        if (cargo != null) {
            return cargoRepo.update(cargo);
        }

        return false;
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return cargoRepo.search(cargoSearchCondition);
    }
}
