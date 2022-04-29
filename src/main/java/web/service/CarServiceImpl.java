package web.service;

import web.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCars(List<Car> list, Integer amount) {

        return list.stream().limit(amount).collect(Collectors.toList());
    }
}
