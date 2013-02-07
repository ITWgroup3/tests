package Schoolware.Json;
//Not yet used this class
import org.json.JSONException;
import org.json.JSONObject;

public class JsonChecker{
	private JSONObject json;

	private static final String QuestionId= new String("QuestionId");
	private static final String Time= new String("Time");
	private static final String TotalTime= new String("TotalTime");
	private static final String Analytics= new String("Analytics");
	private static final String FinalAnswer= new String("FinalAnswer");
	private static final String CorrectAnswer= new String("CorrectAnswer");
	private static final String SelectedAnswers= new String("SelectedAnswers");
	private static final String Clicks= new String("Clicks");
	private static final String AppId= new String("AppId");
	private static final String TestId= new String("TestId");
	private static final String OverallScore= new String("OverallScore");
	private static final String QuesAttended= new String("QuesAttended");
	private static final String TotalQuestions= new String("TotalQuestions");
	private static final String Difficulty= new String("Difficulty");
	private static final String Mark= new String("Mark");
	private static final String QuestionType= new String("QuestionType");
	private static final String TestStartTime= new String("TestStartTime");

	/**
	 * @param args
	 */
	public JsonChecker(){
		
	}
	/**
	 * Takes the builded JSON object as input
	 * @param buildedjson
	 * @throws JSONException
	 */
	public JsonChecker(JSONObject buildedjson) throws JSONException{
		json=buildedjson;
	}
	/**
	 * Check or scans the builded JSON object and reports missing fields.
	 * Eg.  
			TotalTime is missing.
			OverallScore is missing.
			CorrectAnswer is missing for Question NO.1
			Mark is missing for Question NO.1
			QuestionType is missing for Question NO.2
			FinalAnswer is missing for Question NO.4
			Clicks is missing for Question NO.4
			SelectedAnswers is missing for Question NO.4
	 * @return
	 * @throws JSONException
	 */
	public String JsonChecking() throws JSONException{
		String message="";
		
		if(json.isNull(AppId)){
			message = message+"AppId is missing.\n";
		}
		if(json.isNull(TestId)){
			message = message+"TestId is missing.\n";
		}
		if(json.isNull(TestStartTime)){
			message = message+"TestStartTime is missing.\n";
		}
		if(json.isNull(Difficulty)){
			message = message+"Difficulty is missing.\n";
		}
		if(json.isNull(TotalQuestions)){
			message = message+"TotalQuestions is missing.\n";
		}
		if(json.isNull(TotalTime)){
			message = message+"TotalTime is missing.\n";
		}
		if(json.isNull(OverallScore)){
			message = message+"OverallScore is missing.\n";
		}
		if(json.isNull(QuesAttended)){
			message = message+"QuesAttended is missing.\n";
		}
		if(json.isNull(Analytics)){
			message = message+"Analytics is missing.\n";
		}
		else{
			if(json.getJSONArray(Analytics).length()==0){
				message = message+"Analytics is Empty.\n";
			}
			else{
				for(int i=0;i<json.getJSONArray(Analytics).length();i++){
					int tempQuestionid = json.getJSONArray(Analytics).getJSONObject(i).getInt(QuestionId);
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(CorrectAnswer)){
					message = message+"CorrectAnswer is missing for Question NO."+tempQuestionid+"\n";
				}
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(FinalAnswer)){
					message = message+"FinalAnswer is missing for Question NO."+tempQuestionid+"\n";
				}
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(Time)){
					message = message+"Time is missing for Question NO."+tempQuestionid+"\n";
				}
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(QuestionType)){
					message = message+"QuestionType is missing for Question NO."+tempQuestionid+"\n";
				}
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(Clicks)){
					message = message+"Clicks is missing for Question NO."+tempQuestionid+"\n";
				}
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(Mark)){
					message = message+"Mark is missing for Question NO."+tempQuestionid+"\n";
				}
				if(json.getJSONArray(Analytics).getJSONObject(i).isNull(SelectedAnswers)){
					message = message+"SelectedAnswers is missing for Question NO."+tempQuestionid+"\n";
				}
			}
		}
	}
	return message;
	}	
}
