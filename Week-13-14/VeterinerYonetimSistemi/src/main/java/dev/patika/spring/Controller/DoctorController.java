package dev.patika.spring.Controller;

import dev.patika.spring.Entity.Animal;
import dev.patika.spring.Entity.AvailableDate;
import dev.patika.spring.Entity.Doctor;
import dev.patika.spring.Repository.DoctorRepo;
import dev.patika.spring.Service.AvailableDateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorRepo doctorRepo;
    private final AvailableDateService availableDateService;

    public DoctorController(DoctorRepo doctorRepo, AvailableDateService availableDateService) {
        this.doctorRepo = doctorRepo;
        this.availableDateService = availableDateService;
    }


    @GetMapping("/{id}")
    public Doctor findbyId(@PathVariable("id") long id) {
        return this.doctorRepo.findById(id).orElseThrow();
    }

    // id yollanırsa update ediyor, id yoksa insert ediyor
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Doctor doctor) {
        // Telefona göre doktor kontrolü
        String phoneNumber = doctor.getPhone();
        if (doctorRepo.existsByPhone(phoneNumber)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Bu doktor zaten mevcut.");
        }

        Doctor savedDoctor = doctorRepo.save(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @GetMapping("/find-all")
    public List<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }

    @GetMapping("/name/{name}")
    public Doctor findByName(@PathVariable("name") String name) {
        return this.doctorRepo.findByName(name);
    }
    @GetMapping("/{id}/available-dates")
    public List<AvailableDate> getAvailableDates(@PathVariable Long id) {
        return availableDateService.getAvailableDates(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") long id) {
        try {
            Optional<Doctor> optionalDoctor = doctorRepo.findById(id);

            if (optionalDoctor.isPresent()) {
                doctorRepo.deleteById(id);
                return ResponseEntity.ok(id + " numaralı doktor silindi.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu ID'de bir doktor bulunamadı."); // Eğer doktor bulunamazsa 404 hatası
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ID'ye sahip doktor silinemedi: " + id + ": " + e.getMessage());
        }
    }



}
