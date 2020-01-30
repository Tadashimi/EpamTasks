package task18_transportation_local_date_and_streams.carrier.repo.impl;


import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.carrier.repo.CarrierRepo;
import task18_transportation_local_date_and_streams.common.solutions.utils.ArrayUtils;
import task18_transportation_local_date_and_streams.storage.IdGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static task18_transportation_local_date_and_streams.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static task18_transportation_local_date_and_streams.storage.Storage.carrierArray;
import static task18_transportation_local_date_and_streams.storage.Storage.carrierIndex;

public class CarrierArrayRepoImpl implements CarrierRepo {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    @Override
    public void save(Carrier carrier) {
        if (carrierIndex == carrierArray.length) {
            Carrier[] newCarriers = new Carrier[carrierArray.length * 2];
            ArrayUtils.copyArray(carrierArray, newCarriers);
            carrierArray = newCarriers;
        }

        carrier.setId(IdGenerator.generateId());
        carrierArray[carrierIndex] = carrier;
        carrierIndex++;
    }

    @Override
    public boolean update(Carrier carrier) {
        return true;
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Carrier[] findByName(String name) {
        return Arrays.stream(carrierArray)
                .filter(Objects::nonNull)
                .filter(carrier -> Objects.equals(carrier.getName(), name))
                .toArray(Carrier[]::new);
    }


    @Override
    public List<Carrier> getAll() {
        return Arrays.stream(carrierArray)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public int countAll() {
        return this.getAll().size();
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        for (Carrier carrier : carrierArray) {
            if (carrier != null && carrier.getId().equals(id)) {
                return Optional.of(carrier);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Integer indexToDelete = findEntityIndexInArrayStorageById(carrierArray, id);

        if (indexToDelete == null) {
            return false;
        } else {
            ArrayUtils.removeElement(carrierArray, indexToDelete);
            return true;
        }
    }
}
