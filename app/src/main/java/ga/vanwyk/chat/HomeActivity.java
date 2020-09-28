package ga.vanwyk.chat;

import androidx.appcompat.app.AppCompatActivity;
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
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import com.google.gson.Gson;
import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String putStringUsername = "";
	private String putStringFoto = "";
	private String keyword = "";
	private double klikgo = 0;
	
	private ArrayList<HashMap<String, Object>> lm1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm2 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout lin_addforum;
	private ListView listview1;
	private ImageView img_cari;
	private EditText et_cari;
	private ImageView fotoprofile;
	private ImageView imageview1;
	private TextView textview1;
	
	private Intent i = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private DatabaseReference DataBase = _firebase.getReference("user/db");
	private ChildEventListener _DataBase_child_listener;
	private DatabaseReference Database = _firebase.getReference("user/forum");
	private ChildEventListener _Database_child_listener;
	private SharedPreferences data;
	private DatabaseReference DBforum = _firebase.getReference("user/memberforum");
	private ChildEventListener _DBforum_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		lin_addforum = (LinearLayout) findViewById(R.id.lin_addforum);
		listview1 = (ListView) findViewById(R.id.listview1);
		img_cari = (ImageView) findViewById(R.id.img_cari);
		et_cari = (EditText) findViewById(R.id.et_cari);
		fotoprofile = (ImageView) findViewById(R.id.fotoprofile);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		auth = FirebaseAuth.getInstance();
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		lin_addforum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), AddforumActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("username", putStringUsername);
				startActivity(i);
			}
		});
		
		et_cari.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 0) {
					klikgo = 0;
					listview1.setAdapter(new Listview1Adapter(lm1));
					((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				}
				else {
					keyword = et_cari.getText().toString();
					klikgo = 1;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_DataBase_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(_childValue.get("uid").toString())) {
					putStringUsername = _childValue.get("username").toString();
					putStringFoto = _childValue.get("foto").toString();
					_curcle_igm_url(putStringFoto, fotoprofile);
					lin_addforum.setEnabled(true);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		DataBase.addChildEventListener(_DataBase_child_listener);
		
		_Database_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		Database.addChildEventListener(_Database_child_listener);
		
		_DBforum_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(_childValue.get("idm").toString())) {
					lm2.add(_childValue);
					data.edit().putString("memberforum", new Gson().toJson(lm2)).commit();
				}
				Database.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						lm1 = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								lm1.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(lm1));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		DBforum.addChildEventListener(_DBforum_child_listener);
		
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
		_BoardasLay(lin_addforum);
		lin_addforum.setEnabled(false);
		img_cari.setColorFilter(0xFF757575, PorterDuff.Mode.MULTIPLY);
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
	public void onStart() {
		super.onStart();
		Database.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lm1 = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lm1.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				listview1.setAdapter(new Listview1Adapter(lm1));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	private void _BoardasLay (final View _view) {
		android.graphics.drawable.GradientDrawable BCHIADE = new android.graphics.drawable.GradientDrawable();
		int BCHIADEADD[] = new int[]{ Color.argb(255,26,97,191), Color.argb(255,28,97,175) };
		BCHIADE.setColors(BCHIADEADD);
		BCHIADE.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP);
		BCHIADE.setCornerRadii(new float[] { 15, 15, 15, 15, 15, 15, 15, 15 });
		BCHIADE.setStroke(0, Color.argb(255,15,15,15));
		android.graphics.drawable.RippleDrawable BCHIADE_RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.argb(255,214,66,44)}), BCHIADE, null);
		_view.setBackground(BCHIADE_RE);
		if(Build.VERSION.SDK_INT >= 21) { _view.setElevation(45f); }
	}
	
	
	private void _radius_to (final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
	}
	
	
	private void _curcle_igm_url (final String _url, final ImageView _img_view) {
		
		Glide.with(getApplicationContext()).asBitmap().load(_url).centerCrop().into(new com.bumptech.glide.request.target.BitmapImageViewTarget(_img_view) {
			@Override protected void setResource(Bitmap resource) {
				androidx.core.graphics.drawable.RoundedBitmapDrawable circularBitmapDrawable = androidx.core.graphics.drawable.RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource); circularBitmapDrawable.setCircular(true); _img_view.setImageDrawable(circularBitmapDrawable);
			}
		});
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.forum_c, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final TextView title = (TextView) _v.findViewById(R.id.title);
			final Button btn_joint = (Button) _v.findViewById(R.id.btn_joint);
			final Button btn_open = (Button) _v.findViewById(R.id.btn_open);
			final LinearLayout linear4 = (LinearLayout) _v.findViewById(R.id.linear4);
			final LinearLayout linear5 = (LinearLayout) _v.findViewById(R.id.linear5);
			final LinearLayout linear6 = (LinearLayout) _v.findViewById(R.id.linear6);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final TextView member = (TextView) _v.findViewById(R.id.member);
			final TextView textview5 = (TextView) _v.findViewById(R.id.textview5);
			final ImageView imageview2 = (ImageView) _v.findViewById(R.id.imageview2);
			final TextView komen = (TextView) _v.findViewById(R.id.komen);
			final TextView textview6 = (TextView) _v.findViewById(R.id.textview6);
			final ImageView imageview3 = (ImageView) _v.findViewById(R.id.imageview3);
			final TextView like = (TextView) _v.findViewById(R.id.like);
			final TextView textview7 = (TextView) _v.findViewById(R.id.textview7);
			
			if (klikgo == 1) {
				if (_data.get((int)_position).get("judul").toString().toUpperCase().contains(keyword.toUpperCase())) {
					title.setText(_data.get((int)_position).get("judul").toString());
					member.setText(_data.get((int)_position).get("member").toString());
					komen.setText(_data.get((int)_position).get("komen").toString());
					like.setText(_data.get((int)_position).get("like").toString());
					if (data.getString("memberforum", "").contains(_data.get((int)_position).get("key").toString())) {
						btn_joint.setVisibility(View.GONE);
						btn_open.setVisibility(View.VISIBLE);
					}
					else {
						btn_joint.setVisibility(View.VISIBLE);
						btn_open.setVisibility(View.GONE);
					}
					imageview1.setColorFilter(0xFF2196F3, PorterDuff.Mode.MULTIPLY);
					imageview2.setColorFilter(0xFF2196F3, PorterDuff.Mode.MULTIPLY);
					imageview3.setColorFilter(0xFF2196F3, PorterDuff.Mode.MULTIPLY);
				}
				else {
					linear1.setVisibility(View.GONE);
				}
			}
			else {
				title.setText(_data.get((int)_position).get("judul").toString());
				member.setText(_data.get((int)_position).get("member").toString());
				komen.setText(_data.get((int)_position).get("komen").toString());
				like.setText(_data.get((int)_position).get("like").toString());
				if (data.getString("memberforum", "").contains(_data.get((int)_position).get("key").toString())) {
					btn_joint.setVisibility(View.GONE);
					btn_open.setVisibility(View.VISIBLE);
				}
				else {
					btn_joint.setVisibility(View.VISIBLE);
					btn_open.setVisibility(View.GONE);
				}
				imageview1.setColorFilter(0xFF2196F3, PorterDuff.Mode.MULTIPLY);
				imageview2.setColorFilter(0xFF2196F3, PorterDuff.Mode.MULTIPLY);
				imageview3.setColorFilter(0xFF2196F3, PorterDuff.Mode.MULTIPLY);
			}
			_radius_to(linear1, 10, 10, "#FFFFFF");
			btn_joint.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), JoinActivity.class);
					i.putExtra("key", _data.get((int)_position).get("key").toString());
					i.putExtra("judul", _data.get((int)_position).get("judul").toString());
					i.putExtra("penerbit", _data.get((int)_position).get("username_pemilik").toString());
					i.putExtra("private", _data.get((int)_position).get("pass").toString());
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});
			btn_open.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), ViewActivity.class);
					i.putExtra("key", _data.get((int)_position).get("key").toString());
					i.putExtra("judul", _data.get((int)_position).get("judul").toString());
					i.putExtra("penerbit", _data.get((int)_position).get("username_pemilik").toString());
					i.putExtra("username", putStringUsername);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});
			
			return _v;
		}
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
