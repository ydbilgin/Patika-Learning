package dev.patika.spring.Controller;


import dev.patika.spring.Dto.Request.AvailableDateRequest;
import dev.patika.spring.Entity.AvailableDate;
import dev.patika.spring.Entity.Doctor;
import dev.patika.spring.Repository.AvailableDateRepo;
import dev.patika.spring.Repository.DoctorRepo;
import dev.patika.spring.Service.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/available-dates")
public class AvailableDateController {
    @Autowired
    private  AvailableDateRepo availableDateRepo;
    @Autowired

    private  AvailableDateService availableDateService;
    @Autowired

    private  DoctorRepo doctorRepository;
    AvailableDate availableDate;




    @GetMapping("/{id}")
    public List<AvailableDate> findByDoctorId(@PathVariable("id") long id) {
        return availableDateService.getAvailableDates(id);
    }

    @PostMapping("/save")
    public ResponseEntity<AvailableDate> save(@RequestBody AvailableDateRequest request) {
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(request.getAvailableDate());

        Long doctorId = request.getDoctor().getId();
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Belirtilen id'ye sahip doktor bulunamadı: " + doctorId));
        availableDate.setDoctor(doctor);

        Doctor doctorDate = availableDate.getDoctor();
        LocalDate appointmentDate = availableDate.getAvailableDate();
        if (availableDateRepo.existsByDoctorAndAvailableDate(doctorDate, appointmentDate)) {
            throw new RuntimeException("Bu tarih için zaten bir kayıt var.");
        }

        availableDateRepo.save(availableDate);
        return ResponseEntity.ok(availableDate);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAvailableDate(@PathVariable("id") long id) {
        try {
            Optional<AvailableDate> optionalAvailableDate = availableDateRepo.findById(id);

            if (optionalAvailableDate.isPresent()) {
                availableDateRepo.deleteById(id);
                return ResponseEntity.ok(id + " numaralı çalışma tarihi silindi.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu ID'de bir çalışma tarihi bulunamadı."); // Eğer müsait tarih bulunamazsa 404 hatası
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ID'ye sahip çalışma tarihi silinemedi: " + id + ": " + e.getMessage());
        }
    }

}

