package dev.patika.spring.Controller;

import dev.patika.spring.Entity.Animal;
import dev.patika.spring.Entity.Appointment;
import dev.patika.spring.Entity.Customer;
import dev.patika.spring.Repository.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    @GetMapping("/{id}")
    public Customer findbyId(@PathVariable("id") long id) {
        return this.customerRepo.findById(id).orElseThrow();
    }

    // id yollanırsa update ediyor, id yoksa insert ediyor
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        try {
            if (customerRepo.existsByPhone(customer.getPhone())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu telefon numarasına sahip müşteri zaten mevcut.");
            }

            Customer savedCustomer = customerRepo.save(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Müşteri kaydedilemedi: " + e.getMessage());
        }
    }

    @GetMapping("/find-all")
    public List<Customer> findAll() {
        return this.customerRepo.findAll();
    }
    @GetMapping("/{customerId}/animals")
    public List<Animal> findAnimalsByCustomerId(@PathVariable("customerId") long id) {
        Customer customer = customerRepo.findById(id).orElseThrow();

        return customer.getAnimals();
    }
    @GetMapping("/name/{name}")
    public List<Customer> findByName(@PathVariable("name") String name){
        return this.customerRepo.findByCustomerNameIgnoreCase(name);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
        try {
            Optional<Customer> optionalCustomer = customerRepo.findById(id);

            if (optionalCustomer.isPresent()) {
                customerRepo.deleteById(id);
                return ResponseEntity.ok(id + " numaralı müşteri silindi.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu ID'de bir müşteri bulunamadı."); // Eğer müşteri bulunamazsa 404 hatası
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ID'ye sahip müşteri silinemedi: " + id + ": " + e.getMessage());
        }
    }

}
