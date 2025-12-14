package com.myproject.Bike;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/bike")
@CrossOrigin(origins = "*")
public class BikeController {

    @Autowired 
    private bikeService bikeservice;

    // ================== ADD BIKE ==================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBike(
            @RequestParam String model,
            @RequestParam String brand,
            @RequestParam int price,
            @RequestParam String type,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        Bike bike = new Bike();
        bike.setModel(model);
        bike.setBrand(brand);
        bike.setPrice(price);
        bike.setType(type);
        bike.setImage(image.getBytes());

        bikeservice.addBike(bike);
        return ResponseEntity.status(HttpStatus.CREATED).body(bike);
    }

    // ================== GET ALL BIKES ==================
    @GetMapping("/getbike")
    public ResponseEntity<List<Bike>> getAllBikes() {
        List<Bike> bikes = bikeservice.getallbikesfromdb();
        return ResponseEntity.ok(bikes);
    }

    // ================== UPDATE BIKE ==================
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBike(
            @PathVariable int id,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String type,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {

        Optional<Bike> optionalBike = bikeservice.getBikeById(id);
        if (optionalBike.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bike not found with ID: " + id);
        }

        Bike existingBike = optionalBike.get();

        if (model != null) existingBike.setModel(model);
        if (brand != null) existingBike.setBrand(brand);
        if (price != null) existingBike.setPrice(price);
        if (type != null) existingBike.setType(type);
        if (image != null && !image.isEmpty()) existingBike.setImage(image.getBytes());

        Bike updatedBike = bikeservice.updateBike(existingBike);
        return ResponseEntity.ok(updatedBike);
    }

    // ================== DELETE BIKE ==================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBike(@PathVariable int id) {
        boolean deleted = bikeservice.deleteBike(id);
        if (deleted) {
            return ResponseEntity.ok("✅ Bike deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Bike not found with ID: " + id);
        }
    }
}
