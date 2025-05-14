package it.ecostanzi.pastries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pastries")
public class PastryController {

    private static final Logger log = LoggerFactory.getLogger(PastryController.class);

    private final Map<String, Pastry> pastryStore = new HashMap<>();

    public PastryController() {
        // Seed data
        pastryStore.put("Tartelette Fraise", new Pastry("Tartelette Fraise", "Delicieuse Tartelette aux Fraises fraiches", "S", 2.0, "available"));
        pastryStore.put("Divorces", new Pastry("Divorces", "Delicieux Divorces pas calorique du tout", "M", 2.8, "available"));
        pastryStore.put("Eclair Cafe", new Pastry("Eclair Cafe", "Delicieux Eclair au Cafe pas calorique du tout", "M", 2.5, "available"));
        pastryStore.put("Baba Rhum", new Pastry("Baba Rhum", "Delicieux Baba au Rhum pas calorique du tout", "L", 3.2, "available"));
        pastryStore.put("Millefeuille", new Pastry("Millefeuille", "Delicieux Millefeuille pas calorique du tout", "L", 4.4, "available"));
    }

    @GetMapping
    public ResponseEntity<List<Pastry>> getPastriesBySize(@RequestParam String size) {

        log.info("Getting pastries by size {}", size);
        List<Pastry> filtered = pastryStore.values().stream()
                .filter(p -> p.getSize().equalsIgnoreCase(size))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Pastry> getPastryByName(@PathVariable String name) {

        log.info("Getting pastry with name {}", name);
        Pastry pastry = pastryStore.get(name);
        if (pastry != null) {
            return ResponseEntity.ok(pastry);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Pastry> patchPastry(@PathVariable String name, @RequestBody Pastry updated) {

        log.info("Patching pastry with name {}", name);
        Pastry existing = pastryStore.get(name);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Apply patch logic (only price is expected to change)
        if (updated.getPrice() != null) {
            existing.setPrice(updated.getPrice());
        }
        return ResponseEntity.ok(existing);
    }
}
