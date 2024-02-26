package taito.com.calorie.security;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service; 
import taito.com.calorie.model.User; 
import taito.com.calorie.repository.UserRepository; 

@Service
public class SecurityUserDetailsService implements UserDetailsService { 
   @Autowired 
   private UserRepository userRepository; 
   
   @Override 
   public UserDetails loadUserByUsername(String username) 
   throws UsernameNotFoundException { 
      User user = userRepository.findUserByUsername(username) 
         .orElseThrow(() -> new UsernameNotFoundException("User not present")); 
         return user; 
   } 
   public void createUser(UserDetails user) { 
      userRepository.save((User) user); 
   } 
}