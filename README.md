# TimeSlotPicker
android time slot picker helps you to pick a time slot from a set of predefined slots in a day. Just need to pass the date to this lib.

Add it in your root build.gradle at the end of repositories:
Gradle:
______________


allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
Step 2. Add the dependency

dependencies {
	        implementation 'com.github.emsyneapps:TimeSlotPicker:em01'
	}
  
  Maven:
  _______________
  
  <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
  Step 2. Add the dependency
  
  <dependency>
	    <groupId>com.github.emsyneapps</groupId>
	    <artifactId>TimeSlotPicker</artifactId>
	    <version>em01</version>
	</dependency>
  
  
  
  You can use it in your activity as below.
  
  SlotPicker.pickTime(Activiy context, dateSelected,scheduledtimes,LAUNCH_SECOND_ACTIVITY);
   
  LAUNCH_SECOND_ACTIVITY:  contant for receave activity result  // LAUNCH_SECOND_ACTIVITY =1
  scheduledtimes:  already selected time arrylist<String>       // scheduledtimes.add("16:50");
  dateSelected : user selected date for pick time               // dateSelected = 21/05/2020
  
  
  
      @Override
       protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String date_time = data.getStringExtra("TIME_SELECTED");
                if (date_time != null) {
                    tvDateTimeSelected.setText(date_time);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
  
  
