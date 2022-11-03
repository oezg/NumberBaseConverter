package converter;

import java.math.BigDecimal;

public class FractionalNumberWithBase extends WholeNumberWithBase {


    public FractionalNumberWithBase(String representation, NumeralSystem numeralSystem) throws NumberBaseException {
        super(representation, numeralSystem);
    }

    @Override
    protected BigDecimal convertToDecimal() {
        
    }
}
