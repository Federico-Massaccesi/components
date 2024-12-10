package it.personal.Components.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    @Bean
    PasswordEncoder stdPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    AuthTokenFilter authenticationJwtToken() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // Utilizza la configurazione CORS
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST,"/users/login").permitAll()
                              .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                                .requestMatchers(HttpMethod.GET,"/users").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/users/{id}").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/users/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/products").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/categories").permitAll()
                                .requestMatchers(HttpMethod.POST,"/categories").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/products/{id}").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/api/products/{id}/availability").hasAnyAuthority("ADMIN","WAREHOUSE")
                                .requestMatchers(HttpMethod.PATCH,"/api/orders/{id}/completed").hasAuthority("WAREHOUSE")
                                .requestMatchers(HttpMethod.PATCH,"/api/orders/{id}/pending").hasAuthority("COMPANY")
                                .requestMatchers(HttpMethod.PATCH,"/api/orders/{id}/checked").hasAuthority("WAREHOUSE")
                                .requestMatchers(HttpMethod.POST,"/api/orders").hasAnyAuthority("PRIVATE","COMPANY")
                                .requestMatchers(HttpMethod.GET,"/api/orders/**").authenticated()
                                .requestMatchers(HttpMethod.POST,"/chat").permitAll()

                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //COMUNICA ALLA FILTERCHAIN QUALE FILTRO UTILIZZARE, SENZA QUESTA RIGA DI CODICE IL FILTRO NON VIENE RICHIAMATO
                .addFilterBefore(authenticationJwtToken(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
