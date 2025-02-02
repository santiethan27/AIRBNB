
package com.airbnb.airbnb.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailFile {
    private String[] toUser;
    private String subject;
    private String message;
    MultipartFile file;
}
