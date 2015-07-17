package Open;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchProcess", urlPatterns = {"/SearchProcess"})
public class SearchProcess extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            
            String message=(new Processing()).apiProcessing(request.getParameter("zipcode"),request.getParameter("Selection"));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Coupon Api</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h4> " + message + "</h4>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
