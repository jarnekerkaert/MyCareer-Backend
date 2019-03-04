
package Realdolmen.MyCareer.domain;

import java.util.List;

public class FunctionListWrapper {
    private List<CurrentFunction> currentfunctions;
    private List<PrevFunction> prevfunctions;

    public List<CurrentFunction> getCurrentfunctions() {
        return currentfunctions;
    }

    public void setCurrentfunctions(List<CurrentFunction> currentfunctions) {
        this.currentfunctions = currentfunctions;
    }

    public List<PrevFunction> getPrevfunctions() {
        return prevfunctions;
    }

    public void setPrevfunctions(List<PrevFunction> prevfunctions) {
        this.prevfunctions = prevfunctions;
    }
    
    
}
