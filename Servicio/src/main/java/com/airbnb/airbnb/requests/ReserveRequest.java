/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airbnb.airbnb.requests;

import com.airbnb.airbnb.enums.ReservaType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveRequest {
    
    private String client;
    private String property;
    private String detail;
    private Integer total_quatity;
    private Date startDate;
    private Date endDate;
    private ReservaType state;
    
}
