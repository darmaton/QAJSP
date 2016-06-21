package mvc.models;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.QuestionGateway;
import mvc.Question;

//model for the questions
public class QuestionsViewModel {
	public ArrayList<Question> questions;
 	public QuestionsViewModel() throws SQLException { //get questions from database on creation
 		QuestionGateway.makeGate();
		questions=QuestionGateway.getQuestions();
 	}
	public void add(String theQuestion) throws SQLException {
		QuestionGateway.addQuestion(theQuestion);
	}
}
