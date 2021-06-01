package web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import web.config.handler.LoginSuccessHandler;
import web.service.AdminServiceImp;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final  LoginSuccessHandler successUserHandler;
    private final AdminServiceImp adminServiceImp;
    private final PasswordEncoder passwordEncoder;


    public SecurityConfig(LoginSuccessHandler successUserHandler, AdminServiceImp adminServiceImp, PasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.adminServiceImp = adminServiceImp;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/").authenticated()// доступность всем
                .antMatchers("/user")
                .access("hasAnyRole('ROLE_USER')") // разрешаем входить на /user пользователям с ролью User
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin() // Spring сам подставит свою логин форму
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                //указываем логику обработки при логине
                .successHandler(successUserHandler);// подключаем наш SuccessHandler для перенеправления по ролям

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(adminServiceImp)
                    .passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}