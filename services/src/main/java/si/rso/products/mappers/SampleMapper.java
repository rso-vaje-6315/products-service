package si.rso.products.mappers;

import si.rso.products.lib.Sample;
import si.rso.products.persistence.SampleEntity;

public class SampleMapper {
    
    public static Sample fromEntity(SampleEntity entity) {
        return new Sample();
    }
    
}