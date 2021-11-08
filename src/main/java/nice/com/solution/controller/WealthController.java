package nice.com.solution.controller;

import nice.com.solution.dataTransferObject.RichDTO;
import nice.com.solution.model.Person;
import nice.com.solution.model.Rich;
import nice.com.solution.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class WealthController {

    @Autowired
    private WealthService wealthService;

    @PostMapping("/wealth-rating")
    public String  wealthRating(@RequestBody Person person) throws Exception {
        return wealthService.wealthRating(person);
    }

    @GetMapping("/all-rich")
    public List<RichDTO> getAllRichPersons(){
        return wealthService.getAllRichPersons();
    }

    @GetMapping("/rich-by-id/{id}")
    public RichDTO getRichPersonById(@PathVariable long id) throws Exception {
        return wealthService.getRichPersonById(id);
    }

}
