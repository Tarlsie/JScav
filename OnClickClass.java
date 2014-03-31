package com.example.jerusalemscav;

import android.view.View;

public class OnClickClass implements View.OnClickListener{
	private View view;
	private int i;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v, int i) {
		this.i = i;
		this.view = v;
		
	}

	
	
	/*@Override
	public void onClick(View v) {
		passButtonDetails data = new passButtonDetails();
		inputFName = getResources().getText(fNameInput[i].getId()).toString();
		inputLName = getResources().getText(lNameInput[i].getId()).toString();
		toast(inputFName);
		data.setFirstName(inputFName);
		data.setLastname(inputLName);
		playerDetailsButton[i].setTag(data);
		
		passButtonDetails buttonData = (passButtonDetails)v.getTag();
		String fName = buttonData.getFirstName();
		String lName = buttonData.getLastName();
		String fullName = fName+" " +lName;
		toast(fullName);
		
	}*/
	

}
