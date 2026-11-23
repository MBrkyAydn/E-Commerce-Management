package com.berkay.ecommerce_management.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Category name is required")
        @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
        String name,


        @Size(max = 250, message = "Description cannot exceed 250 characters")
        String description
) {


}
