    package controller;

    import com.mycompany.reservasdesalas.CommandFactory;
    import com.mycompany.reservasdesalas.InterfaceCommand;
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    @WebServlet(name = "FrontControllerServlet", urlPatterns = {"/frontcontrollerServlet"})
    public class FrontControllerServlet extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try {

                String paramAction = request.getParameter("action"); 


                InterfaceCommand comandoAction = CommandFactory.create(paramAction);

                if (comandoAction == null) {
                    throw new Exception("Ação inválida ou não registrada: " + paramAction);
                }


                String pageAction = comandoAction.execute(request, response);


                if (pageAction.startsWith("redirect:")) {
                    response.sendRedirect(pageAction.substring(9));
                } else {
                    request.getRequestDispatcher(pageAction).forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("msg", "Ops... algo deu errado! Erro crítico na aplicação: " + e.getMessage());
                request.getRequestDispatcher("view/erro.jsp").forward(request, response);
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
        }
    }
