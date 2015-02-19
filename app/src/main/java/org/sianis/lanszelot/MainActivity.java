package org.sianis.lanszelot;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.name)
    EditText name;

    @InjectView(R.id.age)
    EditText age;

    @InjectView(R.id.asthmaCheckBox)
    CheckBox asthmaCheckBox;

    @InjectView(R.id.text)
    EditText text;

    @InjectView(R.id.checkbox)
    CheckBox checkBox;

    private CustomSQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        db = new CustomSQLiteOpenHelper(this);
    }

    @OnClick(R.id.saveButton)
    void save() {
        if (isValid()) {
            Data data = new Data(name.getText().toString().trim(), Integer.parseInt(age.getText().toString().trim()), asthmaCheckBox.isChecked(), text.getText().toString().trim());
            db.addData(data);
            name.setText("");
            age.setText("");
            asthmaCheckBox.setChecked(false);
            text.setText("");
            checkBox.setChecked(false);
        }
    }

    public boolean isValid() {
        boolean valid = true;

        if (name.getText().toString().trim().length() == 0) {
            name.setError(getText(R.string.mandatoryError));
            valid = false;
        } else {
            name.setError(null);
        }
        if (age.getText().toString().trim().length() == 0) {
            age.setError(getText(R.string.mandatoryError));
            valid = false;
        } else {
            int parsedAge = Integer.parseInt(age.getText().toString().trim());
            if (parsedAge < 10 || parsedAge > 110) {
                age.setError(getText(R.string.ageError));
                valid = false;
            } else {
                age.setError(null);
            }
        }
        if (text.getText().toString().trim().length() == 0) {
            text.setError(getText(R.string.mandatoryError));
            valid = false;
        } else {
            text.setError(null);
        }
        if (!checkBox.isChecked()) {
            checkBox.setError(getText(R.string.mandatoryError));
            valid = false;
        } else {
            checkBox.setError(null);
        }
        return valid;
    }
}
