package mvc;
//the question object
public class Question {
	public String question;
	public int id;
	public Question() {}
	public void setID(String idString) {
		this.id=Integer.parseInt(idString);
	}
	public void setQuestion(String question) {
		this.question=question;
	}
}
