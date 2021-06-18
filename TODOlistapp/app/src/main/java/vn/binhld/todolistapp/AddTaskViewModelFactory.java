package vn.binhld.todolistapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import vn.binhld.todolistapp.database.AppDatabase;

public class AddTaskViewModelFactory implements ViewModelProvider.Factory {

    private AppDatabase mDb;
    private int taskId;

    public AddTaskViewModelFactory(AppDatabase mDb, int mTaskId) {
        this.mDb = mDb;
        this.taskId = taskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(mDb, taskId);
    }
}
