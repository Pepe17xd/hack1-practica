package com.auth;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User register(RegisterRequest request) {

        if (request.getRole().equals("BRANCH") && request.getBranch() == null) {
            throw new RuntimeException("Branch requerido");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole().equals("CENTRAL") ?
                Role.ROLE_CENTRAL : Role.ROLE_BRANCH);

        user.setBranch(request.getBranch());

        return userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, user.getRole().name(), user.getBranch());
    }
}
