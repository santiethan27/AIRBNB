//Dev Duque
package com.airbnb.airbnb.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicePropertyRequest {
    private String name;
    private String property;
}
