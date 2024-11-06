//Dev Duque
package com.airbnb.airbnb.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String[] toUser;
    private String subject;
    private String message;
}
