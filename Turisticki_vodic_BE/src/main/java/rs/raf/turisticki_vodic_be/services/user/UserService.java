package rs.raf.turisticki_vodic_be.services.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.turisticki_vodic_be.dto.requests.LoginRequest;
import rs.raf.turisticki_vodic_be.dto.requests.RegisterRequest;
import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.dto.responses.LoginResponse;
import rs.raf.turisticki_vodic_be.dto.responses.RegisterResponse;
import rs.raf.turisticki_vodic_be.entities.User;
import rs.raf.turisticki_vodic_be.entities.enums.UserType;
import rs.raf.turisticki_vodic_be.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserService {

    private final String SECRET_KEY = "SECRET_KEY";

    @Inject
    UserRepository userRepository;

    public List<User> getAllUserByPage(int page){
        return userRepository.getAllUserByPage(page);
    }

    public Response changeUserStatus(int id,String status){
        User u = userRepository.getById(id);
        u.setStatus(status);
        userRepository.changeUser(u);
        return new Response(200,"Status changed!");
    }


    public User getByName(String name){
        return userRepository.getByName(name);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        int statusCode = 200;
        String jwt = null;
        String message = "";

        User user = userRepository.getByEmail(loginRequest.getEmail());

        if(user.getStatus().equals("INACTIVE")){
            return new LoginResponse(jwt,402,"Your account is INACTIVE!");
        }

        String hashedPassword = DigestUtils.sha256Hex(loginRequest.getPassword());
        System.out.println("Korisnik: " + user.getEmail());
        System.out.println("Uneta loznika: " + hashedPassword);
        System.out.println("Njegova lozinka " + user.getPassword());


        if (user != null && user.getPassword().equals(hashedPassword)) {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            jwt = JWT.create()
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName() + " " + user.getSurname())
                    .withClaim("type", user.getType().toString())
                    .sign(algorithm);
        } else {
            statusCode = 401;
            message = "Invalid e-mail or password!";
        }
        return new LoginResponse(jwt,statusCode,message);
    }

    public RegisterResponse register(RegisterRequest registerRequest) {


        if(userRepository.getByEmail(registerRequest.getEmail()) != null)
            return new RegisterResponse(null,400,"E-mail is already used!");


        String hashedPassword = DigestUtils.sha256Hex(registerRequest.getPassword());

        User user = new User(0,registerRequest.getEmail(),registerRequest.getName(),registerRequest.getSurname(),registerRequest.getType(),"ACTIVE",hashedPassword);

        user = userRepository.create(user);

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String jwt = JWT.create()
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("type", user.getType().toString())
                .sign(algorithm);


        return new RegisterResponse(jwt,200,"OK");
    }

    public List<User> getAll() {
        return userRepository.getAllUsers();
    }


    ///obicno kreiranje korisnika, ali treba da se kreira pomocu registrovanja
    public User create(User user) {
        String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println(hashedPassword.length());
        return userRepository.create(user);
    }

    public Response changeUser(User changedUser){

        User user = userRepository.getById(changedUser.getId());
        if(user == null)
            return new Response(500,"Error not found");
        String hashedPassword = DigestUtils.sha256Hex(changedUser.getPassword());
        user.setEmail(changedUser.getEmail());
        user.setName(changedUser.getName());
        user.setSurname(changedUser.getSurname());
        user.setType(changedUser.getType());
        //user.setStatus(changedUser.getStatus());
        //user.setPassword(hashedPassword);

        User u = userRepository.changeUser(user);

        if(u != null)
            return new Response(200,"OK");

        return new Response(500,"Error while changing user!");

    }

    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public String getUsernameByJwt(String token){

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);


        String nameAndSurname = jwt.getClaim("name").asString();
        System.out.println("name je iz jwta " + nameAndSurname);

        String[] niz = nameAndSurname.split(" ");

        return niz[0];

    }

    public String getTypeByJwt(String token){

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);


        String type = jwt.getClaim("type").asString();
        System.out.println("type iz jwta " + type);

        return type;
    }

    public boolean isAuthorized(String token, String requiredRole){


        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String nameAndSurname = jwt.getClaim("name").asString();
        System.out.println("name je iz jwta " + nameAndSurname);

        String[] niz = nameAndSurname.split(" ");
        String name = niz[0];


        User user = userRepository.getByName(name);

        String role = jwt.getClaim("type").asString();


        if (user == null){
            return false;
        }

        UserType enumRole = UserType.valueOf(role);
        UserType enumRequiredRole = UserType.valueOf(requiredRole);
        System.out.println(enumRole);
        System.out.println(enumRequiredRole);

        if(enumRole.ordinal() <= enumRequiredRole.ordinal()){
            return true;
        }
        else
            return false;
    }
}
