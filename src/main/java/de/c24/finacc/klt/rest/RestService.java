package de.c24.finacc.klt.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Service
 */
@RestController
@RequestMapping("/api")
public class RestService {

    /**
     * Test rest endpoint
     *
     * @return Map of key/values
     */
    @GetMapping("/testit")
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("key", "value");
        return response;
    }
}
