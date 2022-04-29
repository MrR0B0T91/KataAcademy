package web.model;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private Long id;

    private String model;

    private Integer series;

    private Car(Long id, String model, Integer series) {
        this.id = id;
        this.model = model;
        this.series = series;
    }

    public static List<Car> creatList() {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1L, "Audi", 100));
        list.add(new Car(2L, "BMW", 200));
        list.add(new Car(3L, "Lada", 300));
        list.add(new Car(4L, "Toyota", 400));
        list.add(new Car(5L, "KIA", 500));

        return list;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }
}
