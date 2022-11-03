package converter;

import java.math.BigDecimal;
import java.util.Objects;


public class WholeNumberWithBase extends NumberWithBase {


    public WholeNumberWithBase(String representation, NumeralSystem numeralSystem) throws NumberBaseException {
        super(representation, numeralSystem);
    }

    @Override
    protected BigDecimal convertToDecimal() throws NumberBaseException {
        BigDecimal sum = BigDecimal.ZERO;
        for (int index = 0; index < representation.length(); index++) {
            char character = representation.charAt(index);
            int digit = convertCharToDecimal(character);
            if (digit < 0 || numeralSystem.getBase() <= digit) {
                throw new NumberBaseException(character, index);
            }
            int power = representation.length() - 1 - index;
            sum = sum.add(calculateEachDigitsValue(digit, power));
        }
        return sum;
    }

    private BigDecimal calculateEachDigitsValue(int digit, int power) {
        return BigDecimal.valueOf(numeralSystem.getBase()).pow(power).multiply(BigDecimal.valueOf(digit));
    }

    static int convertCharToDecimal(char digit) {
        if ('0' <= digit && digit <= '9') {
            return digit - '0';
        }
        if ('a' <= digit && digit <= 'z') {
            return 10 + digit - 'a';
        }
        return -1;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WholeNumberWithBase that = (WholeNumberWithBase) o;
        return getBase() == that.getBase() && Objects.equals(getRepresentation(), that.getRepresentation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRepresentation(), getBase());
    }

    @Override
    public String toString() {
        return getRepresentation();
    }


}
