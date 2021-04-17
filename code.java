public static Array freetime(int[] Arr1, int[] arr2) {
	int[] finalArr = new int[1440];
	int start = 0;
	int finish = 0;
	int startHour;
	int startMinute;
	int finishHour;
	int finishMinute;
	for(int i = 0; i<= 1440; i++) {
		while(arr1[i] == 0 && arr2[i]== 0) {
			finalArr[i] = 1;
		}
	}
	for(int i = 0; i<= 1440; i++) {
		if(finalArr[i] == 1) {
			start = i;
			while(finalArr[i] ==1) {
				finish = i;
				i++;
			}
		}
		if(start>60) {
			startHour = start/60;
			startMinute = start % 60;
			if(finish > 60) {
				finishHour = finish/60;
				finishMinute = finish % 60;
			}
			System.out.println("free from : " + startHour + ":" + startMinute " to "
					+ finishHour + ":"+ finishMinute + "\n");
		}else if (start < 60){
			startHour = 12;
			startMinute = start % 60;
			if(finish > 60) {
				finishHour = finish/60;
				finishMinute = finish % 60;
			}else if(finish < 60) {
				finishHour = 12;
				finishMinute = finish % 60;
			}
			System.out.println("free from : " + startHour + ":" + startMinute " to "
					+ finishHour + ":"+ finishMinute + "\n");
		}
		
	}
	
}