package com.sam.iisib24.web.ui;

import com.sam.iisib24.model.LawOfMomentum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class MomentumRestController {

    // GET-запрос для отображения страницы
    @GetMapping("/momentum")
    public String showMomentumPage() {
        return "momentum"; // Возвращаем название HTML-шаблона
    }

    // POST-запрос для вычисления импульса
    @PostMapping("/momentum")
    @ResponseBody
    public Map<String, Object> calculateMomentum(
            @RequestParam double speedBeforeFirst,
            @RequestParam double speedBeforeSecond,
            @RequestParam double massFirst,
            @RequestParam double massSecond) {

        // Создаем экземпляр компонента
        LawOfMomentum lawOfMomentum = new LawOfMomentum();

        // Устанавливаем значения
        lawOfMomentum.setMassFirst(massFirst);
        lawOfMomentum.setMassSecond(massSecond);
        lawOfMomentum.setSpeedBeforeFirst(speedBeforeFirst);
        lawOfMomentum.setSpeedBeforeSecond(speedBeforeSecond);

        // Получаем скорости после столкновения
        double speedAfterFirst = lawOfMomentum.calculateSpeedAfterFirst();
        double speedAfterSecond = lawOfMomentum.calculateSpeedAfterSecond();

        // Возвращаем результат в формате JSON
        Map<String, Object> response = new HashMap<>();
        response.put("speedAfterFirst", speedAfterFirst);
        response.put("speedAfterSecond", speedAfterSecond);

        return response;
    }
}

