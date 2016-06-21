package mvc;
//the answer object
public class Answer {
	public String answer;
	public int id;
	public Answer() {}
	public void setID(String idString) {
		this.id=Integer.parseInt(idString);
	}
	public void setQuestion(String answer) {
		this.answer=answer;
	}
}
