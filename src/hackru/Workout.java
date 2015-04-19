package hackru;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Workout {
	//Test initialization
	int[] plan;
	int setNum = 0;
	
	int currentReps = 0;
	
	int botLim;
	int topLim;
	
	public Workout(int day, int week, int max){
		try{
			String path = "./src/hackru/" + week + "/" + day + "/lim.dat";
			File lims = new File(path);
			
			Scanner input = new Scanner(lims);
			
			this.setBotLim(input.nextInt());
			this.setTopLim(input.nextInt());
			
			System.out.println("BotLim: " + this.getBotLim() + "\nTopLim: " + this.getTopLim() + "\n");
			
			if(max <= this.getBotLim())
				path = "./src/hackru/" + week + "/" + day + "/1.dat";
			else if(max > this.getBotLim() && max < this.getTopLim())
				path = "./src/hackru/" + week + "/" + day + "/2.dat";
			else
				path = "./src/hackru/" + week + "/" + day + "/3.dat";
			
			input.close();
			
			File program = new File(path);
			Scanner setGrab = new Scanner(program);
			
			String happy = setGrab.nextLine();
			
			System.out.println(happy + " HAPPY GOES HERE\n");
			
			this.setPlan(happy);
			
			setGrab.close();
			
		}catch(IOException e){System.out.println("SHits not working!");}
	}
	
	public void setBotLim(int lim){
		this.botLim = lim;
	}
	
	public void setTopLim(int lim){
		this.topLim = lim;
	}
	
	public void setPlan(String repSets){
		String[] items = repSets.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		int[] results = new int[items.length];

		for (int i = 0; i < items.length; i++) {
		    try {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe){};
		}
		    
		this.plan = results;
	}
	
	public int getBotLim(){
		return(this.botLim);
	}
	
	public int getTopLim(){
		return(this.topLim);
	}
	
	public int getSets(){
		return(this.plan.length);
	}
	
	public int getCurrentSet(){
		return(this.setNum);
	}
	
	public int getReps(){
		int reps = (this.plan[setNum]);		
		return(reps);
	}
	
	public void incRep(){
		this.currentReps++;
		if(this.currentReps == this.getReps())
			this.completeSet();
	}
	
	public void completeSet(){
		this.setNum++;
	}
}
