package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Controller
public class GreetingController {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String baseURL = "http://localhost:8090/currencyconverter/";

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="usd", required=false, defaultValue="World") String usd, Model model) {
        model.addAttribute("usd", usd);

        BigDecimal usdToInr = restTemplate.getForObject(baseURL, BigDecimal.class);
        model.addAttribute("rate", usdToInr);

        return "greeting";
    }

}