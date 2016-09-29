package techlearn.com.vectordrawableandtinting;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    private SeekBar mAlphaBar, mGreenBar, mRedBar, mBlueBar;

    private Spinner mBlendSpinner;

    private ImageView mImage;

    private int mHintColor;

    private PorterDuff.Mode mMode;


    private static final PorterDuff.Mode[] MODES = new PorterDuff.Mode[]{
            PorterDuff.Mode.ADD,
            PorterDuff.Mode.CLEAR,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.DST,
            PorterDuff.Mode.DST_ATOP,
            PorterDuff.Mode.DST_IN,
            PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.LIGHTEN,
            PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.OVERLAY,
            PorterDuff.Mode.SCREEN,
            PorterDuff.Mode.SRC,
            PorterDuff.Mode.SRC_ATOP,
            PorterDuff.Mode.SRC_IN,
            PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.XOR
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);


        // Set a drawable as the image to display
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setImageResource(R.drawable.btn_default_normal_holo);

        // Get text labels and seekbars for the four color components: ARGB
        mAlphaBar = (SeekBar) findViewById(R.id.alphaSeek);
        mGreenBar = (SeekBar) findViewById(R.id.greenSeek);
        mRedBar = (SeekBar) findViewById(R.id.redSeek);
        mBlueBar = (SeekBar) findViewById(R.id.blueSeek);

        // Set a listener to update tinted image when selections have changed
        mAlphaBar.setOnSeekBarChangeListener(mSeekBarListener);
        mRedBar.setOnSeekBarChangeListener(mSeekBarListener);
        mGreenBar.setOnSeekBarChangeListener(mSeekBarListener);
        mBlueBar.setOnSeekBarChangeListener(mSeekBarListener);


        // Set up the spinner for blend mode selection from a string array resource
        mBlendSpinner = (Spinner) findViewById(R.id.blendSpinner);
        SpinnerAdapter sa = ArrayAdapter.createFromResource(this,
                R.array.blend_modes, android.R.layout.simple_spinner_dropdown_item);
        mBlendSpinner.setAdapter(sa);
        // Set a listener to update the tinted image when a blend mode is selected
        mBlendSpinner.setOnItemSelectedListener(mBlendListener);
        // Select the first item
        mBlendSpinner.setSelection(0);
        mMode = MODES[0];

    }


    /**
     * Listener that updates the tint when a blend mode is selected.
     */
    private AdapterView.OnItemSelectedListener mBlendListener =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Selected a blend mode and update the tint of image
                    mMode = MODES[mBlendSpinner.getSelectedItemPosition()];
                    mHintColor = getColor();
                    mImage.setColorFilter(mHintColor, mMode);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            };


    /**
     * Seekbar listener that updates the tinted color when the progress bar has changed.
     */
    private SeekBar.OnSeekBarChangeListener mSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    // Update the tinted color from all selections in the UI
                    mMode = MODES[mBlendSpinner.getSelectedItemPosition()];
                    mHintColor = getColor();
                    mImage.setColorFilter(mHintColor, mMode);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            };

    public int getColor() {
        final int alpha = mAlphaBar.getProgress();
        final int red = mRedBar.getProgress();
        final int green = mGreenBar.getProgress();
        final int blue = mBlueBar.getProgress();

        return Color.argb(alpha, red, green, blue);
    }

}
