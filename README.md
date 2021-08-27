# Covid19

In This Application I am Using covid19india.org API to fetch country and state wise covid data.

{

    1). Covid State(Only contain India state data ) and Countries Data.
    2). Here Data Update Every 1hr per day.
    3). In-State u can fetch State name, active cases, recovered cases, confirmed, death cases Data.
    4). In-Countries u can fetch Flags, names, country cases, Today cases, death cases, today death cases, recovered cases Data.
    5). here I am Using volley library to fetch covid19 data.
    
}

dependencies {

    // CardView
    implementation 'androidx.cardview:cardview:1.0.0'
    // Recyclerview
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    // Volley
    implementation 'com.android.volley:volley:1.1.0'
    // Glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

}

Custom Covid19 Library : 

In this, u don't require to write JSON code u have simply write :
{

	Covid19 covid = new Covid19(this,this);

	@Override

	public void OnStateDataReceived(ArrayList<State> states){

	String State_Name = states.get(0).getSTATE_NAME(); }
}

Setp 1 : Add it in your root build.gradle at the end of repositories :

allprojects
{
	
	repositories
		{
			...
			maven { url 'https://jitpack.io' }
		}
}
  
Step 2: Add the dependency :

	dependencies
 	 	{
	        implementation 'com.github.rahuls1571:Covid19_API:1.0.0'
		}
	
# App Image : 

<p align="center">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131082747-8fd79384-c1d9-4fc7-a21f-be69acccb352.jpg">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131074628-5f052da5-d593-41b8-9df9-22c4584d5660.jpg">
</p>

<p align="center">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131074738-9e1d7dea-a60a-4ac5-9844-07116ea92c51.jpg">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131074979-35a0350f-36fc-466e-89fa-5a5d5b34b677.jpg">
</p>

<p align="center">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131074932-a3e7aeba-a8f1-4165-9939-0a61fa48aa21.jpg">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131075022-aa912583-efac-416a-8aaa-0a8a93d76859.jpg">
</p>

<p align="center">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131076642-ccffb607-a5de-4998-a664-baf1ce22ba23.jpg">
<img align="center" width="300" height="600" src="https://user-images.githubusercontent.com/46783288/131083421-9f446cc0-881a-4a85-9b8f-596b96ee5c37.jpg">
</p>


