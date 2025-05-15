package com.sam.iisib24.web.ui;

import com.sam.iisib24.model.Archimed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j         //добавляет поле log, через которое можно писать сообщения в консоль
@Controller    //компонент, который обрабатывает HTTP-запросы и возвращает HTML-страницы
@RequiredArgsConstructor
public class ArchimedController {

    private final Archimed archimed;

    // GET-запрос для отображения страницы
    @GetMapping("/archimed")
    public String showArchimedPage() {
        return "archimed"; // Возвращаем название HTML-шаблона
    }

    // POST-запрос для вычисления силы Архимеда
    @PostMapping("/archimed")
    @ResponseBody
    public Map<String, Object> calculateArchimedForce(
            @RequestParam double density,
            @RequestParam double volume,
            @RequestParam double mass) {

        archimed.setDensity(density);
        archimed.setMass(mass);
        archimed.setVolume(volume);
        double archimedForce = archimed.calculatedArchimedForce();
        double gravityForce = archimed.calculatedGravityForce();

        log.info("логи работают");
        log.info("Archimedes: {}, Archimedes Force: {}, Gravity Force: {}", archimed, archimedForce, gravityForce);

        Map<String, Object> response = new HashMap<>();
        response.put("archimedForce", archimedForce);
        response.put("gravityForce", gravityForce);

        return response;
    }
}