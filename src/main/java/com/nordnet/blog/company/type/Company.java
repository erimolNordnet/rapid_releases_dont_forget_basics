package com.nordnet.blog.company.type;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Company {
    String ceo;
    String email;
    String headquarter;
    LocalDate introductionDate;
    String isinCode;
    String name;
    String url;
}
