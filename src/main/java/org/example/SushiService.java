package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class SushiService {

    @Autowired
    private SushiRepository sushiRepository;

    @PostConstruct
    public void initData() {
        if (sushiRepository.count() == 0) {
            sushiRepository.save(new Sushi("Филадельфия", "Лосось, сливочный сыр, огурец", 450.0, 15));
            sushiRepository.save(new Sushi("Калифорния", "Краб, авокадо, огурец, икра", 380.0, 12));
            sushiRepository.save(new Sushi("Унаги", "Угорь, авокадо, соус унаги", 520.0, 18));
            sushiRepository.save(new Sushi("Аляска", "Лосось, огурец, авокадо", 420.0, 14));
            sushiRepository.save(new Sushi("Дракон", "Угорь, угорь, авокадо", 580.0, 20));
        }
    }

    public List<Sushi> getAllSushi() {
        return sushiRepository.findAll();
    }

    public Optional<Sushi> getSushiById(Long id) {
        return sushiRepository.findById(id);
    }

    public Sushi saveSushi(Sushi sushi) {
        return sushiRepository.save(sushi);
    }

    public void deleteSushi(Long id) {
        sushiRepository.deleteById(id);
    }

    public List<Sushi> searchSushiByName(String name) {
        return sushiRepository.findByNameContainingIgnoreCase(name);
    }
}