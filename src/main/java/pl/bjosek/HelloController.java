package pl.bjosek;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pl.bjosek.strategy.OpperationType;
import pl.bjosek.strategy.Procesor;

@RestController
public class HelloController {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private Procesor procesor;

    @Autowired
    public HelloController(NamedParameterJdbcTemplate jdbcTemplate, Procesor procesor) {
        this.jdbcTemplate = jdbcTemplate;
        this.procesor = procesor;
    }

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class Result {
        private final int left;
        private final int right;
        private final long answer;

        public Result(int left, int right, long answer) {
            this.left = left;
            this.right = right;
            this.answer = answer;
        }
    }

    // SQL sample
    @RequestMapping("calc")
    Result calc(@RequestParam int left, @RequestParam int right) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("left", left)
                .addValue("right", right);
        return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
                (rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
    }

    @RequestMapping(path = "strategycalc/{opp}/{operand1}/{operand2}", method = RequestMethod.GET)
    public ResponseEntity<String> getOperation(@PathVariable("opp")OpperationType opperationType,
                                       @PathVariable("operand1")Long operand1,
                                       @PathVariable("operand2")Long operand2) {
        String result = "Hello from there : " + opperationType + " result : "
                + procesor.operate(opperationType, operand1, operand2);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
