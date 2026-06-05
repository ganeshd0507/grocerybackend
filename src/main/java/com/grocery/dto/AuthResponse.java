package com.grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private UserResponseDTO user;
    private String token;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResponseDTO {
        private String name;
        private String email;
        private String phone;
        private String role;
        @Builder.Default
        private List<AddressDTO> savedAddresses = new ArrayList<>();
        @Builder.Default
        private List<String> wishlist = new ArrayList<>();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressDTO {
        private String id;
        private String tag;
        private String address;
    }
}
