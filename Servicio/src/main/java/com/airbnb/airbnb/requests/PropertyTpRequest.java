//Dev Duque
package com.airbnb.airbnb.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTpRequest {
    private String title;
    private MultipartFile images;
}
