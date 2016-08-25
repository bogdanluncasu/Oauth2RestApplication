package configuration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(
			"select username,password,true from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from users u inner join"
			+ " roles r on u.role_id=r.role_id where username=?");


	}

    private PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable();
      http.authorizeRequests()
        .antMatchers("/users/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/resources/**").permitAll()
//        .antMatchers(HttpMethod.POST,"/register").permitAll()
        .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
        .antMatchers("/**").access("isAuthenticated()")
        .and().formLogin().loginPage("/")
        .failureUrl("/error")
        .usernameParameter("email").passwordParameter("password")
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}