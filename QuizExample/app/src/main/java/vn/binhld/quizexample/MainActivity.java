package vn.binhld.quizexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.udacity.example.droidtermsprovider.DroidTermsExampleContract;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mWord;
    private TextView mDefinition;

    // The data from the DroidTermsExample content provider
    private Cursor mData;

    // The current state of the app
    private int mCurrentState;

    // This state is when the word definition is hidden and clicking the button will therefore
    // show the definition
    private final int STATE_HIDDEN = 0;

    // This state is when the word definition is shown and clicking the button will therefore
    // advance the app to the next word
    private final int STATE_SHOWN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the views
        mButton = (Button) findViewById(R.id.button_next);
        mWord = (TextView) findViewById(R.id.text_view_word);
        mDefinition = (TextView) findViewById(R.id.text_view_definition);

        //Run the database operation to get the cursor off of the main thread
        WordFetchTask mTask = new WordFetchTask();
        mTask.execute();
    }

    public void onButtonClick(View view) {
        // Either show the definition of the current word, or if the definition is currently
        // showing, move to the next word.
        switch (mCurrentState) {
            case STATE_HIDDEN:
                showDefinition();
                break;
            case STATE_SHOWN:
                nextWord();
                break;
        }
    }

    public void nextWord() {
        // Change button text
        mButton.setText(getString(R.string.show_definition));

        // Display word
        if (mData != null) {
            // Move to the next position in the cursor, if there isn't one, move to the first
            if (!mData.moveToNext()) {
                mData.moveToFirst();
            }
            String word = mData.getString(mData.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD));
            mWord.setText(word);
            mDefinition.setText(null);
        }

        // Change state
        mCurrentState = STATE_HIDDEN;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remember to close your cursor!
        mData.close();
    }

    public void showDefinition() {
        try {
            // Change button text
            mButton.setText(getString(R.string.next_word));

            // Display definition
            if (mData != null) {
                mDefinition.setText(mData.getString(mData.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION)));
            }

            // Change state
            mCurrentState = STATE_SHOWN;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Use an async task to do the data fetch off of the main thread.
    public class WordFetchTask extends AsyncTask<Void, Void, Cursor> {

        // Invoked on non-UI thread
        @Override
        protected Cursor doInBackground(Void... voids) {
            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(DroidTermsExampleContract.CONTENT_URI, null, null, null);
            return cursor;
        }

        // Was Invoked on UI thread
        @Override
        protected void onPostExecute(Cursor cursor) {
            // set data for main activity
            mData = cursor;

            // Set the initial state
            nextWord();
            super.onPostExecute(cursor);
        }

    }
}