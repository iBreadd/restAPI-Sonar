package com.example.spring.boot.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/dish")
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish){
        Dish newDish = dishService.saveDish(dish);
        return ResponseEntity.ok(newDish);
    }

    @GetMapping("/dishes")
    public List<Dish> getAllDishes(){
        return dishService.getAllDishes();
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id){
        Optional<Dish> dish = dishService.getDishById(id);
        return dish.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish){
        Dish updatedDish = dishService.updateDish(id, dish);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id){
        dishService.deleteDish(id);
        return ResponseEntity.ok("Dish deleted successfully!");
    }
}
