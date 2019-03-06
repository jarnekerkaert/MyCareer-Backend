
package Realdolmen.MyCareer.qualities.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

//@JsonFormat(shape = JsonFormat.Shape.STRING)
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QualityType implements Serializable{
    STRONG,WEAK;
//       STRONG("STRONG"),WEAK("WEAK");
////    
////    public String getStatus() {
////        return this.name();
////    }
//    
//    private String type;
//    
//    private QualityType(String type){
//        this.type = type;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
    
    
}
