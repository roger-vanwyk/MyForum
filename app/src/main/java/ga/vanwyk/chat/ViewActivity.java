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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
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
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.os.Vibrator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.google.gson.Gson;

public class ViewActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> m = new HashMap<>();
	private String keyPush = "";
	private HashMap<String, Object> m2 = new HashMap<>();
	private double komenCount = 0;
	private boolean start = false;
	private String k = "";
	private String j = "";
	private String u = "";
	private String p = "";
	private double likeCount = 0;
	private HashMap<String, Object> m3 = new HashMap<>();
	private String keyLike = "";
	private HashMap<String, Object> m4 = new HashMap<>();
	private HashMap<String, Object> mu = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> lm1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> lm2 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout lin_head;
	private ListView listview1;
	private LinearLayout lin_et;
	private ImageView imageview1;
	private TextView judul;
	private LinearLayout love;
	private ImageView lv;
	private TextView tv_lv;
	private EditText et_komen;
	private TextView send;
	
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private DatabaseReference DBkomentar = _firebase.getReference("user/komentar");
	private ChildEventListener _DBkomentar_child_listener;
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference DBforum = _firebase.getReference("user/forum");
	private ChildEventListener _DBforum_child_listener;
	private Intent i = new Intent();
	private Vibrator gtr;
	private DatabaseReference DBlike = _firebase.getReference("user/like");
	private ChildEventListener _DBlike_child_listener;
	private SharedPreferences data;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		lin_head = (LinearLayout) findViewById(R.id.lin_head);
		listview1 = (ListView) findViewById(R.id.listview1);
		lin_et = (LinearLayout) findViewById(R.id.lin_et);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		judul = (TextView) findViewById(R.id.judul);
		love = (LinearLayout) findViewById(R.id.love);
		lv = (ImageView) findViewById(R.id.lv);
		tv_lv = (TextView) findViewById(R.id.tv_lv);
		et_komen = (EditText) findViewById(R.id.et_komen);
		send = (TextView) findViewById(R.id.send);
		auth = FirebaseAuth.getInstance();
		gtr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		love.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (data.getString("likeforum", "").contains(k)) {
					SketchwareUtil.showMessage(getApplicationContext(), "Kamu Sudah Menyukai");
				}
				else {
					gtr.vibrate((long)(50));
					_UpdateLikeForum(k, likeCount);
					keyLike = DBlike.push().getKey();
					m4 = new HashMap<>();
					m4.put("key", keyLike);
					m4.put("forum_key", k);
					m4.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					DBlike.child(keyLike).updateChildren(m4);
					SketchwareUtil.showMessage(getApplicationContext(), "Sukses Menyukai");
					tv_lv.setTextColor(0xFFF44336);
					lv.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
				}
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (et_komen.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Komentar isi dahulu !");
				}
				else {
					keyPush = DBkomentar.push().getKey();
					m = new HashMap<>();
					m.put("key", keyPush);
					m.put("key_forum", k);
					m.put("komen", et_komen.getText().toString());
					m.put("username", u);
					m.put("tgl", new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
					m.put("dl", "0");
					m.put("lk", "0");
					DBkomentar.child(keyPush).updateChildren(m);
					_UpdateForumKomen(k, komenCount);
					start = false;
				}
			}
		});
		
		_DBkomentar_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (start) {
					if (k.equals(_childValue.get("key_forum").toString())) {
						lm1.add(_childValue);
						listview1.setAdapter(new Listview1Adapter(lm1));
						listview1.setSelection(lm1.size());
					}
				}
				else {
					i.setClass(getApplicationContext(), FlashActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("key", k);
					i.putExtra("judul", j);
					i.putExtra("username", u);
					i.putExtra("penerbit", p);
					startActivity(i);
					finish();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				i.setClass(getApplicationContext(), FlashActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("key", k);
				i.putExtra("judul", j);
				i.putExtra("username", u);
				i.putExtra("penerbit", p);
				startActivity(i);
				finish();
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
		DBkomentar.addChildEventListener(_DBkomentar_child_listener);
		
		_DBforum_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (k.equals(_childValue.get("key").toString())) {
					komenCount = Double.parseDouble(_childValue.get("komen").toString());
					likeCount = Double.parseDouble(_childValue.get("like").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (k.equals(_childValue.get("key").toString())) {
					komenCount = Double.parseDouble(_childValue.get("komen").toString());
					likeCount = Double.parseDouble(_childValue.get("like").toString());
				}
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
		
		_DBlike_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(_childValue.get("uid").toString())) {
					lm2.add(_childValue);
					data.edit().putString("likeforum", new Gson().toJson(lm2)).commit();
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
		DBlike.addChildEventListener(_DBlike_child_listener);
		
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
		start = true;
		k = getIntent().getStringExtra("key");
		j = getIntent().getStringExtra("judul");
		u = getIntent().getStringExtra("username");
		p = getIntent().getStringExtra("penerbit");
		_radius_to(lin_head, 10, 10, "#ffffff");
		_radius_to(lin_et, 100, 10, "#ffffff");
		judul.setText(j);
		if (data.getString("likeforum", "").contains(k)) {
			lv.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
			tv_lv.setTextColor(0xFFF44336);
		}
		else {
			lv.setColorFilter(0xFF9E9E9E, PorterDuff.Mode.MULTIPLY);
			tv_lv.setTextColor(0xFF9E9E9E);
		}
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
		finish();
	}
	private void _radius_to (final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
	}
	
	
	private void _UpdateForumKomen (final String _key, final double _komentar) {
		m2 = new HashMap<>();
		m2.put("komen", String.valueOf((long)(_komentar + 1)));
		DBforum.child(_key).updateChildren(m2);
	}
	
	
	private void _UpdateLikeForum (final String _key, final double _like) {
		m3 = new HashMap<>();
		m3.put("like", String.valueOf((long)(_like + 1)));
		DBforum.child(_key).updateChildren(m3);
	}
	
	
	private void _UpdateDL_LK (final String _key, final double _dl, final double _lk) {
		mu = new HashMap<>();
		mu.put("dl", String.valueOf((long)(_dl)));
		mu.put("lk", String.valueOf((long)(_lk)));
		DBkomentar.child(_key).updateChildren(mu);
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
				_v = _inflater.inflate(R.layout.comment_c, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final LinearLayout linear5 = (LinearLayout) _v.findViewById(R.id.linear5);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final LinearLayout linear4 = (LinearLayout) _v.findViewById(R.id.linear4);
			final TextView isikomen = (TextView) _v.findViewById(R.id.isikomen);
			final TextView pengomen = (TextView) _v.findViewById(R.id.pengomen);
			final TextView tgl = (TextView) _v.findViewById(R.id.tgl);
			final ImageView dl = (ImageView) _v.findViewById(R.id.dl);
			final TextView dlc = (TextView) _v.findViewById(R.id.dlc);
			final ImageView lk = (ImageView) _v.findViewById(R.id.lk);
			final TextView lkc = (TextView) _v.findViewById(R.id.lkc);
			
			isikomen.setText(_data.get((int)_position).get("komen").toString());
			isikomen.setClickable(true);
			
			android.text.util.Linkify.addLinks(isikomen, android.text.util.Linkify.ALL);
			
			isikomen.setLinkTextColor(Color.parseColor("#2196f3"));
			
			isikomen.setLinksClickable(true);
			pengomen.setText(_data.get((int)_position).get("username").toString());
			tgl.setText(_data.get((int)_position).get("tgl").toString());
			dlc.setText(_data.get((int)_position).get("dl").toString());
			lkc.setText(_data.get((int)_position).get("lk").toString());
			dl.setColorFilter(0xFFFFCDD2, PorterDuff.Mode.MULTIPLY);
			lk.setColorFilter(0xFFBBDEFB, PorterDuff.Mode.MULTIPLY);
			_radius_to(linear1, 3, 10, "#ffffff");
			dl.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					gtr.vibrate((long)(100));
					if (Double.parseDouble(_data.get((int)_position).get("lk").toString()) == 0) {
						_UpdateDL_LK(_data.get((int)_position).get("key").toString(), Double.parseDouble(_data.get((int)_position).get("dl").toString()) + 1, 0);
					}
					else {
						_UpdateDL_LK(_data.get((int)_position).get("key").toString(), Double.parseDouble(_data.get((int)_position).get("dl").toString()) + 1, Double.parseDouble(_data.get((int)_position).get("lk").toString()) - 1);
					}
				}
			});
			lk.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					gtr.vibrate((long)(100));
					if (Double.parseDouble(_data.get((int)_position).get("dl").toString()) == 0) {
						_UpdateDL_LK(_data.get((int)_position).get("key").toString(), 0, Double.parseDouble(_data.get((int)_position).get("lk").toString()) + 1);
					}
					else {
						_UpdateDL_LK(_data.get((int)_position).get("key").toString(), Double.parseDouble(_data.get((int)_position).get("dl").toString()) - 1, Double.parseDouble(_data.get((int)_position).get("lk").toString()) + 1);
					}
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
