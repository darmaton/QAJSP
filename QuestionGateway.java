package mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

public class QuestionGateway {
	public static ArrayList<Question> questions;
	public static ArrayList<Answer> answers;
	private static final long serialVersionUID = 1L;
	private static Connection con;
	private static Statement stmt;  
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public static void makeGate() throws SQLException {
    	String connectionString = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/ertgd8";
        String userID = "ertgd8";
        String password = "NRASn1pgbLn";
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.out.println(e); 
            System.exit(0);
        }
        
        try {
			con = DriverManager.getConnection(connectionString,userID,password);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        questions=new ArrayList<Question>();
        answers=new ArrayList<Answer>();
        createQuestionTable();
        createAnswerTable();
    }
    public static void cleanup() throws SQLException {
        // Close connection and statement
        // Connections, statements, and result sets are
        // closed automatically when garbage collected
        // but it is a good idea to free them as soon
        // as possible.
        // Closing a statement closes its current result set.
        // Operations that cause a new result set to be
        // created for a statement automatically close
        // the old result set.
        stmt.close();
        con.close();
    }
    //creates question table
    public static void createQuestionTable() throws SQLException {
    	String trunc="TRUNCATE TABLE Questions"; //clear old table - start fresh
    	String creQTable="CREATE TABLE IF NOT EXISTS Questions "+
    			"(ID INT PRIMARY KEY AUTO_INCREMENT, "+
    			"Question VARCHAR(255))";
    	stmt.executeUpdate(creQTable);
    	//stmt.executeUpdate(trunc);
    }
    //creates answer table
    public static void createAnswerTable() throws SQLException{
    	String trunc="TRUNCATE TABLE Answers"; //clear old table - start fresh
    	String creATable="CREATE TABLE IF NOT EXISTS Answers "+
    				"(QID INT, "+
    				"Answer VARCHAR(255))";
    	stmt.executeUpdate(creATable);
    	//stmt.executeUpdate(trunc);
    }
    //adds new question
    public static void addQuestion(String newQuestion) throws SQLException{
    	String addQ="INSERT INTO Questions(Question) VALUES('"+newQuestion+"')";
    	stmt.executeUpdate(addQ);
    }
    //adds new answer with specified question
    public static void addAnswer(String newAnswer,String qid) throws SQLException{
    	String addA="INSERT INTO Answers(QID, Answer) VALUES('"+Integer.parseInt(qid)+"','"+newAnswer+"')";
    	stmt.executeUpdate(addA);
    }
    //makes an array of questions from table
    public static ArrayList<Question> getQuestions() throws SQLException{
    	String getQ="SELECT * FROM Questions";
    	ResultSet rs=stmt.executeQuery(getQ);
    	ResultSetMetaData rsmd1=rs.getMetaData();
    	int numCols=rsmd1.getColumnCount();
    	
    	//for each row, make question to pass to webpage
    	boolean more=rs.next();
    	while(more){
    		//create new question object
    		Question question=new Question(); 
    		ArrayList<String> val_list=new ArrayList<String>(); //list of data vals
    		for(int i=1;i<=numCols;++i){
    			//get data for question
    			val_list.add(rs.getString(i));
    		}
    		question.setID(val_list.get(0)); //val_list 0 is the id
    		question.setQuestion(val_list.get(1)); //val_list 1 is the question string
    		questions.add(question);
    		more=rs.next();
    	}
    	return questions;
    }
    //makes an array of answers from table specific to a question
    public static ArrayList<Answer> getAnswers(String qid) throws SQLException{
    	String getA="SELECT * FROM ANSWERS WHERE QID='"+Integer.parseInt(qid)+"'";
    	ResultSet rs=stmt.executeQuery(getA);
    	ResultSetMetaData rsmd1=rs.getMetaData();
    	int numCols=rsmd1.getColumnCount();
    	
    	//for each row, make answer to pass to webpage
    	boolean more=rs.next();
    	while(more){
    		//create new answer object
    		Answer answer=new Answer();
    		ArrayList<String> val_list=new ArrayList<String>(); //list of data vals
    		for(int i=1;i<=numCols;++i){
    			//get data for answer
    			val_list.add(rs.getString(i));
    		}
    		answer.setID(val_list.get(0)); //val_list 0 is the id
    		answer.setQuestion(val_list.get(1)); //val_list 1 is the answer string
    		answers.add(answer);
    		more=rs.next();
    	}
    	return answers;
    }
}

