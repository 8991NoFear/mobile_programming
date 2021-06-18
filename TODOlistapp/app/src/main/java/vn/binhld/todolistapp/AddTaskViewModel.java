package vn.binhld.todolistapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import vn.binhld.todolistapp.database.AppDatabase;
import vn.binhld.todolistapp.database.TaskEntry;

public class AddTaskViewModel extends ViewModel {

    private static final String TAG = AddTaskViewModel.class.getSimpleName();

    public LiveData<TaskEntry> getTask() {
        return task;
    }

    private LiveData<TaskEntry> task;

    public AddTaskViewModel(AppDatabase mDb, int taskId) {
        Log.d(TAG, "Actively retrieving data from database");
        task = mDb.taskDao().loadTaskById(taskId);
    }
}
