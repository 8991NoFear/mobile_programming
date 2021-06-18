package vn.binhld.todolistapp;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import vn.binhld.todolistapp.database.AppDatabase;
import vn.binhld.todolistapp.database.TaskEntry;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }

    public MainViewModel(Application application) {
        super(application);
        AppDatabase mDb = AppDatabase.getInstance(application.getApplicationContext());
        Log.d(TAG, "Actively retrieving tasks from database");
        tasks = mDb.taskDao().loadAllTask();
    }

}
