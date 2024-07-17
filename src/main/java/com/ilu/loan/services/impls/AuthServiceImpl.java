package com.ilu.loan.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ilu.loan.dto.request.AuthRequest;
import com.ilu.loan.dto.response.AuthResponse;
import com.ilu.loan.entities.Auth;
import com.ilu.loan.entities.Customer;
import com.ilu.loan.entities.Role;
import com.ilu.loan.entities.User;
import com.ilu.loan.repositories.RoleRepository;
import com.ilu.loan.repositories.UserRepository;
import com.ilu.loan.securities.JWTSecurity;
import com.ilu.loan.services.AuthService;
import com.ilu.loan.services.CustomerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTSecurity jwtSecurity;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Auth auth = (Auth) authentication.getPrincipal();

        User user = userRepository.findByEmail(auth.getUsername()).get();

        String token = jwtSecurity.generateToken(user);

        return AuthResponse.builder().email(user.getEmail()).role(user.getRoles().stream().map(role -> role.getRole().name().replace("ROLE_", "").toLowerCase()).toList()).token(token).build();
    }

    @Override
    @Transactional
    public AuthResponse register(AuthRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();

        if (request.getIsCustomer() != null) {
            Role customerRole = roleRepository.findByRole(Role.ERole.ROLE_CUSTOMER);
            roles.add(customerRole);

        } else {
            Role adminRole = roleRepository.findByRole(Role.ERole.ROLE_ADMIN);
            Role staffRole = roleRepository.findByRole(Role.ERole.ROLE_STAFF);

            roles.add(adminRole);
            roles.add(staffRole);
        }

        user.setRoles(roles);

        userRepository.save(user);
        userRepository.flush();

        if (request.getIsCustomer() != null) {
            Customer customer = new Customer();
            customer.setUser(user);
            customerService.create(customer);
        }

        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRoles().stream()
                        .map(role -> role.getRole().name().replace("ROLE_", "").toLowerCase())
                        .toList())
                .build();
    }

}