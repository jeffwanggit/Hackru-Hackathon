import java.util.Scanner;

public class inputTimeMthd {
	
	final static int MINUTES_IN_HOUR = 60;
	
	//returns the first index in array when the first minute of the event occurs
	static public int minuteStart(int hour, int minute, String tODay) {
		int time = 0;
		
		//if time is in pm
		if(tODay.equalsIgnoreCase("pm")) {
			//if it is noon, do not change anything in formula, else, add 12 to the hour
			if(hour == 12)
				return hour * MINUTES_IN_HOUR + minute;
			else
				return (12+hour) * MINUTES_IN_HOUR + minute;
		}
		//else if it is am and hour is 12, then the index should start at 0 plus the total amount of minutes
		else if(hour == 12)
			time = 0 + minute;
		//else if it is am, calculate total number of minutes
		else
			time = hour * MINUTES_IN_HOUR + minute;
		
		return time;
	}
	
	//returns the last index in array when the first minute of the event occurs
	static public int minuteEnd(int hour, int minute, String tODay) {
		int time = 0;
		
		//if time is in pm
		if(tODay.equalsIgnoreCase("pm")) {
			//if it is noon, do not change anything in formula, else, add 12 to the hour
			if(hour == 12)
				return hour * MINUTES_IN_HOUR + minute;
			else
				return (12+hour) * MINUTES_IN_HOUR + minute;
		}
		//else if it is am and hour is 20, then the index should start at 0 plus the total amount of minutes
		else if(hour == 12)
			time = 0 + minute;
		//else if it is am, calculate total number of minutes
		else
			time = hour * MINUTES_IN_HOUR + minute;
		
		return time;
	}
	
	static public int[] schedule(int events) {
		//initialize and or declare variables
		Scanner key = new Scanner(System.in);
		int[] schedule = new int[1440];
		String Stime, Etime;
		String delims = "[ :]";
		String[] Sinfo, Einfo;
		
		for(int i =1; i <= events; i++) {
			//loops until there is no invalid case, where there is no space between the time and the time reference
			do {
				//ask user input
				System.out.print("Enter start time of Event " + i + " (##:## am/pm): ");
				Stime = key.nextLine();
				System.out.print("Enter end time of Event " + i + " (##:## am/pm): ");
				Etime = key.nextLine();
				System.out.println();
				
				//splits the hours, minutes, and time of day of the time into elements of an array
				Sinfo = Stime.split(delims);
				Einfo = Etime.split(delims); 
				
				//output error message if there is no space between the time and the time reference
				if((Sinfo.length < 3) || (Einfo.length < 3)) {
					System.out.println("Invalid Input... Re-enter times with correct formatting...");
					System.out.println("*Notice the space between the actual time and time indicator*\n");
				}
				
			}while( (Sinfo.length < 3) || (Einfo.length < 3) );
			
			
			//gets the start and end positions in terms of the array indices
			int minuteStart = minuteStart(Integer.parseInt(Sinfo[0]), Integer.parseInt(Sinfo[1]), Sinfo[2]);
			int minuteEnd = minuteEnd(Integer.parseInt(Einfo[0]), Integer.parseInt(Einfo[1]) , Einfo[2]);
			
			//assign busy time in schedule with 1's
			for(int j= minuteStart ; j < minuteEnd; j++)
				schedule[j] = 1;
		}

		return schedule;
	}
	
	public static void freetime(int[] arr1, int[] arr2) {
		int[] finalArr = new int[1440];
		int start = 0;
		int finish = 0;
		int startHour;
		int startMinute;
		int finishHour = 0;
		int finishMinute = 0;
		//initialize array containing free times of both persons
		for(int i = 0; i< 1440; i++) {
			if(arr1[i] == 0 && arr2[i]== 0) {
				finalArr[i] = 1;
				
			}
		}
		//start searching through array to find free time blocks
		for(int i = 0; i<1440; i++) {
			if(finalArr[i] == 1) {
				start = i; // initialize start time of free time
				if(i == 1440) {
					continue;
				}else {
					while(finalArr[i] ==1) {
						if(i == 1439) {
							break;
						}else {
							finish = i; // endtime of free time
							i++;
						}
						
					}
				}
				
				if(start>60) { // if after 1am
					startHour = start/60; // calculate hour of free time start
					startMinute = start % 60; // calculate minutes of free time slot
					if(finish > 60) { 
						if(finish == 1438) {
							finish++;
						}
						finishHour = finish/60;
						finishMinute = finish % 60;
						if(finishHour >12) { // if after 12 meaning must reset to time - 12 also ends in pm
							finishHour = finishHour - 12;
							if(startHour >12) {// if after 12 meaning must reset to time - 12, also starts in pm
								startHour = startHour-12;
								if(startMinute == 0) { // if minute is 0, print twice
									System.out.println("Freetime from : " + startHour + ":" + startMinute + startMinute +" pm to "
											+ finishHour + ":"+ finishMinute + " pm\n");
								}else { // print time regularly
									System.out.println("Freetime from : " + startHour + ":" + startMinute + " pm to "
											+ finishHour + ":"+ finishMinute + " pm\n");
								}
								
							}else {
								if(startMinute ==0) { // print 0 twice
									System.out.println("Freetime from : " + startHour + ":" + startMinute + startMinute +" am to "
											+ finishHour + ":"+ finishMinute + " pm\n");
								}else { // print regularly
									System.out.println("Freetime from : " + startHour + ":" + startMinute + " am to "
											+ finishHour + ":"+ finishMinute + " pm\n");
								}
								
							}
							
						}
					}
					
					//print 
					
				}else if (start < 60){ // is time is 12 something am, start time is am
					startHour = 12;
					startMinute = start % 60;
					
					if(finish > 60) { 
						finishHour = finish/60;
						finishMinute = finish % 60;
						if(finishHour >12) { // end time is pm
							finishHour = finishHour - 12;
							if(startMinute == 0) { // print double 0s for minute
								System.out.println("Freetime from : " + startHour + ":" + startMinute + startMinute +" am to "
										+ finishHour + ":"+ finishMinute + " pm\n");
							}else { // print regularly
								System.out.println("Freetime from : " + startHour + ":" + startMinute + " am to "
										+ finishHour + ":"+ finishMinute + " pm\n");
							}
							
						}else {
							if(startMinute == 0) { // print double 0s for minutes
								System.out.println("Freetime from : " + startHour + ":" + startMinute + startMinute + " am to "
										+ finishHour + ":"+ finishMinute + " am\n");
							}else { // print regularly
								System.out.println("Freetime from : " + startHour + ":" + startMinute + " am to "
										+ finishHour + ":"+ finishMinute + " am\n");
							}
							
						}
					}else if(finish < 60) { // finishes time is am
						finishHour = 12;
						finishMinute = finish % 60;
						System.out.println("free from : " + startHour + ":" + startMinute + "am to "
								+ finishHour + ":"+ finishMinute + "am\n");
					}
					
				}
			}
			
		}
	}

	
	public static void main(String[] args) {
		int[] person1;
		int[] person2;
		int[] free;
		int events = 0;;
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Person 1");
		System.out.println("========");
		System.out.print("How many events do you have today?" );
		events = keyboard.nextInt();
		person1 = schedule(events);
		
		System.out.println("Person 2");
		System.out.println("========");
		System.out.print("How many events do you have today? ");
		events = keyboard.nextInt();
		person2 = schedule(events);
		
		freetime(person1, person2);
		
		
		
	}
}
