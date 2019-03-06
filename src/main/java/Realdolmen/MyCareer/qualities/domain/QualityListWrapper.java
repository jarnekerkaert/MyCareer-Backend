
package Realdolmen.MyCareer.qualities.domain;

import java.util.List;

public class QualityListWrapper {
    private List<StrongQuality> strongqualities;
    private List<WeakQuality> weakqualities;

    public List<StrongQuality> getStrongqualities() {
        return strongqualities;
    }

    public void setStrongqualities(List<StrongQuality> strongqualities) {
        this.strongqualities = strongqualities;
    }

    public List<WeakQuality> getWeakqualities() {
        return weakqualities;
    }

    public void setWeakqualities(List<WeakQuality> weakqualities) {
        this.weakqualities = weakqualities;
    }
    
    
}
