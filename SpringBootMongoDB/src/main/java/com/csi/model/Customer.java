package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer {
    @Id
    private int custId;
    private String custName;
    private String custAddress;
    private long custContactNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;
    private String custEmailId;
    private String custPassword;



}
