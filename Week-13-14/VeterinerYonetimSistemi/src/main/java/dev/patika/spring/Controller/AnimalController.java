package dev.patika.spring.Controller;

import dev.patika.spring.Dto.Request.AnimalRequest;
import dev.patika.spring.Entity.Animal;
import dev.patika.spring.Entity.Customer;
import dev.patika.spring.Repository.AnimalRepo;
import dev.patika.spring.Repository.CustomerRepo;
import dev.patika.spring.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;
    private final AnimalService animalService;
    @Autowired
    public AnimalController(AnimalRepo animalRepo, CustomerRepo customerRepo, AnimalService animalService) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
        this.animalService = animalService;
    }
    //id'ye göre hayvan bulmak için
    @GetMapping("/{id}")
    public Animal findbyId(@PathVariable("id") long id){
        return this.animalRepo.findById(id).orElseThrow();
    }

    // id yollanırsa update ediyor, id yoksa insert ediyor
    @PostMapping("/save")
    public ResponseEntity<?> saveAnimal(@RequestBody AnimalRequest animalRequest) {
        try {
            // Eğer gelen istekte id değeri yoksa yeni bir hayvan kaydedilir
            if (animalRequest.getId() == null) {
                Animal savedAnimal = animalService.saveAnimal(animalRequest);
                return ResponseEntity.ok(savedAnimal);
            } else { // Eğer id değeri varsa, id'ye göre hayvan güncellenir
                Optional<Animal> optionalAnimal = animalRepo.findById(animalRequest.getId());
                if (optionalAnimal.isPresent()) {
                    Animal existingAnimal = optionalAnimal.get();
                    // Yeni bilgilerle var olan hayvanın bilgileri güncellenir
                    existingAnimal.setName(animalRequest.getName());
                    existingAnimal.setSpecies(animalRequest.getSpecies());
                    existingAnimal.setBreed(animalRequest.getBreed());
                    existingAnimal.setGender(animalRequest.getGender());
                    existingAnimal.setColour(animalRequest.getColour());
                    existingAnimal.setDateOfBirth(animalRequest.getDateOfBirth());
                    Animal updatedAnimal = animalRepo.save(existingAnimal);
                    return ResponseEntity.ok(updatedAnimal);
                } else {
                    return ResponseEntity.notFound().build(); // Eğer id'ye sahip bir hayvan bulunamazsa 404 hatası döndürülür
                }
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //id ye göre hayvan silmek için
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable("id") long id) {
        try {
            Optional<Animal> optionalAnimal = animalRepo.findById(id);

            if (optionalAnimal.isPresent()) {
                animalRepo.deleteById(id);
                return ResponseEntity.ok( id + " numaralı hayvan silindi.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu id'de bir hayvan yok"); // Eğer hayvan bulunamazsa 404 hatası
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Id'ye sahip hayvan silinemedi: " + id + ": " + e.getMessage());
        }
    }





    // Hayvanların listesini döndüren metot
    @GetMapping("/find-all")
    public List<Animal> findAll(){
        return this.animalRepo.findAll();
    }
    //isme göre hayvan aramak için
    @GetMapping("/name/{name}")
    public List<Animal> findByName(@PathVariable("name") String name){
        return this.animalRepo.findByAnimalNameIgnoreCase(name);
    }



}
