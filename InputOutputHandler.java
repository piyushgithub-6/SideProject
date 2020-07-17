
public class InputOutputHandler {
	 public String question_type="name";
	 public String botInput;
	 public String botOutput;
	 public long botId;
	 public String lastQuestion;
	public String getLastQuestion() {
		return lastQuestion;
	}
	public void setLastQuestion(String lastQuestion) {
		this.lastQuestion = lastQuestion;
	}
	public String getBotInput() {
		return botInput;
	}
	public void setBotInput(String botInput) {
		this.botInput = botInput.toLowerCase();
	}
	public String getBotOutput() {
		return botOutput;
	}
	public void setBotOutput(String botOutput) {
		this.botOutput = botOutput;
	}
	public long getBotId() {
		return botId;
	}
	public void setBotId(long botId) {
		this.botId = botId;
	}
	
	
	
	public void execute() {
		if(botInput.equals("/start")) {
			this.setBotOutput("Side Projects is a community of young developers and professionals looking for programming projects to do while the country is under lockdown. \n" + 
					"\n" + 
					"If you are interested, press /fillup to send us about yourself");
		}
		else if(botInput.equals("/fillup") ){
			this.setBotOutput("What is your name?");
		}
		else if(lastQuestion.equals("What is your name?")) {
			DBConnection.start(botId,botInput,"name");
			this.setBotOutput("Which college are you from?");
		}
		else if(lastQuestion.equals("Which college are you from?")) {
			DBConnection.start(botId,botInput,"college");
			this.setBotOutput("How did you get to know about SideProjects?");
		}
		else if(lastQuestion.equals("How did you get to know about SideProjects?")) {
			DBConnection.start(botId,botInput,"side_project_reference");
			this.setBotOutput("Which programming languages do you know?");
		}
		else if(lastQuestion.equals("Which programming languages do you know?")) {
			DBConnection.start(botId,botInput,"language");
			this.setBotOutput("Do you know any frameworks? Please list them");
		}
		else if(lastQuestion.equals("Do you know any frameworks? Please list them")) {
			DBConnection.start(botId,botInput,"framework");
			this.setBotOutput("Have you previously done any projects? (Yes or No)");
		}
		else if(lastQuestion.equals("Have you previously done any projects? (Yes or No)")) {
			DBConnection.start(botId,botInput,"previous_project");
			this.setBotOutput("How confident are you about your programming skills?");
		}
		else if(lastQuestion.equals("How confident are you about your programming skills?")) {
			DBConnection.start(botId,botInput,"confidence");
			this.setBotOutput("Please share your github repository for us to keep a track of your work");
		}
		else if(lastQuestion.equals("Please share your github repository for us to keep a track of your work")) {
			DBConnection.start(botId,botInput,"github_link");
			this.setBotOutput("Based on your skills and experience, we feel you should join the SideProjects \n"
					+ "levelling process at: - Level2. Please further communicate with\n" + 
					"SideProjects admin. Happy Coding!");
		}
		else {
			if(botInput.equals("hello") || botInput.indexOf("hello")==1) {
				this.setBotOutput("Hello Sir");				
			}
			else {
				this.setBotOutput("You will select the wrong operations please /fillup again.");
			}
		}
		
		
	}

}
