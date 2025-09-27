package com.iuh.fit.ontapdepartment_2.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {
    private int id;
    private String name;
    private int departmentId;
    private double salary;
}
