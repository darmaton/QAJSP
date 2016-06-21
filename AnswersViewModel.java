package mvc.models;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.Answer;
import mvc.QuestionGateway;

//model for the answers
public class AnswersViewModel {
	public String qid;
	public String question;
	public ArrayList<Answer> answers;
	public  AnswersViewModel(String theQid,String theQuestion) throws SQLException{
		this.qid=theQid;
		this.question=theQuestion;
		QuestionGateway.makeGate();
		this.answers=QuestionGateway.getAnswers(qid);
	}
	public void add(String theAnswer,String qid) throws SQLException {
		QuestionGateway.addAnswer(theAnswer,qid);
	}
}