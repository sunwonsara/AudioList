
package jungdain.kr.hs.emirim.audiolist;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button butPlay, butStop, butPause;
    TextView textMusic;
    ProgressBar progress;
    String[] musics={"knock","signal"};
    int[] musicResIds = {R.raw.knock, R.raw.signal};
    int selectedMusicId;
    MediaPlayer mediaplay;
    boolean selectedPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list_music);
        butPlay = (Button)findViewById(R.id.but_play);
        butStop = (Button)findViewById(R.id.but_stop);
        butPause = (Button)findViewById(R.id.but_Pause);
        textMusic = (TextView)findViewById(R.id.text_music);
        progress = (ProgressBar)findViewById(R.id.progress_music);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, musics);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setItemChecked(0, true);
        selectedMusicId = musicResIds[0];
        mediaplay = MediaPlayer.create(this,selectedMusicId);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaplay.stop();
                selectedMusicId = musicResIds[position];
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPauseButton==true){
                    mediaplay.start();
                    selectedPauseButton=false;
                }
                else {
                    mediaplay = MediaPlayer.create(MainActivity.this, selectedMusicId);
                    mediaplay.start();
                }
                progress.setVisibility(View.VISIBLE);
            }
        });

        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplay.stop();
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplay.pause();
                progress.setVisibility(View.INVISIBLE);
                selectedPauseButton=true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaplay.stop();
    }
}
