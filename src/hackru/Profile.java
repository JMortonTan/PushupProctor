package hackru;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Profile {
	private int day;
	private int week;
	private int max;
	
	Workout currentWorkout;
	
	public Profile(){
		try{
			File data = new File("./profile.dat");
			Scanner input = new Scanner(data);
		
			if(data.exists()){
				this.setDay(input.nextInt());
				this.setWeek(input.nextInt());
				this.setMax(input.nextInt());
				
				input.close();
				this.setWorkout();
			}
			
			this.saveProfile();
		}catch(IOException e){
			this.setDay(1);
			this.setWeek(1);
			this.setMax(20);		
			
			this.setWorkout();
			
			this.saveProfile();
		}
	}
	
	public void setDay(int dayNum){
		this.day = dayNum;
	}
	
	public void setWeek(int weekNum){
		this.week = weekNum;
	}
	
	public void setMax(int maxResult){
		this.max = maxResult;
	}
	
	public void setWorkout(){
		System.out.println("Day: " + this.day);
		System.out.println("Week: " + this.week);
		System.out.println("Max: " + this.max);
		this.currentWorkout = new Workout(this.day, this.week, this.max);
	}
	
	public void incDay(){
		//Check if Sunday: reset if true
		if(this.day == 3){
			this.day = 1;
			this.incWeek();
		}
		else
			this.day++;
	}
	
	public void incWeek(){
		//Check if week 6,
		if(this.week == 6 && this.max < 40)
			//MESSAGE TO DISPLAY REPEAT WEEK 6
			this.week = 6;
			
		else if(this.week == 7)
			//CONGRATS!  Keep it up!
			this.week = 6;
		
		else
			this.week++;
	}
	
	public int getDay(){
		return(this.day);
	}
	
	public int getWeek(){
		return(this.week);
	}
	
	public int getMax(){
		return(this.max);
	}
	
	public Workout getWorkout(){
		return(this.currentWorkout);
	}
	
	public void saveProfile(){
		File data = new File("profile.dat");
		try{
			FileWriter fwriter = new FileWriter(data, false);
			fwriter.write(this.getDay() + " ");
			fwriter.write(this.getWeek() + " ");
			fwriter.write(this.getMax() + " ");
			fwriter.close();
		}catch(IOException e){}
	}
	
	public String toString(){
		return("Day: " + this.day + " Week: " + this.week);
	}

}
