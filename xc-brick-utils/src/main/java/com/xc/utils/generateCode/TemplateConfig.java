package com.xc.utils.generateCode;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TemplateConfig {
    Integer id;
    String name;
    String path;
    String description;
}
