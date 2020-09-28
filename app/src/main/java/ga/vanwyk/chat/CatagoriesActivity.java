package ga.vanwyk.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class CatagoriesActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private boolean isAppInstalled = false;
	private double count = 0;
	private double exit_count = 0;
	
	private ArrayList<String> str_list = new ArrayList<>();
	
	private LinearLayout linear83;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear_home;
	private LinearLayout linear_team;
	private LinearLayout linear_about;
	private TextView textview3;
	private ImageView imageview1;
	private LinearLayout linear3;
	private TextView textview4;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear10;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private LinearLayout linear40;
	private LinearLayout linear11;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private TextView textview47;
	private TextView textview37;
	private TextView textview38;
	private ScrollView vscroll_team_members;
	private LinearLayout linear26;
	private LinearLayout team_member_1;
	private LinearLayout team_member_2;
	private LinearLayout team_member_3;
	private LinearLayout team_member_4;
	private LinearLayout team_member_5;
	private LinearLayout team_member_6;
	private LinearLayout team_member_7;
	private LinearLayout team_member_8;
	private LinearLayout team_member_9;
	private LinearLayout team_member_10;
	private ImageView image_member_1;
	private LinearLayout linear38;
	private TextView textview11;
	private ImageView twitter_member_1;
	private ImageView facebook_member_1;
	private ImageView plus_member_1;
	private ImageView image_member_2;
	private LinearLayout linear37;
	private TextView textview12;
	private ImageView twitter_member_2;
	private ImageView facebook_member_2;
	private ImageView plus_member_2;
	private ImageView image_member_3;
	private LinearLayout linear36;
	private TextView textview13;
	private ImageView twitter_member_3;
	private ImageView facebook_member_3;
	private ImageView plus_member_3;
	private ImageView image_member_4;
	private LinearLayout linear35;
	private TextView textview14;
	private ImageView twitter_member_4;
	private ImageView facebook_member_4;
	private ImageView plus_member_4;
	private ImageView image_member_5;
	private LinearLayout linear34;
	private TextView textview15;
	private ImageView twitter_member_5;
	private ImageView facebook_member_5;
	private ImageView plus_member_5;
	private ImageView image_member_6;
	private LinearLayout linear32;
	private TextView plus_member_6;
	private ImageView twitter_member_6;
	private ImageView facebook_member_6;
	private ImageView imageview46;
	private ImageView image_member_7;
	private LinearLayout linear31;
	private TextView textview17;
	private ImageView twitter_member_7;
	private ImageView facebook_member_7;
	private ImageView plus_member_7;
	private ImageView image_member_8;
	private LinearLayout linear30;
	private TextView textview18;
	private ImageView twitter_member_8;
	private ImageView facebook_member_8;
	private ImageView plus_member_8;
	private ImageView image_member_9;
	private LinearLayout linear29;
	private TextView textview19;
	private ImageView twitter_member_9;
	private ImageView facebook_member_9;
	private ImageView plus_member_9;
	private ImageView image_member_10;
	private LinearLayout linear28;
	private TextView textview35;
	private ImageView twitter_member_10;
	private ImageView facebook_member_10;
	private ImageView imageview59;
	private LinearLayout linear27;
	private TextView textview39;
	private TextView textview40;
	private TextView textview42;
	private TextView textview45;
	private TextView textview46;
	private ImageView image_pastorial_pair;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_linear8;
	private LinearLayout _drawer_linear_footer;
	private ImageView _drawer_imageview7;
	private LinearLayout _drawer_linear_telephone;
	private LinearLayout _drawer_linear_email;
	private LinearLayout _drawer_linear_location;
	private LinearLayout _drawer_linear_donations;
	private ImageView _drawer_image_call;
	private TextView _drawer_text_number;
	private ImageView _drawer_image_mail;
	private TextView _drawer_text_email;
	private ImageView _drawer_image_location;
	private TextView _drawer_text_location;
	private ImageView _drawer_image_donation;
	private TextView _drawer_text_donation;
	private TextView _drawer_textview6;
	private ImageView _drawer_imageview6;
	
	private Intent install = new Intent();
	private Intent intent = new Intent();
	private TimerTask timer;
	private Intent call = new Intent();
	private Intent email = new Intent();
	private ObjectAnimator oa_d = new ObjectAnimator();
	private TimerTask exit_timer;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.catagories);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(CatagoriesActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear83 = (LinearLayout) findViewById(R.id.linear83);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear_home = (LinearLayout) findViewById(R.id.linear_home);
		linear_team = (LinearLayout) findViewById(R.id.linear_team);
		linear_about = (LinearLayout) findViewById(R.id.linear_about);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		textview4 = (TextView) findViewById(R.id.textview4);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		linear40 = (LinearLayout) findViewById(R.id.linear40);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		textview47 = (TextView) findViewById(R.id.textview47);
		textview37 = (TextView) findViewById(R.id.textview37);
		textview38 = (TextView) findViewById(R.id.textview38);
		vscroll_team_members = (ScrollView) findViewById(R.id.vscroll_team_members);
		linear26 = (LinearLayout) findViewById(R.id.linear26);
		team_member_1 = (LinearLayout) findViewById(R.id.team_member_1);
		team_member_2 = (LinearLayout) findViewById(R.id.team_member_2);
		team_member_3 = (LinearLayout) findViewById(R.id.team_member_3);
		team_member_4 = (LinearLayout) findViewById(R.id.team_member_4);
		team_member_5 = (LinearLayout) findViewById(R.id.team_member_5);
		team_member_6 = (LinearLayout) findViewById(R.id.team_member_6);
		team_member_7 = (LinearLayout) findViewById(R.id.team_member_7);
		team_member_8 = (LinearLayout) findViewById(R.id.team_member_8);
		team_member_9 = (LinearLayout) findViewById(R.id.team_member_9);
		team_member_10 = (LinearLayout) findViewById(R.id.team_member_10);
		image_member_1 = (ImageView) findViewById(R.id.image_member_1);
		linear38 = (LinearLayout) findViewById(R.id.linear38);
		textview11 = (TextView) findViewById(R.id.textview11);
		twitter_member_1 = (ImageView) findViewById(R.id.twitter_member_1);
		facebook_member_1 = (ImageView) findViewById(R.id.facebook_member_1);
		plus_member_1 = (ImageView) findViewById(R.id.plus_member_1);
		image_member_2 = (ImageView) findViewById(R.id.image_member_2);
		linear37 = (LinearLayout) findViewById(R.id.linear37);
		textview12 = (TextView) findViewById(R.id.textview12);
		twitter_member_2 = (ImageView) findViewById(R.id.twitter_member_2);
		facebook_member_2 = (ImageView) findViewById(R.id.facebook_member_2);
		plus_member_2 = (ImageView) findViewById(R.id.plus_member_2);
		image_member_3 = (ImageView) findViewById(R.id.image_member_3);
		linear36 = (LinearLayout) findViewById(R.id.linear36);
		textview13 = (TextView) findViewById(R.id.textview13);
		twitter_member_3 = (ImageView) findViewById(R.id.twitter_member_3);
		facebook_member_3 = (ImageView) findViewById(R.id.facebook_member_3);
		plus_member_3 = (ImageView) findViewById(R.id.plus_member_3);
		image_member_4 = (ImageView) findViewById(R.id.image_member_4);
		linear35 = (LinearLayout) findViewById(R.id.linear35);
		textview14 = (TextView) findViewById(R.id.textview14);
		twitter_member_4 = (ImageView) findViewById(R.id.twitter_member_4);
		facebook_member_4 = (ImageView) findViewById(R.id.facebook_member_4);
		plus_member_4 = (ImageView) findViewById(R.id.plus_member_4);
		image_member_5 = (ImageView) findViewById(R.id.image_member_5);
		linear34 = (LinearLayout) findViewById(R.id.linear34);
		textview15 = (TextView) findViewById(R.id.textview15);
		twitter_member_5 = (ImageView) findViewById(R.id.twitter_member_5);
		facebook_member_5 = (ImageView) findViewById(R.id.facebook_member_5);
		plus_member_5 = (ImageView) findViewById(R.id.plus_member_5);
		image_member_6 = (ImageView) findViewById(R.id.image_member_6);
		linear32 = (LinearLayout) findViewById(R.id.linear32);
		plus_member_6 = (TextView) findViewById(R.id.plus_member_6);
		twitter_member_6 = (ImageView) findViewById(R.id.twitter_member_6);
		facebook_member_6 = (ImageView) findViewById(R.id.facebook_member_6);
		imageview46 = (ImageView) findViewById(R.id.imageview46);
		image_member_7 = (ImageView) findViewById(R.id.image_member_7);
		linear31 = (LinearLayout) findViewById(R.id.linear31);
		textview17 = (TextView) findViewById(R.id.textview17);
		twitter_member_7 = (ImageView) findViewById(R.id.twitter_member_7);
		facebook_member_7 = (ImageView) findViewById(R.id.facebook_member_7);
		plus_member_7 = (ImageView) findViewById(R.id.plus_member_7);
		image_member_8 = (ImageView) findViewById(R.id.image_member_8);
		linear30 = (LinearLayout) findViewById(R.id.linear30);
		textview18 = (TextView) findViewById(R.id.textview18);
		twitter_member_8 = (ImageView) findViewById(R.id.twitter_member_8);
		facebook_member_8 = (ImageView) findViewById(R.id.facebook_member_8);
		plus_member_8 = (ImageView) findViewById(R.id.plus_member_8);
		image_member_9 = (ImageView) findViewById(R.id.image_member_9);
		linear29 = (LinearLayout) findViewById(R.id.linear29);
		textview19 = (TextView) findViewById(R.id.textview19);
		twitter_member_9 = (ImageView) findViewById(R.id.twitter_member_9);
		facebook_member_9 = (ImageView) findViewById(R.id.facebook_member_9);
		plus_member_9 = (ImageView) findViewById(R.id.plus_member_9);
		image_member_10 = (ImageView) findViewById(R.id.image_member_10);
		linear28 = (LinearLayout) findViewById(R.id.linear28);
		textview35 = (TextView) findViewById(R.id.textview35);
		twitter_member_10 = (ImageView) findViewById(R.id.twitter_member_10);
		facebook_member_10 = (ImageView) findViewById(R.id.facebook_member_10);
		imageview59 = (ImageView) findViewById(R.id.imageview59);
		linear27 = (LinearLayout) findViewById(R.id.linear27);
		textview39 = (TextView) findViewById(R.id.textview39);
		textview40 = (TextView) findViewById(R.id.textview40);
		textview42 = (TextView) findViewById(R.id.textview42);
		textview45 = (TextView) findViewById(R.id.textview45);
		textview46 = (TextView) findViewById(R.id.textview46);
		image_pastorial_pair = (ImageView) findViewById(R.id.image_pastorial_pair);
		_drawer_linear7 = (LinearLayout) _nav_view.findViewById(R.id.linear7);
		_drawer_linear8 = (LinearLayout) _nav_view.findViewById(R.id.linear8);
		_drawer_linear_footer = (LinearLayout) _nav_view.findViewById(R.id.linear_footer);
		_drawer_imageview7 = (ImageView) _nav_view.findViewById(R.id.imageview7);
		_drawer_linear_telephone = (LinearLayout) _nav_view.findViewById(R.id.linear_telephone);
		_drawer_linear_email = (LinearLayout) _nav_view.findViewById(R.id.linear_email);
		_drawer_linear_location = (LinearLayout) _nav_view.findViewById(R.id.linear_location);
		_drawer_linear_donations = (LinearLayout) _nav_view.findViewById(R.id.linear_donations);
		_drawer_image_call = (ImageView) _nav_view.findViewById(R.id.image_call);
		_drawer_text_number = (TextView) _nav_view.findViewById(R.id.text_number);
		_drawer_image_mail = (ImageView) _nav_view.findViewById(R.id.image_mail);
		_drawer_text_email = (TextView) _nav_view.findViewById(R.id.text_email);
		_drawer_image_location = (ImageView) _nav_view.findViewById(R.id.image_location);
		_drawer_text_location = (TextView) _nav_view.findViewById(R.id.text_location);
		_drawer_image_donation = (ImageView) _nav_view.findViewById(R.id.image_donation);
		_drawer_text_donation = (TextView) _nav_view.findViewById(R.id.text_donation);
		_drawer_textview6 = (TextView) _nav_view.findViewById(R.id.textview6);
		_drawer_imageview6 = (ImageView) _nav_view.findViewById(R.id.imageview6);
		auth = FirebaseAuth.getInstance();
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (_fab.getRotation()==0) {
					_fab.animate().setDuration(200).rotation(45);
					_Show(true);
				} else {
					_fab.animate().setDuration(200).rotation(0);
					_Show(false);
				};
			}
		});
		
		_drawer_linear_telephone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_call_dialog();
			}
		});
		
		_drawer_linear_email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_email_dialog();
			}
		});
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	private void initializeLogic() {
		/**
*Created by Roger Van Wyk on 13/08/2020.
*Roger Van Wyk trading as Generic Intelligent Frameworks (G.I.F) in Google Play.
*Multi purpose app for church members.
*Chat one to one, and in groups.
*Host or join webinars.
*Stream free musical content uploaded by app users.
*/
		//Setup bottom navigation bar
		com.google.android.material.bottomnavigation.BottomNavigationView bnv = new com.google.android.material.bottomnavigation.BottomNavigationView(this); bnv.setLayoutParams(new LinearLayout.LayoutParams(-1,-2)); bnv.getMenu().add(Menu.NONE, 1, Menu.NONE, "Home").setIcon(R.drawable.icon_3); bnv.getMenu().add(Menu.NONE, 2, Menu.NONE, "Our Team").setIcon(R.drawable.icon_2); bnv.getMenu().add(Menu.NONE, 3, Menu.NONE, "About Us").setIcon(R.drawable.icon_1); bnv.setOnNavigationItemSelectedListener(new com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener() { @Override public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem item) { switch (item.getItemId()){ 
					case 1: 
					_home();
					break;
					case 2:
					
					_team();
					
					break; 
					case 3:
					
					_about();
					 
					break; 
					 } return true; } }); linear2.addView(bnv); bnv.setSelectedItemId(1); 
		setTitle("Jubilee Life Ministries");
		while(true) {
			//Reset click counts to exit app
			exit_count = 0;
			//Setup 3d
			imageview1.setBackgroundColor(0xFFBA68C8);
			imageview2.setBackgroundColor(0xFFBA68C8);
			imageview3.setBackgroundColor(0xFFBA68C8);
			imageview4.setBackgroundColor(0xFFBA68C8);
			imageview5.setBackgroundColor(0xFFBA68C8);
		}
		//Setup auto image slide timer
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						count = count;
						count++;
						if (count == 1) {
							imageview1.setImageResource(R.drawable.ps_brouwers);
						}
						if (count == 2) {
							imageview1.setImageResource(R.drawable.vwest);
						}
						if (count == 3) {
							imageview1.setImageResource(R.drawable.grabouw);
						}
						if (count == 4) {
							imageview1.setImageResource(R.drawable.ravensmead);
						}
						if (count == 5) {
							imageview1.setImageResource(R.drawable.brandvlei);
						}
						if (count == 6) {
							count = 0;
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(timer, (int)(0), (int)(3000));
		//Setup multiple fabs
		_custom_fab();
		_fab1();
		_fab2();
		_fab3();
		_Show(false);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}
	private void _Remove (final View _view) {
		((ViewGroup)_view.getParent()).removeView(_view);
	}
	
	
	private void _Show (final boolean _Show) {
		if (_Show) {
			bg.setVisibility(View.VISIBLE);
			bg.setTranslationY((int)getDip(50));
			
			
			bg.setAlpha(0);
			
			
			bg.animate().setDuration(200).alpha(1f).translationY(0);
			
			
			_fab.animate().setDuration(200).rotation(45);
		}
		else {
			bg.setVisibility(View.GONE);
		}
	}
	private LinearLayout bg;
	{
	}
	
	
	private void _SetRadiusToView (final View _view, final double _radius, final String _Colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); gd.setColor(Color.parseColor(_Colour)); gd.setCornerRadius((int)_radius); _view.setBackground(gd);
	}
	
	
	private void _fab1 () {
		LinearLayout fab1 = (LinearLayout)bg.findViewById(R.id.linear9);
		
		_SetRadiusToView(fab1, (int)getDip(20), "#3770FD");
		
		fab1.setElevation(8f);
		
		fab1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				intent.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(intent);
			}});
	}
	
	
	private void _fab2 () {
		//Setup fab
		LinearLayout fab2 = (LinearLayout)bg.findViewById(R.id.linear8);
		
		_SetRadiusToView(fab2, (int)getDip(20), "#3F51B5");
		
		fab2.setElevation(8f);
		
		fab2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Check if jitsi meet is installed
				boolean isAppInstalled = appInstalledOrNot("org.jitsi.meet");
				if (isAppInstalled) {
					//Open jitsi meet
					Intent launchIntent = getPackageManager().getLaunchIntentForPackage("org.jitsi.meet"); startActivity(launchIntent);
				}
				else {
					//Download jitsi meet
					install.setAction(Intent.ACTION_VIEW);
					install.setData(Uri.parse("https://play.google.com/store/apps/details?id=org.jitsi.meet"));
					startActivity(install);
				}
			}});
	}
	
	
	private void _fab3 () {
		//Setup fab
		LinearLayout fab3 = (LinearLayout)bg.findViewById(R.id.linear7);
		
		_SetRadiusToView(fab3, (int)getDip(20), "#FF0700");
		
		fab3.setElevation(8f);
		
		fab3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				_exit_dialog();
			}});
	}
	
	
	private void _custom_fab () {
		View cv = getLayoutInflater().inflate(R.layout.multiple_fabs, null);
		
		bg = (LinearLayout)cv.findViewById(R.id.linear1);
		
		_Remove(bg);
		
		((ViewGroup)_fab.getParent()).addView(bg);
	}
	
	
	private void _exitApp () {
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			FirebaseAuth.getInstance().signOut();
		}
		finish();
	}
	
	
	private void _extra () {
		//Check if app is installed
	}
	
	private boolean appInstalledOrNot(String uri) { android.content.pm.PackageManager pm = getPackageManager(); try { pm.getPackageInfo(uri, android.content.pm.PackageManager.GET_ACTIVITIES); return true; } catch (android.content.pm.PackageManager.NameNotFoundException e) { } return false;
	}
	
	
	private void _call_dialog () {
		final AlertDialog call_dialog = new AlertDialog.Builder(CatagoriesActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		
		View convertView = (View) inflater.inflate(R.layout.call_dialog, null);
		call_dialog.setView(convertView);
		
		call_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  call_dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		
		
		LinearLayout i_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg);
		
		LinearLayout i_div = (LinearLayout) convertView.findViewById(R.id.linear_div);
		
		Button i_ok = (Button) convertView.findViewById(R.id.call_okay_button);
		
		Button i_cancel = (Button) convertView.findViewById(R.id.call_cancel_button);
		
		ImageView i_header = (ImageView) convertView.findViewById(R.id.img_header);
		
		TextView i_title = (TextView) convertView.findViewById(R.id.txt_title);
		
		TextView i_msg = (TextView) convertView.findViewById(R.id.txt_msg);
		
		_setBgCorners(i_bg, 8, "#FFFFFF");
		_setBgCorners(i_ok, 8, "#03A9F4");
		_setBgCorners(i_cancel, 8, "#68CFFD");
		i_header.setElevation(5);
		i_ok.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				call_dialog.dismiss();
				call.setAction(Intent.ACTION_CALL);
				call.setData(Uri.parse("tel:0219311374"));
				startActivity(call);
			}});
		i_cancel.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				call_dialog.dismiss();
				SketchwareUtil.showMessage(getApplicationContext(), "Cancelled");
			}});
		call_dialog.show();
		_bounce(i_header);
	}
	
	
	private void _exit_dialog () {
		final AlertDialog exit_dialog = new AlertDialog.Builder(MainActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		
		View convertView = (View) inflater.inflate(R.layout.exit_dialog, null);
		exit_dialog.setView(convertView);
		
		exit_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  exit_dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		
		
		LinearLayout i_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg);
		
		LinearLayout i_div = (LinearLayout) convertView.findViewById(R.id.linear_div);
		
		Button i_ok = (Button) convertView.findViewById(R.id.exit_okay_button);
		
		Button i_cancel = (Button) convertView.findViewById(R.id.exit_cancel_button);
		
		ImageView i_header = (ImageView) convertView.findViewById(R.id.img_header);
		
		TextView i_title = (TextView) convertView.findViewById(R.id.txt_title);
		
		TextView i_msg = (TextView) convertView.findViewById(R.id.txt_msg);
		
		_setBgCorners(i_bg, 8, "#FFFFFF");
		_setBgCorners(i_ok, 8, "#03A9F4");
		_setBgCorners(i_cancel, 8, "#68CFFD");
		i_header.setElevation(5);
		i_ok.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				exit_dialog.dismiss();
				_exitApp();
			}});
		i_cancel.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				exit_dialog.dismiss();
				SketchwareUtil.showMessage(getApplicationContext(), "Cancelled!");
			}});
		exit_dialog.show();
		_bounce(i_header);
	}
	
	
	private void _setCornerRadius (final View _view, final double _value, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_value);
		_view.setBackground(gd);
	}
	
	
	private void _set3d (final View _vw, final double _n) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { _vw.setElevation((int)_n);}
	}
	
	
	private void _home () {
		linear_home.setVisibility(View.VISIBLE);
		linear_dashboard.setVisibility(View.GONE);
		linear_settings.setVisibility(View.GONE);
	}
	
	
	private void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	private void _gd (final View _view, final double _numb, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_numb);
		_view.setBackground(gd);
	}
	
	
	private void _team () {
		linear_home.setVisibility(View.GONE);
		linear_team.setVisibility(View.VISIBLE);
		linear_about.setVisibility(View.GONE);
	}
	
	
	private void _about () {
		linear_home.setVisibility(View.GONE);
		linear_team.setVisibility(View.GONE);
		linear_about.setVisibility(View.VISIBLE);
	}
	
	
	private void _roundImageView (final ImageView _imageview, final double _round) {
		//Created by Roger Van Wyk
		Bitmap bm = ((android.graphics.drawable.BitmapDrawable)_imageview.getDrawable()).getBitmap();
		
		_imageview.setImageBitmap(getRoundedCornerBitmap(bm, ((int)_round)));
		
	}
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); 
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
	
	
	private void _setBgCorners (final View _view, final double _radius, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
		gd.setColor(Color.parseColor("#" + _color.replace("#", ""))); /* color */
		gd.setCornerRadius((int)_radius); /* radius */
		gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
		_view.setBackground(gd);
	}
	
	
	private void _bounce (final View _view) {
		oa_d.setTarget(_view);
		oa_d.setPropertyName("rotation");
		oa_d.setFloatValues((float)(90), (float)(0));
		oa_d.setDuration((int)(1000));
		oa_d.setInterpolator(new BounceInterpolator());
		oa_d.start();
	}
	
	
	private void _email_dialog () {
		final AlertDialog email_dialog = new AlertDialog.Builder(CatagoriesActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		
		View convertView = (View) inflater.inflate(R.layout.email_dialog, null);
		email_dialog.setView(convertView);
		
		email_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  email_dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		
		
		LinearLayout i_bg = (LinearLayout) convertView.findViewById(R.id.linear_bg);
		
		LinearLayout i_div = (LinearLayout) convertView.findViewById(R.id.linear_div);
		
		Button i_ok = (Button) convertView.findViewById(R.id.email_okay_button);
		
		Button i_cancel = (Button) convertView.findViewById(R.id.email_cancel_button);
		
		ImageView i_header = (ImageView) convertView.findViewById(R.id.img_header);
		
		TextView i_title = (TextView) convertView.findViewById(R.id.txt_title);
		
		TextView i_msg = (TextView) convertView.findViewById(R.id.txt_msg);
		
		_setBgCorners(i_bg, 8, "#FFFFFF");
		_setBgCorners(i_ok, 8, "#03A9F4");
		_setBgCorners(i_cancel, 8, "#68CFFD");
		i_header.setElevation(5);
		i_ok.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				email_dialog.dismiss();
				email.setAction(Intent.ACTION_VIEW);
				email.setData(Uri.parse("mailto:info@jubileelife.co.za"));
				email.putExtra("subject", "what are you discussing");
				email.putExtra("message", "write your message here");
				startActivity(email);
			}});
		i_cancel.setOnClickListener(new View.OnClickListener(){
			    public void onClick(View v){
				email_dialog.dismiss();
				SketchwareUtil.showMessage(getApplicationContext(), "Cancelled");
			}});
		email_dialog.show();
		_bounce(i_header);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
