package pl.bjosek.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Procesor {

    Map<OpperationType, Operation> operations = new HashMap<>();

    public Procesor() {
        operations.put(OpperationType.ADD, new AddOperation());
        operations.put(OpperationType.MUL, new MulOperation());
        operations.put(OpperationType.SUB, new SubOperation());
    }

    public Long operate(OpperationType opperationType, Long op1, Long op2) {
        return operations.get(opperationType).operate(op1, op2);
    }
}
