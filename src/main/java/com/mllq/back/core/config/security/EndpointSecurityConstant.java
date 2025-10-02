package com.mllq.back.core.config.security;

public class EndpointSecurityConstant {
        public static final String[] ENDPOINT_PUBLIC = {
                        "/api/auth/register",
                        "/api/auth/login",
                        "/api/persona/**",
                        "/api/persona/register",
                        "/api/persona/update/**",
                        "/api/persona/delete/**",
                        "/api/persona/all",
                        "/api/empadronamiento/**",
                        "/api/empadronamiento/update/**",
                        "/api/empadronamiento/delete/**",
                        "/api/empadronamiento/all",
                        "/api/empadronamiento/register",
                        "/api/fertilizante/register",
                        "/api/fertilizante/update/**",
                        "/api/fertilizante/update/**",
                        "/api/fertilizante/delete/**",
                        "/api/fertilizante/all",
                        "/api/zonas/register",
                        "/api/zonas/update/**",
                        "/api/zonas/delete/**",
                        "/api/zonas/all",
                        "/api/variedad/register",
                        "/api/variedad/update/**",
                        "/api/variedad/delete/**",
                        "/api/variedad/all",
                        "/api/usuarios/register",
                        "/api/usuarios/update/**",
                        "/api/usuarios/delete/**",
                        "/api/usuarios/all",
                        "/api/rolusuarios/register",
                        "/api/rolusuarios/update/**",
                        "/api/rolusuarios/delete/**",
                        "/api/rolusuarios/all",
                        "/api/tipoparcela/register",
                        "/api/tipoparcela/update/**",
                        "/api/tipoparcela/delete/**",
                        "/api/tipoparcela/all",
                        "/api/usuariozona/register",
                        "/api/usuariozona/update/**",
                        "/api/usuariozona/delete/**",
                        "/api/usuariozona/all",
                        "/api/public/**",
                        "/h2-console/**",
        };

        public static final String[] ENDPOINT_PRIVATE = {
                        "/api/v1/**",
                        "/api/socket"

        };
        public static final String[] ENDPOINT_SWAGGER = {
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/swagger-ui/**"
        };
}
