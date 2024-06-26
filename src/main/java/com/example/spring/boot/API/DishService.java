package com.example.spring.boot.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        Optional<Dish> existingDish = dishRepository.findById(id);
        if (existingDish.isPresent()) {
            Dish dish = existingDish.get();
            dish.setName(updatedDish.getName());
            dish.setType(updatedDish.getType());
            dish.setPrice(updatedDish.getPrice());
            dish.setRating(updatedDish.getRating());
            return dishRepository.save(dish);
        } else {
            throw new RuntimeException("Dish not found");
        }
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
