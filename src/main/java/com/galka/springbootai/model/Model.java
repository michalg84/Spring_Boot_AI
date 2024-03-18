package com.galka.springbootai.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class Model {
    private final String question;
}
