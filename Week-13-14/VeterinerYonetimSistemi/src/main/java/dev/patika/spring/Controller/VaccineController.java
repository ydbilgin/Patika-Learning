package dev.patika.spring.Controller;

import dev.patika.spring.Dto.Request.VaccineRequest;
import dev.patika.spring.Dto.Response.VaccineResponse;
import dev.patika.spring.Entity.Animal;
import dev.patika.spring.Entity.Vaccine;
import dev.patika.spring.Repository.VaccineRepo;
import dev.patika.spring.Service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private final VaccineService vaccineService;
    private final VaccineRepo vaccineRepository;

    @Autowired
    public VaccineController(VaccineService vaccineService, VaccineRepo vaccineRepository) {
        this.vaccineService = vaccineService;
        this.vaccineRepository = vaccineRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<Vaccine>> getVaccinesByAnimalId(@PathVariable Long id) {
        List<Vaccine> vaccines = vaccineService.getVaccinesByAnimalId(id);
        return ResponseEntity.ok(vaccines);
    }
    @PostMapping("/save")
    public ResponseEntity<?> createVaccine(@RequestBody VaccineRequest vaccineRequest) {
        try {
            if (!vaccineService.isAnimalExist(vaccineRequest.getAnimal().getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Belirtilen id'de hayvan mevcut değil");
            }

            VaccineResponse response = vaccineService.saveVaccine(vaccineRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVaccine(@PathVariable("id") long id) {
        try {
            Optional<Vaccine> optionalVaccine = vaccineRepository.findById(id);

            if (optionalVaccine.isPresent()) {
                vaccineRepository.deleteById(id);
                return ResponseEntity.ok(id + " numaralı aşı silindi.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu ID'de bir aşı bulunamadı."); // Eğer aşı bulunamazsa 404 hatası
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ID'ye sahip aşı silinemedi: " + id + ": " + e.getMessage());
        }
    }




    @GetMapping("/expiring")
    public ResponseEntity<List<Animal>> getAnimalsWithExpiringVaccines(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        List<Animal> animals = vaccineService.getAnimalsWithExpiringVaccines(startDate, endDate);
        return ResponseEntity.ok(animals);
    }

}
