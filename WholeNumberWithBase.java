package converter;

import java.math.BigDecimal;

public class WholeNumberWithBase extends NumberWithBase {


    public WholeNumberWithBase(String representation, NumeralSystem numeralSystem) throws NumberBaseException, PluralRadixPointsError {
        super(representation, numeralSystem);
    }

    @Override
    protected BigDecimal convertToDecimal() throws NumberBaseException {
        BigDecimal sum = BigDecimal.ZERO;
        char character;
        int digit;
        int power;
        for (int index = 0; index < representation.length(); index++) {
            character = representation.charAt(index);
            digit = convertCharToDecimal(character);
            if (digit < 0 || getBase() <= digit) {
                throw new NumberBaseException(character, index);
            }
            power = representation.length() - 1 - index;
            sum = sum.add(calculateEachDigitsValue(digit, power));
        }
        return sum;
    }
}
