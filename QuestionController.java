package mvc.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.models.QuestionsViewModel;

@WebServlet("/Questions")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QuestionController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
	        
	    //make model that connects to the database/gets data
	    QuestionsViewModel result;
		try {
			result = new QuestionsViewModel();
			rd = request.getRequestDispatcher("/view/questionview.jsp");
			request.setAttribute("viewModel", result);
			rd.forward(request, response);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String theQuestion = request.getParameter("theQuestion");
	    
	    //make model that adds the new question, then get the data
	    QuestionsViewModel adder;
	    try{
	    	adder = new QuestionsViewModel();
	    	adder.add(theQuestion);
	    }
	    catch(SQLException e){
	    	e.printStackTrace();
	    }
	    this.doGet(request, response);
	}
}