package mvc.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.models.AnswersViewModel;

@WebServlet("/Answers")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AnswerController() {
        super();
    }
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String questionID = request.getParameter("id");
		String questionString=request.getParameter("question");

		RequestDispatcher rd = null;
	        
	    //make model that connects to the database/gets data
	    AnswersViewModel result;
		try {
			result = new AnswersViewModel(questionID,questionString);
			rd = request.getRequestDispatcher("/view/answerview.jsp");
			request.setAttribute("viewModel", result);
			rd.forward(request, response);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}   
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionID = request.getParameter("id");
		String questionString = request.getParameter("question");
	    String theAnswer = request.getParameter("theAnswer");

	    //add new answer from text field
	    AnswersViewModel adder;
	    try{
	    	adder=new AnswersViewModel(questionID,questionString);
	    	adder.add(theAnswer,questionID);
	    }
	    catch(SQLException e){
	    		e.printStackTrace();
	    }
	    this.doGet(request, response);
	}
}
