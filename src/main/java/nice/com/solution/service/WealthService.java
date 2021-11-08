package nice.com.solution.service;

import nice.com.solution.dataTransferObject.RichDTO;
import nice.com.solution.model.ApiError;
import nice.com.solution.ecxeptionHandler.AppException;
import nice.com.solution.model.Person;
import nice.com.solution.model.Rich;
import nice.com.solution.repository.IRichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WealthService {

    @Value("${evaluateUrl}")
    private String evaluateUrl;
    @Value("${wealthThresholdUrl}")
    private String wealthThresholdUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IRichRepository richRepository;

    public String wealthRating(Person person) throws Exception {
        if (person == null) {
            throw new AppException(new ApiError(HttpStatus.BAD_REQUEST, "The Person object is missing"));
        }

        String city = person.getPersonalInfo().getCity();

        BigDecimal evaluateResponse = restTemplate.getForObject(evaluateUrl + city, BigDecimal.class);
        BigDecimal wealthThresholdResponse = restTemplate.getForObject(wealthThresholdUrl, BigDecimal.class);

        BigDecimal cash = person.getFinancialInfo().getCash();
        int numberOfAssets = person.getFinancialInfo().getNumberOfAssets();

        validateParams(evaluateResponse, wealthThresholdResponse, cash, numberOfAssets);

        BigDecimal fortune = cash.multiply(evaluateResponse.multiply(cash));
        if (fortune.compareTo(wealthThresholdResponse) > 0) {
            saveRichEntity(person, fortune);
            return "This person is considered as a Rich";
        }

        return "This person is NOT considered as a Rich";
    }

    public List<RichDTO> getAllRichPersons() {
        List<Rich> riches = richRepository.findAll();
        List<RichDTO> richDTOList = new ArrayList<>();
        for (Rich rich : riches) {
            richDTOList.add(convertToRichDto(rich));
        }
        return richDTOList;
    }

    public RichDTO getRichPersonById(long richId) throws Exception {
        Optional<Rich> rich = richRepository.findById(richId);
        if (rich.isEmpty()) {
            throw new AppException(new ApiError(HttpStatus.NOT_FOUND, "Entity not found"));
        }
        return convertToRichDto(rich.get());
    }



    // private methods for this class
    private void validateParams(BigDecimal evaluateResponse, BigDecimal wealthThresholdResponse, BigDecimal cash, Integer numberOfAssets) throws Exception {
        if (evaluateResponse == null || wealthThresholdResponse == null) {
            throw new AppException(new ApiError(HttpStatus.SERVICE_UNAVAILABLE, "The central bank MS is not available"));
        }
        if (cash == null || numberOfAssets == null) {
            throw new AppException(new ApiError(HttpStatus.BAD_REQUEST, "One of the parameters is missing"));
        }
    }

    private void saveRichEntity(Person person, BigDecimal fortune) throws Exception {
        String firstName = person.getPersonalInfo().getFirstName();
        String lastName = person.getPersonalInfo().getLastName();

        Rich rich = new Rich();
        rich.setFirstName(firstName);
        rich.setLastName(lastName);
        rich.setFortune(fortune.doubleValue());

        // Should check first if this person already exist in DB by unique. For example:
//        Optional<Rich> richFromDb = richRepository.findByEmail(person.getPersonalInfo().getEmail());
//        if (!richFromDb.isEmpty()){
//            System.out.println("This rich person already exist in DB");
//        }

        try {
            richRepository.save(rich);
        } catch (Exception e) {
            throw new AppException(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save entity to DB"));
        }
    }

    private RichDTO convertToRichDto(Rich rich) {

        RichDTO richDTO = new RichDTO();
        richDTO.setRichId(rich.getRichId());
        richDTO.setFortune(BigDecimal.valueOf(rich.getFortune()));
        richDTO.setFirstName(rich.getFirstName());
        richDTO.setLastName(rich.getLastName());

        return richDTO;
    }

}
