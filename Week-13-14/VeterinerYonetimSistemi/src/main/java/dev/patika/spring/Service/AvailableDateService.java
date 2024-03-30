package dev.patika.spring.Service;

import dev.patika.spring.Entity.AvailableDate;
import dev.patika.spring.Repository.AvailableDateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    @Autowired
    public AvailableDateService(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    //Doktor'a ait günleri bulma
    public List<AvailableDate> getAvailableDates(Long doctorId) {
        return availableDateRepo.findByDoctor_Id(doctorId);
    }



}
