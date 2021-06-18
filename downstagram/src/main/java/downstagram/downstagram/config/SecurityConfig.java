package downstagram.downstagram.config;

import downstagram.downstagram.service.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/", "/resources/**")
                .permitAll()
                .antMatchers("/**").authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/guest/login")
                .loginProcessingUrl("/guest/login_processing")
                .failureUrl("/guest/login?error")
                .defaultSuccessUrl("/main", true)
                .usernameParameter("loginId")
                .passwordParameter("passwd");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/main/logout_processing"))
                .logoutSuccessUrl("/guest/login")
                .invalidateHttpSession(true);

        http.authenticationProvider(myAuthenticationProvider);
    }
}
