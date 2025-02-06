package com.system.ventas.config.model;

public class ApiEndpoints {
    public static final String[] ENDPOINTS_SADMIN = {
            "/v1/security/**"
    };

    public static final String[] ENDPOINTS_ADMIN = {
            "/profile",
            "/v1/system/**",
            "/v1/inventary/**",
            "/v1/secutiry/**",
    };

    public static final String[] ENDPOINTS_MANAGER = {
            "/v1/reports/**",
            "/v1/analytics/**"
    };

    private ApiEndpoints() {
        // Constructor privado para evitar instanciación
    }
}
