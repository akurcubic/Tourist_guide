package rs.raf.turisticki_vodic_be.resources.user;

import rs.raf.turisticki_vodic_be.annotations.Authorize;
import rs.raf.turisticki_vodic_be.dto.requests.LoginRequest;
import rs.raf.turisticki_vodic_be.dto.requests.RegisterRequest;
import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.dto.responses.LoginResponse;
import rs.raf.turisticki_vodic_be.dto.responses.RegisterResponse;
import rs.raf.turisticki_vodic_be.entities.User;
import rs.raf.turisticki_vodic_be.services.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResources {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("ADMIN")
    @Path("/page/{page}")
    public List<User> getAllUsersByPage(@PathParam("page") Integer page) {
        return userService.getAllUserByPage(page);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse login(LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") Integer id) {
        return userService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("ADMIN")
    public List<User> getAll() {
        return userService.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("ADMIN")
    public User create(User user) {
        return userService.create(user);
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("ADMIN")
    public RegisterResponse register(RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("ADMIN")
    public Response changeUser(User user) {
        return userService.changeUser(user);
    }

    @GET
    @Path("/user_name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getByName(@PathParam("name") String name) {
        return userService.getByName(name);
    }

    @GET
    @Path("/jwt/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNameByJwt(@PathParam("jwt") String jwt){
        return userService.getUsernameByJwt(jwt);
    }

    @GET
    @Path("/jwt/type/{jwt}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTypeByJwt(@PathParam("jwt") String jwt){
        return userService.getTypeByJwt(jwt);
    }

    @PUT
    @Path("/change_status/{id}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("ADMIN")
    public Response changeUserStatus(@PathParam("id") Integer id,@PathParam("status") String status){
         return userService.changeUserStatus(id,status);
    }
}
