
package com.denschmied.metrology.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class DateRequest {
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate definiteDate;
    
   
    public DateRequest() {}
    
    public DateRequest(LocalDate definiteDate) {
        this.definiteDate = definiteDate;
    }
    
    public LocalDate getDefiniteDate() {
        return definiteDate;
    }
    
    public void setDefiniteDate(LocalDate definiteDate) {
        this.definiteDate = definiteDate;
    }    
}
