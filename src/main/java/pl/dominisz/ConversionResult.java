package pl.dominisz;

import java.util.List;

public class ConversionResult {

    private static final int CODE_TIMEOUT = -1;

    private List<ConversionInfo> inf;

    public List<ConversionInfo> getInf() {
        return inf;
    }

    public boolean timeout(){
        return inf.get(0).getCode() == CODE_TIMEOUT;
    }
}
