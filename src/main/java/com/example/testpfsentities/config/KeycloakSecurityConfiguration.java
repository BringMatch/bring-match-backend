package com.example.testpfsentities.config;

import com.example.testpfsentities.entities.enums.Role;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider =
                keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    /**
     * Tell Spring Security that keycloak will take care of users
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.cors().and().authorizeRequests().antMatchers("/owners/**").hasAuthority(Role.OWNER.name())
//                .and();

        http.csrf().disable().authorizeRequests()
                .antMatchers("/h2-console/**","/owners/save/**").permitAll()
                .antMatchers("/owners/**")
                .hasAuthority(Role.OWNER.name())
                .antMatchers("/players/**")
                .hasAuthority(Role.PLAYER.name())
                .antMatchers("/admins/**")
                .hasAuthority(Role.ADMIN.name())
                .and()
                .headers(
                        headers -> headers
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                )
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ;
//        http.csrf().disable().
//                authorizeRequests()
//                .antMatchers("/h2-console/**")
//                .permitAll().and()
////                .antMatchers("/players/**")
////                .hasRole("player")
////                .antMatchers("/admins/**")
////                .hasRole("ADMIN")
////                //.anyRequest().denyAll().and()
////                .anyRequest().authenticated().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http.headers().frameOptions().disable();
    }
}