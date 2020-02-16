package pl.bjosek.strategy;

public class MulOperation implements Operation {
    @Override
    public Long operate(Long op1, Long op2) {
        return op1 * op2;
    }
}
