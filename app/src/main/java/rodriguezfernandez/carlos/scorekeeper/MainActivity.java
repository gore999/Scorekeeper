package rodriguezfernandez.carlos.scorekeeper;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mScore1;
    private int mScore2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    static final String STATE_SCORE_1="Team 1 Score";
    static final String STATE_SCORE_2="Team 2 Score";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreText1=findViewById(R.id.score1);
        mScoreText2=findViewById(R.id.score2);
        if(savedInstanceState!=null){
            //Recuperar los datos.
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            //Poner el texto en los views.
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    public void decreaseScore(View view) {
        //recuperar la id del view:
        int viewID=view.getId();
        switch (viewID){
            case R.id.decreaseTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//crea un menu
        getMenuInflater().inflate(R.menu.main_menu, menu);//infla el xml para convertirlo en java.
        // Change the label of the menu based on the state of the app.
        //Capturamos el valor del modo, segun sea noche o dia tomará un valor entero diferente.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){//Si es noche
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);//Cambiar el titulo del menu por el de día.
        } else{//Si no es noche
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);//Cambiar el titulo a noche.
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Get the night mode state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        //Set the theme mode for the restarted activity
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
// Recreate the activity for the theme change to take effect.
        recreate();
        return true;
    }
    public void increaseScore(View view) {
        int idView=view.getId();
        switch (idView){
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SCORE_1,mScore1);
        outState.putInt(STATE_SCORE_2,mScore2);
        super.onSaveInstanceState(outState);
    }
}
