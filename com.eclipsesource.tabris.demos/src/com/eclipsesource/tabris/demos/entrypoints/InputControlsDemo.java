/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Contributors:
 * EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.tabris.demos.entrypoints;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.eclipsesource.tabris.widgets.ClientDialog;
import com.eclipsesource.tabris.widgets.ClientDialog.ButtonType;
import com.eclipsesource.tabris.widgets.enhancement.Widgets;

public class InputControlsDemo implements EntryPoint {

  private Text firstNameField;
  private Text lastNameField;
  private Combo countryCombo;
  private Combo classCombo;
  private DateTime dateField;
  private Button vegetarianCheckbox;
  private Label flightLabel;

  @Override
  public int createUI() {
    Display display = new Display();
    Shell shell = new Shell( display, SWT.NO_TRIM );
    shell.setMaximized( true );
    shell.setLayout( new FillLayout() );
    shell.setBackground( display.getSystemColor( SWT.COLOR_WHITE ) );
    createContent( shell );
    shell.open();
    return 0;
  }

  private void createContent( Composite parent ) {
    Composite content = new Composite( parent, SWT.NONE );
    GridLayoutFactory.fillDefaults().spacing( 0, 0 ).applyTo( content );
    createToolBar( content );

    Composite container = new Composite( content, SWT.NONE );
    GridLayoutFactory.fillDefaults().numColumns( 2 ).margins( 15, 15 ).spacing( 5, 5 ).applyTo( container );
    GridDataFactory.fillDefaults().align( SWT.FILL, SWT.FILL ).grab( true, false ).applyTo( container );
    createInputForm( container );
    createPlaceReservationButton( content );
    createFlightLabel( content );
  }

  private void createToolBar( Composite parent ) {
    ToolBar toolBar = new ToolBar( parent, SWT.NONE );
    toolBar.setLayoutData( GridDataFactory.fillDefaults().grab( true, false ).create() );
    Display display = parent.getDisplay();
    toolBar.setBackground( display.getSystemColor( SWT.COLOR_DARK_GREEN ) );
    toolBar.setForeground( display.getSystemColor( SWT.COLOR_WHITE ) );

    ToolItem title = new ToolItem( toolBar, SWT.NONE );
    Widgets.onToolItem( title ).useAsTitle();
    title.setText( "Oceanic Flight 815 Booking" );
  }

  private void createInputForm( Composite parent ) {
    firstNameField = createFirstNameField( parent );
    lastNameField = createLastNameField( parent );
    createPasswordField( parent );
    countryCombo = createCountryCombo( parent );
    classCombo = createClassCombo( parent );
    dateField = createDateField( parent );
    createVegetarianCheckbox( parent );
  }

