package example.com.ustadiapp.database;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import example.com.ustadiapp.model.Duty;
import example.com.ustadiapp.model.LogModel;
import example.com.ustadiapp.model.SampleModel;
import example.com.ustadiapp.model.Schedule;

/**
 * Created by naeem on 12/7/17.
 */

public  class FirebaseCRUD {
    private static final FirebaseDatabase databaseInstance = FirebaseDatabase.getInstance();
    public FirebaseCRUD(){
//        databaseInstance.setPersistenceEnabled(true);
    }
    public void postUserSchedule(String userId, SampleModel schedule){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/available_user").child(userId);
       databaseReference.setValue(schedule);
    }
    public DatabaseReference getUserRefrence(String userId){
        return databaseInstance.getReference("schedule/available_user").child(userId);
    }
    public DatabaseReference getRootRefrence(){
        return databaseInstance.getReference().child("schedule");
    }
    public DatabaseReference getSampleRefrence(){
        return databaseInstance.getReference("schedule/").child("sample");
    }
    public void postSampleSchedule(SampleModel schedule){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/").child("sample");
        databaseReference.setValue(schedule);
    }
    public void updateDutyTable(Schedule schedule){

        DatabaseReference databaseReference= databaseInstance.getReference("schedule/").child("dutyTable");
        databaseReference.setValue(schedule);
    }
    public DatabaseReference dutyTableRefrence(){
        return databaseInstance.getReference("schedule/").child("dutyTable");
    }
    public void updateDuty(int position, Duty value){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/dutyTable/list").child(""+position);
        databaseReference.setValue(value);
    }
    public void insertLog(String userid, LogModel log){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/logs/").child(userid);
        databaseReference.setValue(log);

    }

//    httpPost = new HttpPost("http://xxx.xxx.xxx.xx/user/login.json");
//    nameValuePairs = new ArrayList<NameValuePair>(2);
//nameValuePairs.add(new BasicNameValuePair("email".trim(), emailID));
//nameValuePairs.add(new BasicNameValuePair("password".trim(), passWord));
//    httpPost = new HttpPost(url);
//httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//    String response = new DefaultHttpClient().execute(httpPost, new BasicResponseHandler());

}
