package cuny.edu.com.resumebuilder;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class LocalWebServerViewActivity  {

    public void init() throws Exception  {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    Server server = new Server(8080);
                    WebAppContext webapp = new WebAppContext();
                    webapp.setContextPath("/");
                    webapp.setTempDirectory(new File("/data/data/cuny.edu.com.resumebuilder/my-work/"));
                    webapp.setWar("/data/data/cuny.edu.com.resumebuilder/my-webapps/sample.war");

                    server.setHandler(webapp);
/*

                    ContextHandler context = new ContextHandler();
                    context.setContextPath("/hello");
                    context.setResourceBase(".");
                    context.setClassLoader(Thread.currentThread().getContextClassLoader());
                    server.setHandler(context);
                    context.setHandler(new AbstractHandler() {
                                           @Override
                                           public void handle(String s, Request request,
                                                              javax.servlet.http.HttpServletRequest httpServletRequest,
                                                              javax.servlet.http.HttpServletResponse httpServletResponse)
                                                   throws IOException, ServletException {
                                               httpServletResponse.setContentType("text/html;charset=utf-8");
                                               httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                                               request.setHandled(true);
                                               httpServletResponse.getWriter().println("<h1>Hello World</h1>");
                                           }
                                       });
*/

                            server.start();
                    server.join();
                } catch(Exception e) {
                    e.printStackTrace();
                    if (e.getCause() != null) {
                        e.getCause().printStackTrace();
                    }
                }
            }
        });

        thread1.start();
    }
}

