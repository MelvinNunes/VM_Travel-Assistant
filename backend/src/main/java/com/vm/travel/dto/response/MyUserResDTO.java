package com.vm.travel.dto.response;

import com.vm.travel.domain.entities.User;

public record MyUserResDTO(
        String message,
        User data
) {
}
