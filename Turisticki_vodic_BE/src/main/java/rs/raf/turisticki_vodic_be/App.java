
package rs.raf.turisticki_vodic_be;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.turisticki_vodic_be.filters.AuthFilter;
import rs.raf.turisticki_vodic_be.filters.Cors;
import rs.raf.turisticki_vodic_be.repositories.activity.ActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.activity.MySqlActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.article.ArticleRepository;
import rs.raf.turisticki_vodic_be.repositories.article.MySqlArticleRepository;
import rs.raf.turisticki_vodic_be.repositories.articleandactivity.ArticleAndActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.articleandactivity.MySqlArticleAndActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.comment.CommentRepository;
import rs.raf.turisticki_vodic_be.repositories.comment.MySqlCommentRepository;
import rs.raf.turisticki_vodic_be.repositories.destination.DestinationRepository;
import rs.raf.turisticki_vodic_be.repositories.destination.MySqlDestinationRepository;
import rs.raf.turisticki_vodic_be.repositories.user.MySqlUserRepository;
import rs.raf.turisticki_vodic_be.repositories.user.UserRepository;
import rs.raf.turisticki_vodic_be.services.activity.ActivityService;
import rs.raf.turisticki_vodic_be.services.article.ArticleService;
import rs.raf.turisticki_vodic_be.services.articleactivity.ArticleActivityService;
import rs.raf.turisticki_vodic_be.services.comment.CommentService;
import rs.raf.turisticki_vodic_be.services.destination.DestinationService;
import rs.raf.turisticki_vodic_be.services.user.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App(){

        // Property to send validation errors in the response
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Register the binder
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                bindAsContract(UserService.class);

                bind(MySqlDestinationRepository.class).to(DestinationRepository.class).in(Singleton.class);
                bindAsContract(DestinationService.class);

                bind(MySqlArticleRepository.class).to(ArticleRepository.class).in(Singleton.class);
                bindAsContract(ArticleService.class);

                bind(MySqlActivityRepository.class).to(ActivityRepository.class).in(Singleton.class);
                bindAsContract(ActivityService.class);

                bind(MySqlArticleAndActivityRepository.class).to(ArticleAndActivityRepository.class).in(Singleton.class);
                bindAsContract(ArticleActivityService.class);

                bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                bindAsContract(CommentService.class);
            }
        });
        register(AuthFilter.class);
        register(Cors.class);

        // Register packages containing resources
        packages("rs.raf.turisticki_vodic_be.resources"); // Adjust to the actual package containing your resources
    }
}

