package com.letsappit.mineautomation.Zone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.letsappit.mineautomation.R;
import com.letsappit.mineautomation.data.DBOps;
import com.letsappit.mineautomation.data.MAContract;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import utils.Util;

public class RowEditorZone extends AppCompatActivity {

    private EditText codeEditText,  descriptionEditText;
    private TextView updated;
    private Button update,delete, add;
    private Spinner locationSpinner;
    ArrayList<String> allLocations;
    String code;
    int id;
    Zone currentZone;
    String description,primaryLocationCode;
    private Date datetime;

    final int  DIALOG_TYPE_PROGRESS = 1,
            DIALOG_TYPE_ALERT = 2, ALERT_TYPE_FAILURE = 2,
            ALERT_TYPE_SUCCESS = 1;
    ProgressDialog reqProgress;
    AlertDialog reDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_activity_zone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        allLocations = new ArrayList<>();
        allLocations = DBOps.getAllRecords(this,
                MAContract.PrimaryLocation.TABLE_NAME,
                MAContract.PrimaryLocation.COLUMN_CODE,
                MAContract.PrimaryLocation.COLUMN_DESCRIPTION);
        setSupportActionBar(toolbar);
        code = getIntent().getStringExtra("ROW_CODE");

        try {
            manageLayoutFromOpType(getIntent().getIntExtra("OP_TYPE",0));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void manageLayoutFromOpType(int opType) throws ParseException {
        if(opType!=0)
        {
            if(opType ==1)
            {
                initializeViews(opType);
                locationSpinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, allLocations));
            }
            else
            {
                initializeViews(opType);
               currentZone = DBOps.getZoneByCode(this, code);
                String description  = DBOps.getLocationByCode(this,currentZone.getPrimaryLocationCode()).getDescription();
                int index = allLocations.indexOf(currentZone.getPrimaryLocationCode()+" "+description);
                populateViews(currentZone);
id = currentZone.getId();
                locationSpinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, allLocations));
                locationSpinner.setSelection(index);

            }
        }
    }

    private void populateViews(Zone currentZone) {

        codeEditText.setText(currentZone.getCode());
        descriptionEditText.setText(currentZone.getDescription());
        updated.setText(updated.getText() + Util.getFormatedDate(currentZone.getUpdatedOn()));
    }

    private void initializeViews(int opType) {
locationSpinner =  (Spinner) findViewById(R.id.location_spinner);
        codeEditText = (EditText) findViewById(R.id.code_edit_text);
        descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        updated = (TextView) findViewById(R.id.updated_text_view);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);


        if(opType==1)
        {
            add = (Button) findViewById(R.id.add);
            add.setVisibility(View.VISIBLE);

            update.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }
        else
        {
            add = (Button) findViewById(R.id.add);
            add.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }
    }
    public void insert(View v)
    {
        datetime = new Date();
        code = codeEditText.getEditableText().toString();
        description = descriptionEditText.getEditableText().toString();
        primaryLocationCode = locationSpinner.getSelectedItem().toString().split(" ")[0];


        long i = DBOps.insertZone(this, new Zone(code, description, datetime,0,primaryLocationCode));
        if(i!=-1)
        {
            showDialog("Insert Successfull","Entry added successfully",DIALOG_TYPE_ALERT,ALERT_TYPE_SUCCESS);
        }
        else
        {
            showDialog("Error","Failed to add this entry",DIALOG_TYPE_ALERT,ALERT_TYPE_FAILURE);

        }

    }

    public void delete(View v)
    {

        int i =  DBOps.deleteRow(this, code, MAContract.Zone.TABLE_NAME, MAContract.Zone.COLUMN_CODE);
        if(i==1)
        {
            showDialog("Delete Successfull","Entry deleted successfully",DIALOG_TYPE_ALERT,ALERT_TYPE_SUCCESS);
        }
        else
        {
            showDialog("Error","Failed to delete this entry",DIALOG_TYPE_ALERT,ALERT_TYPE_FAILURE);
        }

    }
    public void update(View v)
    {
        datetime = new Date();
        code = codeEditText.getEditableText().toString();
        description = descriptionEditText.getEditableText().toString();
        primaryLocationCode = locationSpinner.getSelectedItem().toString().split(" ")[0];
        int i = DBOps.updateZone(this, new Zone(code, description, datetime, id, primaryLocationCode));
        if(i==1)
        {
            showDialog("Update Successfull","Entry updated successfully",DIALOG_TYPE_ALERT,ALERT_TYPE_SUCCESS);
        }
        else
        {
            showDialog("Error","Failed to update this entry",DIALOG_TYPE_ALERT,ALERT_TYPE_FAILURE);
        }
    }
    private void showDialog(String title, String message, int dialogType,
                            int alertType) {

        switch (dialogType) {
            case DIALOG_TYPE_PROGRESS:
                reqProgress = new ProgressDialog(this);
                reqProgress.setTitle(title);
                reqProgress.setMessage(message);

                reqProgress.show();
                break;
            case DIALOG_TYPE_ALERT:
                try {
                    reDialog = new AlertDialog.Builder(this).create();
                    reDialog.setTitle(title);

                    reDialog.setMessage(message);
                    if (alertType == ALERT_TYPE_SUCCESS) {

                        reDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        // TODO Auto-generated method stub
                                        finish();

                                    }
                                });
                    } else if (alertType == ALERT_TYPE_FAILURE) {
                        reDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        // TODO Auto-generated method stub

                                    }
                                });
                    }
                    reDialog.show();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;

        }

    }





    private void stopProgress() {
        if (reqProgress != null && reqProgress.isShowing()) {
            reqProgress.dismiss();
        }
    }



}