  private Text createFirstNameField( Composite formComp ) {
    Label label = new Label( formComp, SWT.NONE );
    label.setForeground( label.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    label.setText( "First Name:" );
    final Text firstNameText = new Text( formComp, SWT.SINGLE | SWT.BORDER );
    GridData gridData1 = GridDataFactory.fillDefaults().align( SWT.FILL, SWT.TOP ).grab( true, false ).create();
    firstNameText.setLayoutData( gridData1 );
    return firstNameText;
  }

  private Text createLastNameField( Composite formComp ) {
    Label label = new Label( formComp, SWT.NONE );
    label.setForeground( label.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    label.setText( "Last Name:" );
    final Text lastNameText = new Text( formComp, SWT.SINGLE | SWT.BORDER );
    GridData gridData2 = GridDataFactory.fillDefaults().align( SWT.FILL, SWT.TOP ).grab( true, false ).create();
    lastNameText.setLayoutData( gridData2 );
    return lastNameText;
  }

  private Text createPasswordField( Composite formComp ) {
    Label label = new Label( formComp, SWT.NONE );
    label.setForeground( label.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    label.setText( "Passphrase:" );
    final Text passwordText = new Text( formComp, SWT.PASSWORD | SWT.BORDER );
    GridData gridData3 = GridDataFactory.fillDefaults().align( SWT.FILL, SWT.TOP ).grab( true, false ).create();
    passwordText.setLayoutData( gridData3 );
    return passwordText;
  }

  private Combo createCountryCombo( Composite formComp ) {
    Label label = new Label( formComp, SWT.NONE );
    label.setForeground( label.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    label.setText( "Country:" );
    final Combo combo = new Combo( formComp, SWT.BORDER );
    String[] countries = new String[]{
      "Germany", "Canada", "USA", "Bulgaria"
    };
    combo.setItems( countries );
    GridData gridData = GridDataFactory.fillDefaults().align( SWT.FILL, SWT.TOP ).grab( true, false ).create();
    combo.setLayoutData( gridData );
    combo.select( 0 );
    return combo;
  }

  private Combo createClassCombo( Composite formComp ) {
    Label label = new Label( formComp, SWT.NONE );
    label.setForeground( label.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    label.setText( "Class:" );
    final Combo classCombo = new Combo( formComp, SWT.READ_ONLY | SWT.BORDER );
    String[] classes = new String[]{
      "Business", "Economy", "Economy Plus"
    };
    classCombo.setItems( classes );
    GridData gridData = GridDataFactory.fillDefaults().align( SWT.FILL, SWT.TOP ).grab( true, false ).create();
    classCombo.setLayoutData( gridData );
    classCombo.select( 0 );
    return classCombo;
  }

  private DateTime createDateField( Composite formComp ) {
    Label label = new Label( formComp, SWT.NONE );
    label.setForeground( label.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    label.setText( "Date:" );
    int dateTimeStyle = SWT.READ_ONLY | SWT.BORDER;
    final DateTime dateTime = new DateTime( formComp, dateTimeStyle );
    GridData gridData = GridDataFactory.fillDefaults().align( SWT.FILL, SWT.TOP ).grab( true, false ).create();
    dateTime.setLayoutData( gridData );
    return dateTime;
  }

  private void createVegetarianCheckbox( Composite parent ) {
    new Label( parent, SWT.NONE );
    vegetarianCheckbox = new Button( parent, SWT.CHECK );
    vegetarianCheckbox.setForeground( vegetarianCheckbox.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    vegetarianCheckbox.setLayoutData( new GridData( SWT.LEFT, SWT.CENTER, true, false ) );
    vegetarianCheckbox.setText( "Vegetarian" );
    vegetarianCheckbox.setSelection( true );
  }

  private void createPlaceReservationButton( Composite parent ) {
    Composite buttonParent = new Composite( parent, SWT.NONE );
    buttonParent.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, false ) );
    GridLayout layout
      = GridLayoutFactory.fillDefaults().margins( 10, 10 ).spacing( 10, 10 ).create();
    buttonParent.setLayout( layout );
    Button button = new Button( buttonParent, SWT.PUSH );
    button.setBackground( parent.getDisplay().getSystemColor( SWT.COLOR_DARK_RED ) );
    button.setForeground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ) );
    button.setText( "Place Reservation" );
    button.setLayoutData( new GridData( SWT.FILL, SWT.BOTTOM, true, true ) );
    button.addSelectionListener( new SelectionAdapter() {

      @Override
      public void widgetSelected( SelectionEvent e ) {
        ClientDialog dialog = createReservationDialog();
        dialog.open();
      }
    } );
  }

  protected void updateFlightLabel() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append( "Flight booked for: " );
    stringBuilder.append( firstNameField.getText() + " " );
    stringBuilder.append( lastNameField.getText() + "\n" );
    stringBuilder.append( "Departure: " + dateField.getYear() + "/" );
    stringBuilder.append( dateField.getMonth() + 1 + "/" );
    stringBuilder.append( dateField.getDay() + "\n" );
    flightLabel.setText( stringBuilder.toString() );
  }

  private void createFlightLabel( Composite parent ) {
    Composite labelParent = new Composite( parent, SWT.NONE );
    labelParent.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    GridLayout layout
      = GridLayoutFactory.fillDefaults().margins( 1, 10 ).spacing( 0, 0 ).create();
    labelParent.setLayout( layout );
    flightLabel = new Label( labelParent, SWT.WRAP );
    flightLabel.setForeground( parent.getDisplay().getSystemColor( SWT.COLOR_BLACK ) );
    GridData layoutData = new GridData( SWT.FILL, SWT.FILL, true, true );
    flightLabel.setLayoutData( layoutData );
  }

  public ClientDialog createReservationDialog() {
    ClientDialog dialog = new ClientDialog();
    dialog.setTitle( "Reservation" );
    dialog.setMessage( "Do you really want to place\na reservation?" );
    dialog.setButton( ButtonType.CANCEL, "No" );
    dialog.setButton( ButtonType.OK, "Yes", new Listener() {

      @Override
      public void handleEvent( Event event ) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "First Name: " + firstNameField.getText() + "\n" );
        stringBuilder.append( "Last Name: " + lastNameField.getText() + "\n" );
        stringBuilder.append( "Country: " + countryCombo.getText() + "\n" );
        stringBuilder.append( "Class: " + classCombo.getText() + "\n" );
        stringBuilder.append( "Date: " + dateField.getYear() + "/" );
        stringBuilder.append( dateField.getMonth() + 1 + "/" );
        stringBuilder.append( dateField.getDay() + "\n" );
        stringBuilder.append( "Vegetarian: "
                              + String.valueOf( vegetarianCheckbox.getSelection() )
                              + "\n" );
        stringBuilder.append( "-> want to flight to the Island!!!" );
        System.out.println( stringBuilder.toString() );
        updateFlightLabel();
      }
    } );
    return dialog;
  }
}
