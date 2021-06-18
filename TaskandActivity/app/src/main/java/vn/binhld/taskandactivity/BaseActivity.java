package vn.binhld.taskandactivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityManager activityManager;
    final String packageName = "vn.binhld.taskandactivity.";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    String getAppTaskState() {
        StringBuilder stringBuilder = new StringBuilder();
        int totalNumberOfTasks = activityManager.getRunningTasks(10).size();//Returns total number of tasks - stacks
        stringBuilder.append("\nTotal Number of Tasks: " + totalNumberOfTasks + "\n\n");

        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(10);//returns List of RunningTaskInfo - corresponding to tasks - stacks

        for (ActivityManager.RunningTaskInfo info : taskInfo) {
            stringBuilder.append("Task Id: " + info.id + ", Number of Activities : " + info.numActivities + "\n");//Number of Activities in task - stack

            // Display the top Activity of task-stack
            stringBuilder.append("TopActivity: " + info.topActivity.getClassName().toString().replace(packageName, "") + "\n");
            // Display the root Activity of task-stack
            stringBuilder.append("BaseActivity: " + info.baseActivity.getClassName().toString().replace(packageName, "") + "\n");
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
