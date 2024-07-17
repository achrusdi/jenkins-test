package com.ilu.loan.dto.request;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.ilu.loan.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String status;
    private String dateOfBirth;

    public Customer toEntity() {
        LocalDate parsedDate = LocalDate.parse(dateOfBirth);
        Timestamp timestamp = Timestamp.valueOf(parsedDate.atStartOfDay());

        return Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .status(status)
                .dateOfBirth(timestamp)
                .build();
    }
}