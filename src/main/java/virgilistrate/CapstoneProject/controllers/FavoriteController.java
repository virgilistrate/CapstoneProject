package virgilistrate.CapstoneProject.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @PostMapping("/{carId}")
    public String addFavorite(@PathVariable Long carId) {
        return "Auto " + carId + " aggiunta ai preferiti";
    }
}