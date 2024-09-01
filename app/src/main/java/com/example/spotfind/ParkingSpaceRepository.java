package com.example.spotfind;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParkingSpaceRepository {
    private DatabaseReference mDatabase;

    public ParkingSpaceRepository() {
        // Initialize Firebase Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference("ParkingSpaces");
    }

    public void saveParkingSpace(ParkingSpaceDatabase parkingSpace, final RepositoryCallback callback) {
        mDatabase.push().setValue(parkingSpace).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess("Parking space submitted successfully");
                } else {
                    callback.onFailure("Failed to submit parking space");
                }
            }
        });
    }

    // Callback interface to handle success or failure in the database operations
    public interface RepositoryCallback {
        void onSuccess(String message);
        void onFailure(String message);
    }
}
