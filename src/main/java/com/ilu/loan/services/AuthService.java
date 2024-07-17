package com.ilu.loan.services;

import com.ilu.loan.dto.request.AuthRequest;
import com.ilu.loan.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    AuthResponse register(AuthRequest request);
}