package dev.patika.spring.Service;

import dev.patika.spring.Dto.Request.VaccineRequest;
import dev.patika.spring.Dto.Response.VaccineResponse;
import dev.patika.spring.Entity.Animal;
import dev.patika.spring.Entity.Vaccine;
import dev.patika.spring.Repository.AnimalRepo;
import dev.patika.spring.Repository.VaccineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final VaccineRepo vaccineRepository;
    private final AnimalRepo animalRepo;

    @Autowired
    public VaccineService(VaccineRepo vaccineRepository, AnimalRepo animalRepo) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepo = animalRepo;
    }



    public List<Vaccine> getVaccinesByAnimalId(Long id) {
        return vaccineRepository.findByAnimal_Id(id);
    }

    public List<Animal> getAnimalsWithExpiringVaccines(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findAnimalsWithExpiringVaccines(startDate, endDate);
    }

    //aşı kaydetme
    public VaccineResponse saveVaccine(VaccineRequest vaccineRequest) {
        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineRequest.getName());
        vaccine.setCode(vaccineRequest.getCode());
        vaccine.setProtectionStartDate(vaccineRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineRequest.getProtectionFinishDate());

        Animal animal = animalRepo.findById(vaccineRequest.getAnimal().getId())
                .orElseThrow(() -> new RuntimeException("Belirtilen ID'ye sahip hayvan bulunamadı."));
        vaccine.setAnimal(animal);

        List<Vaccine> vaccines = vaccineRepository.findByAnimalIdAndNameAndCode(vaccineRequest.getAnimal().getId(),vaccineRequest.getName(),vaccineRequest.getCode());

        if (!vaccines.isEmpty()) {
            throw new RuntimeException("Aynı tarihlerde aynı hayvana aynı aşıyı tekrar ekleyemezsiniz.");
        }

        if (vaccine.getProtectionFinishDate().isBefore(vaccine.getProtectionStartDate())) {
            throw new RuntimeException("Koruma bitiş tarihi koruma başlangıç tarihinden önce olamaz.");
        }

        vaccineRepository.save(vaccine);

        VaccineResponse vaccineResponse = new VaccineResponse();
        vaccineResponse.setId(vaccine.getId());
        vaccineResponse.setName(vaccine.getName());
        vaccineResponse.setCode(vaccine.getCode());
        vaccineResponse.setProtectionStartDate(vaccine.getProtectionStartDate());
        vaccineResponse.setProtectionFinishDate(vaccine.getProtectionFinishDate());

        // Animal nesnesini kontrol et ve eğer null değilse atamaları yap
        if (vaccine.getAnimal() != null) {
            long animalId = vaccine.getAnimal().getId();
            Optional<Animal> animalVaccine = animalRepo.findById(animalId);
            vaccineResponse.setAnimal(animalVaccine.orElse(null));
        }

        return vaccineResponse;
    }

    public boolean isAnimalExist(Long animalId) {
        return animalRepo.existsById(animalId);
    }


}
