package task11_transportation_txt_parser.cargo.repo.impl;


import task11_transportation_txt_parser.cargo.domain.Cargo;
import task11_transportation_txt_parser.cargo.search.CargoSearchCondition;
import task11_transportation_txt_parser.common.solutions.utils.CollectionUtils;
import task11_transportation_txt_parser.storage.IdGenerator;

import java.util.*;

import static task11_transportation_txt_parser.storage.Storage.cargoCollection;

public class CargoCollectionRepoImpl extends CommonCargoRepo {

    @Override
    public void add(Cargo carrier) {
        carrier.setId(IdGenerator.generateId());
        cargoCollection.add(carrier);
    }

    @Override
    public Cargo getById(Long id) {
        for (Cargo carrier : cargoCollection) {
            if (Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
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
        List<Cargo> result = new ArrayList<>();

        for (Cargo carrier : cargoCollection) {
            if (Objects.equals(carrier.getName(), name)) {
                result.add(carrier);
            }
        }

        return result.toArray(new Cargo[0]);
    }

    @Override
    public List<Cargo> getAll() {
        return cargoCollection;
    }

    @Override
    public void update(Cargo cargo) {

    }

    @Override
    public void addAll(List<Cargo> cargos) {
        if (cargos != null) {
            cargoCollection.addAll(cargos);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Iterator<Cargo> iter = cargoCollection.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (Long.valueOf(id).equals(iter.next().getId())) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;
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
