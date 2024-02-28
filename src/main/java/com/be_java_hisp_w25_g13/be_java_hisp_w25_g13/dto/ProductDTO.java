package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @NotNull(message = "El  id no puede estar vacío")
    @Positive(message = "El id debe ser mayor a cero")
    private Integer productId;

    @NotBlank(message = "El campo no puede estar vacío")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "El campo no puede poseer caracteres especiales")
    private String productName;

    @NotEmpty(message = "El campo no puede estar vacío")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "El campo no puede poseer caracteres especiales")
    private String type;

    @NotEmpty(message = "El campo no puede estar vacío")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "El campo no puede poseer caracteres especiales")
    private String brand;

    @NotEmpty(message = "El campo no puede estar vacío")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "El campo no puede poseer caracteres especiales")
    private String color;

    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "El campo no puede poseer caracteres especiales")
    private String notes;
}
