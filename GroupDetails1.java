package com.example.jerusalemscav;

import java.util.ArrayList;

import com.example.jerusalemscav.R.id;

import android.os.Bundle;
import android.app.Activity;
//import android.content.Context;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputMethodManager;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.KeyEvent;
//import android.widget.TextView.OnEditorActionListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
//import android.view.ViewGroup.LayoutParams;
import android.content.Intent;
import android.drm.DrmStore.RightsStatus;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GroupDetails1 extends Activity {
	TextView passedActivity, text;
	EditText groupNum,groupName;
	
	EditText numInGroup, GroupDetButton;
	LinearLayout fill,groupDetails; 
	RelativeLayout[] groupDetailsContainer;
	Button buttonGroupDetails;
	Spinner spinner;
	Spinner[] spinnerArray;
	TextView[] row_Number, firstNameText,lastNameText;
	RelativeLayout  groupDetailsList;
	String scavJob;
	Button[] playerDetailsButton;
	RelativeLayout.OnClickListener PlayerDetailsButtonOnClick;
	int i;
	String inputFName;
	String inputLName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jlm_oc_group_details1);

		/*display of data from previous activity
		 * final String text1 = getIntent().getStringExtra("UserName"); final
		 * String textPWord = getIntent().getStringExtra("Password");
		 * 
		 * passedActivity = (TextView)findViewById(R.id.textView1);
		 * passedActivity.setText(text1 +' '+textPWord);
		 */

		groupName = (EditText) findViewById(R.id.SetUp_Jer1_Input_NameOfGroup);
		GroupDetButton = (EditText) findViewById(R.id.SetUp_Jer1_Input_NoOfPeople);
		buttonGroupDetails = (Button) findViewById(R.id.GroupDetailsInfoButton);
		
		OnClickListener groupDetailsButton = (new OnClickListener() {

			@Override
			public void onClick(View v) {
				String groupNum1 = GroupDetButton.getText().toString();
				String groupName1 = groupName.getText().toString();
				toast(groupNum1 + ' ' + groupName1);
				
				Integer number = Integer.parseInt(groupNum1);
				buildDynamicViews(number);
			}
		});
		
		
		buttonGroupDetails.setOnClickListener(groupDetailsButton);
		

		OnClickListener groupDetailButtonClick = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent groupDetailButton = new Intent(GroupDetails1.this,
						GameStart.class);
				// groupDetailButton.putExtra("Data1", input);
				// groupDetailButton.putExtra("Data2",textPWord);
				startActivity(groupDetailButton);

			}

		};

		buttonGroupDetails = (Button) findViewById(R.id.GroupDetailsButton1);
		buttonGroupDetails.setOnClickListener(groupDetailButtonClick);

		// Add data capture of new inputs
	}

	public void fillSpinner() {
		spinner = (Spinner) findViewById(R.id.groupDetailsSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.TeamRoles, android.R.layout.simple_spinner_item);

		spinner.setAdapter(adapter);

	}

	public void buildDynamicViews(Integer input1) {
			int textId = 10; 
		
		groupDetailsList = (RelativeLayout)findViewById(R.id.playersNameLayout);
		row_Number = new TextView[input1];
		spinnerArray = new Spinner[input1];
		groupDetailsContainer = new RelativeLayout[input1];
		firstNameText = new TextView[input1];
		lastNameText = new TextView[input1];
		final EditText[] fNameInput= new EditText[input1];
		final EditText[] lNameInput= new EditText[input1]; 
		playerDetailsButton = new Button[input1];
		
		
		
		text = (TextView)findViewById(R.id.displayMessage);
		ViewGroup.LayoutParams params = text.getLayoutParams();
		params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		text.setId(textId);
		((MarginLayoutParams) params).setMargins(0,0,0,20);
		text.setLayoutParams(params);
		String txt1 = getResources().getString(R.string.player_details_Title);
		String txt2 = getResources().getString(R.string.players_details_message);
		String txt = txt1+"\n"+txt2;
		text.setText(txt);
		
		ScrollView scroll = (ScrollView)findViewById(R.id.playerNameScroller);
		RelativeLayout.LayoutParams scrollerLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		scrollerLayoutParams.addRule(RelativeLayout.BELOW, text.getId());
		scroll.setLayoutParams(scrollerLayoutParams);
		
		
		
		for ( i = 0; i < input1; i++) {
			groupDetailsContainer[i] = new RelativeLayout(this);
			groupDetailsContainer[i].setId(20+i);
			RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			if(i==0){
					containerParams.addRule(RelativeLayout.BELOW ,text.getId());
					}
					else
				{
				containerParams.addRule(RelativeLayout.BELOW, (i+19));
				}
			containerParams.setMargins(0,0,0,20);
			groupDetailsContainer[i].setLayoutParams(containerParams);
			
						
			//LayoutParams textLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			row_Number[i]= new TextView(this);
			LayoutParams rowNumParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rowNumParams.addRule(RelativeLayout.ALIGN_LEFT);
			if(i ==0)
				rowNumParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			else
			{
				rowNumParams.addRule(RelativeLayout.BELOW, (i-1));
			
			}
			row_Number[i].setLayoutParams(rowNumParams);
			row_Number[i].setText("Details for Player Number:" + (i+1));
			//row_Number[i].setBackgroundColor(Color.GREEN);
			row_Number[i].setHeight(80);
			row_Number[i].setTextSize(20);
			row_Number[i].setWidth(125);
			row_Number[i].setId((i+1));

			
			RelativeLayout.LayoutParams fNameParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			fNameParams.addRule(RelativeLayout.RIGHT_OF, row_Number[i].getId() );
			firstNameText[i] = new TextView(this);
			firstNameText[i].setTextSize(20);
			firstNameText[i].setHeight(40);
			firstNameText[i].setText("Enter First Name:");
			firstNameText[i].setId(31*(i+1));
			firstNameText[i].setLayoutParams(fNameParams);
			
			RelativeLayout.LayoutParams fNameinputParams = new LayoutParams(200, 25);
			fNameinputParams.addRule(RelativeLayout.RIGHT_OF, firstNameText[i].getId());
			fNameinputParams.addRule(RelativeLayout.ALIGN_BASELINE, firstNameText[i].getId());
			fNameinputParams.setMargins(10,	0, 10, 0);
			fNameInput[i] = new EditText(this);
			fNameInput[i].setLayoutParams(fNameinputParams);
			fNameInput[i].setId(32*(i+1));
			fNameInput[i].setTextSize(20);
			
			
			RelativeLayout.LayoutParams lNameParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lNameParams.addRule(RelativeLayout.BELOW, firstNameText[i].getId() );
			lNameParams.addRule(RelativeLayout.RIGHT_OF, row_Number[i].getId() );
			lastNameText[i] = new TextView(this);
			lastNameText[i].setTextSize(20);
			lastNameText[i].setHeight(40);
			lastNameText[i].setText("Enter Last Name:");
			lastNameText[i].setLayoutParams(lNameParams);
			lastNameText[i].setId(41*(i+1));
			
			RelativeLayout.LayoutParams lNameinputParams = new LayoutParams(200, 25);
			lNameinputParams.addRule(RelativeLayout.RIGHT_OF, lastNameText[i].getId());
			lNameinputParams.addRule(RelativeLayout.BELOW, fNameInput[i].getId());
			lNameinputParams.addRule(RelativeLayout.ALIGN_BASELINE, lastNameText[i].getId());
			lNameinputParams.setMargins(10,	0, 10, 0);
			lNameInput[i] = new EditText(this);
			lNameInput[i].setLayoutParams(lNameinputParams);
			lNameInput[i].setId(33*(i+1));
			lNameInput[i].setTextSize(20);
			
			
			
			spinnerArray[i] = new Spinner(this);
			LayoutParams spinnerLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			if(i==0){
				spinnerLayout.addRule(RelativeLayout.BELOW, lastNameText[0].getId());
				spinnerLayout.addRule(RelativeLayout.RIGHT_OF,row_Number[i].getId()); 
			}
			else
			{
				spinnerLayout.addRule(RelativeLayout.BELOW, lastNameText[i].getId());
				spinnerLayout.addRule(RelativeLayout.RIGHT_OF,row_Number[i].getId()); 
				
			}
			
			spinnerArray[i].setId(100+i);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
					this, R.array.TeamRoles, android.R.layout.simple_spinner_item);
			spinnerArray[i].setAdapter(adapter);
			spinnerArray[i].setLayoutParams(spinnerLayout);
			spinnerArray[i].setId(20*(i+1));
			
			RelativeLayout.LayoutParams detailsButtonParams = new LayoutParams (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			playerDetailsButton[i] = new Button(this);
			detailsButtonParams.addRule(RelativeLayout.RIGHT_OF, spinnerArray[i].getId());
			detailsButtonParams.addRule(RelativeLayout.ALIGN_BOTTOM, spinnerArray[i].getId());
			detailsButtonParams.setMargins(20, 0, 20, 0);
			playerDetailsButton[i].setLayoutParams(detailsButtonParams);
			playerDetailsButton[i].setWidth(250);
			playerDetailsButton[i].setHeight(20);
			playerDetailsButton[i].setText("Press to save details");
			
			
			
			
			groupDetailsContainer[i].addView(row_Number[i]);
			groupDetailsContainer[i].addView(fNameInput[i]);
			groupDetailsContainer[i].addView(lNameInput[i]);
			groupDetailsContainer[i].addView(firstNameText[i]);
			groupDetailsContainer[i].addView(lastNameText[i]);
			groupDetailsContainer[i].addView(spinnerArray[i]); 
			groupDetailsContainer[i].addView(playerDetailsButton[i]);
			groupDetailsList.addView(groupDetailsContainer[i]);
			
			
		PlayerDetailsButtonOnClick = new OnClickClass() {
				
				@Override
				public void onClick(View v, int i) {
					passButtonDetails data = new passButtonDetails();
					inputFName = getText(fNameInput[i].getId()).toString();
					inputLName = getText(lNameInput[i].getId()).toString();
					toast(inputFName);
					data.setFirstName(inputFName);
					data.setLastname(inputLName);
					playerDetailsButton[i].setTag(data);
					
					
				}
			};	
			
			playerDetailsButton[i].setOnClickListener(PlayerDetailsButtonOnClick);
			
			/*inputFName = fNameInput[i].getText().toString();
			toast(inputFName);
			
			data.setFirstName(inputFName);
			data.setLastname(lNameInput[i].getText().toString());
			playerDetailsButton[i].setTag(data);
			*/
			
		
			
			//playerDetailsButton[i].setOnClickListener(PlayerDetailsButtonOnClick(fNameInput[i],fNameInput[i]));

			
			
			
		}
		
		
		
	}

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_details1, menu);
		return true;
	}

	public void toast(String groupNum1) {
		Toast.makeText(GroupDetails1.this, groupNum1, Toast.LENGTH_LONG).show();
	}

}

