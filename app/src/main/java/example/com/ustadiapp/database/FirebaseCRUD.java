package example.com.ustadiapp.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import example.com.ustadiapp.model.Schedule;

/**
 * Created by naeem on 12/7/17.
 */

public  class FirebaseCRUD {
    private FirebaseDatabase databaseInstance;
    public FirebaseCRUD(FirebaseDatabase databaseInstance){
        this.databaseInstance=databaseInstance;
    }
    public void postUserSchedule(String userId, Schedule schedule){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/").child(userId);
       databaseReference.setValue(schedule);
    }
    public DatabaseReference getUserRefrence(String userId){
        return databaseInstance.getReference("schedule/").child(userId);
    }
    public DatabaseReference getRootRefrence(){
        return databaseInstance.getReference().child("schedule");
    }
    public DatabaseReference getSampleRefrence(){
        return databaseInstance.getReference("schedule/").child("sample");
    }
    public void postSchedule(Schedule schedule){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/").child("sample");
        databaseReference.setValue(schedule);
    }

}