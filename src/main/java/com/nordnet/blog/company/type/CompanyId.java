package com.nordnet.blog.company.type;

import lombok.Value;

@Value(staticConstructor = "of")
public class CompanyId {
    String value;
}
