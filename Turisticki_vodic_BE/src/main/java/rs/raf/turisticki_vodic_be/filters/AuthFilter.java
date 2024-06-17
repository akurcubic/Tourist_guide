package rs.raf.turisticki_vodic_be.filters;

import rs.raf.turisticki_vodic_be.annotations.Authorize;
import rs.raf.turisticki_vodic_be.resources.activity.ActivityResources;
import rs.raf.turisticki_vodic_be.resources.article.ArticleResources;
import rs.raf.turisticki_vodic_be.resources.articleactivity.ArticleActivityResources;
import rs.raf.turisticki_vodic_be.resources.comment.CommentResources;
import rs.raf.turisticki_vodic_be.resources.destination.DestinationResources;
import rs.raf.turisticki_vodic_be.resources.user.UserResources;
import rs.raf.turisticki_vodic_be.services.user.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {


        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        Method resourceMethod = resourceInfo.getResourceMethod();
        if (resourceMethod != null && resourceMethod.isAnnotationPresent(Authorize.class)) {

            String token = requestContext.getHeaderString("Authorization");
            System.out.println(token);
            if (token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            Authorize authorizeAnnotation = resourceMethod.getAnnotation(Authorize.class);
            String requiredRole = authorizeAnnotation.value();
            System.out.println("Role je " + requiredRole);

            if (!userService.isAuthorized(token,requiredRole)) {

                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }

        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof DestinationResources ||
                    matchedResource instanceof UserResources ||
                    matchedResource instanceof CommentResources ||
                    matchedResource instanceof ArticleActivityResources ||
                    matchedResource instanceof ArticleResources ||
                    matchedResource instanceof ActivityResources) {
                return true;
            }
        }

        return false;
    }
}
