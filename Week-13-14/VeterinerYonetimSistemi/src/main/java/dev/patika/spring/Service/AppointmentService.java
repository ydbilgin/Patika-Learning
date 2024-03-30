package dev.patika.spring.Service;

import dev.patika.spring.Dto.Request.AppointmentRequest;
import dev.patika.spring.Entity.Animal;
import dev.patika.spring.Entity.Appointment;
import dev.patika.spring.Entity.Doctor;
import dev.patika.spring.Repository.AnimalRepo;
import dev.patika.spring.Repository.AppointmentRepo;
import dev.patika.spring.Repository.AvailableDateRepo;
import dev.patika.spring.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private AppointmentRepo appointmentRepository;
    private DoctorRepo doctorRepository;
    private AvailableDateRepo availableDateRepository;
    private AnimalRepo animalRepo;
    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepository, DoctorRepo doctorRepository, AvailableDateRepo availableDateRepository,AnimalRepo animalRepo) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.availableDateRepository = availableDateRepository;
        this.animalRepo = animalRepo;
    }

    // Randevu oluşturma

    // Randevu oluşturma
    public Appointment createAppointment(AppointmentRequest appointmentRequest) {
        LocalDateTime requestedDateTime = appointmentRequest.getAppointmentDate();

        if (requestedDateTime.getMinute() != 0 || requestedDateTime.getSecond() != 0) {
            throw new RuntimeException("Sadece saat başı randevu alınabilir.");
        }

        Long doctorId = appointmentRequest.getDoctor().getId();
        Long animalId = appointmentRequest.getAnimal().getId();
        LocalDate appointmentDate = requestedDateTime.toLocalDate();
        if (!doctorRepository.existsById(doctorId)) {
            throw new RuntimeException("Böyle bir doktor bulunmamaktadır!");

        } else {
            if (appointmentRepository.existsByAppointmentDateAndDoctor_Id(requestedDateTime, doctorId)) {
                throw new RuntimeException("Girilen tarihte başka bir randevu mevcuttur.");
            }

            if (!doctorRepository.isDoctorAvailableOnDate(doctorId, appointmentDate)) {
                throw new RuntimeException("Doktor bu tarihte çalışmamaktadır!");
            }

        }


        Appointment appointment = convertDtoToAppointment(appointmentRequest, animalId);
        appointment.setAppointmentDate(requestedDateTime); // Saat bilgisini atar

        return appointmentRepository.save(appointment);
    }
    private Appointment convertDtoToAppointment(AppointmentRequest appointmentRequest, Long animalId) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());

        Long doctorId = appointmentRequest.getDoctor().getId();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doktor bulunamadı."));
        appointment.setDoctor(doctor);


        Animal animal = animalRepo.findById(animalId).orElseThrow(() -> new RuntimeException("Hayvan bulunamadı."));
        appointment.setAnimal(animal);


        return appointment;
    }






    public List<Appointment> findAppointmentsByDateAndAnimal(LocalDate startDate, LocalDate endDate, Long id) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        return appointmentRepository.findByAppointmentDateBetweenAndAnimal_Id(startDateTime, endDateTime, id);
    }









    // Randevu bilgilerini güncelleme
    public Appointment updateAppointment(Appointment appointment) {
        try {
            // Güncelleme işlemi
            return appointmentRepository.save(appointment);
        } catch (DataIntegrityViolationException e) {
            // Veritabanı kısıtlaması hatası
            throw new RuntimeException("Güncelleme işlemi sırasında veritabanı kısıtlaması hatası oluştu.", e);
        }
    }

    // Randevu bilgilerini görüntüleme
    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    // Randevuları tarih aralığına ve doktora göre filtreleme
    public List<Appointment> findAppointmentsByDateAndDoctor(LocalDate startDate, LocalDate endDate, Long id) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        return appointmentRepository.findByAppointmentDateBetweenAndDoctor_Id(startDateTime, endDateTime, id);
    }


    // Randevuların tamamını getirme
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }


}