package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FractionalNumberWithBase extends NumberWithBase {

    public FractionalNumberWithBase(String representation, NumeralSystem numeralSystem) throws NumberBaseException, PluralRadixPointsError {
        super(representation, numeralSystem);
    }
    @Override
    protected BigDecimal convertToDecimal() throws NumberBaseException {
        int indexRadixPoint = representation.indexOf('.');
        BigDecimal sum = BigDecimal.ZERO;
        char character;
        int digit;
        int power;
        for (int index = 0; index < representation.length(); index++) {
            if (index == indexRadixPoint) {
                continue;
            }
            character = representation.charAt(index);
            digit = convertCharToDecimal(character);
            if (digit < 0 || getBase() <= digit) {
                throw new NumberBaseException(character, index);
            }
            if (index < indexRadixPoint) {
                power = indexRadixPoint - 1 - index;
            } else {
                power = indexRadixPoint - index;
            }
            sum = sum.add(calculateEachDigitsValue(digit, power));
        }
        return sum.setScale(NumberBaseConverter.LENGTH_FRACTIONAL_PART, RoundingMode.HALF_UP);
    }
}
