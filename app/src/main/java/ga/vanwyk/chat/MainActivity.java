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
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.content.ClipData;
import android.view.View;
import android.widget.CompoundButton;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {
	
	public final int REQ_CD_PICK = 101;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private boolean log = false;
	private String string_email = "";
	private String string_pass = "";
	private String path = "";
	private HashMap<String, Object> m = new HashMap<>();
	
	private LinearLayout linear1;
	private LinearLayout lin_mailpasssubmit;
	private LinearLayout lin_adduser_foto;
	private EditText et_email;
	private EditText et_pass;
	private Button btn_submit;
	private LinearLayout linear4;
	private CheckBox setlogreg;
	private EditText et_username;
	private ImageView img_pick;
	private ProgressBar progressbar1;
	private TextView note_fotoprofil;
	
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private StorageReference fotoprol = _firebase_storage.getReference("user/foprof");
	private OnCompleteListener<Uri> _fotoprol_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fotoprol_download_success_listener;
	private OnSuccessListener _fotoprol_delete_success_listener;
	private OnProgressListener _fotoprol_upload_progress_listener;
	private OnProgressListener _fotoprol_download_progress_listener;
	private OnFailureListener _fotoprol_failure_listener;
	private DatabaseReference DataBase = _firebase.getReference("user/db");
	private ChildEventListener _DataBase_child_listener;
	private Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		lin_mailpasssubmit = (LinearLayout) findViewById(R.id.lin_mailpasssubmit);
		lin_adduser_foto = (LinearLayout) findViewById(R.id.lin_adduser_foto);
		et_email = (EditText) findViewById(R.id.et_email);
		et_pass = (EditText) findViewById(R.id.et_pass);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		setlogreg = (CheckBox) findViewById(R.id.setlogreg);
		et_username = (EditText) findViewById(R.id.et_username);
		img_pick = (ImageView) findViewById(R.id.img_pick);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		note_fotoprofil = (TextView) findViewById(R.id.note_fotoprofil);
		auth = FirebaseAuth.getInstance();
		pick.setType("image/*");
		pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		btn_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (et_email.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Email Harus Terisi !");
				}
				else {
					if (et_pass.getText().toString().equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "Password Harus Terisi !");
					}
					else {
						if (log) {
							auth.signInWithEmailAndPassword(et_email.getText().toString(), et_pass.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_sign_in_listener);
						}
						else {
							string_email = et_email.getText().toString();
							string_pass = et_pass.getText().toString();
							auth.createUserWithEmailAndPassword(et_email.getText().toString(), et_pass.getText().toString()).addOnCompleteListener(MainActivity.this, _auth_create_user_listener);
						}
					}
				}
			}
		});
		
		setlogreg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2)  {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					log = true;
				}
				else {
					log = false;
				}
			}
		});
		
		img_pick.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (et_username.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Username Harus Terisi Dahulu !");
				}
				else {
					startActivityForResult(pick, REQ_CD_PICK);
				}
			}
		});
		
		_fotoprol_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				lin_mailpasssubmit.setVisibility(View.GONE);
				lin_adduser_foto.setVisibility(View.VISIBLE);
				progressbar1.setVisibility(View.VISIBLE);
				note_fotoprofil.setText("Proses Upload : ".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
		};
		
		_fotoprol_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_fotoprol_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				m = new HashMap<>();
				m.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				m.put("username", et_username.getText().toString());
				m.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
				m.put("foto", _downloadUrl);
				DataBase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
				auth.signInWithEmailAndPassword(string_email, string_pass).addOnCompleteListener(MainActivity.this, _auth_sign_in_listener);
			}
		};
		
		_fotoprol_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_fotoprol_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_fotoprol_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		_DataBase_child_listener = new ChildEventListener() {
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
		DataBase.addChildEventListener(_DataBase_child_listener);
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					lin_mailpasssubmit.setVisibility(View.GONE);
					lin_adduser_foto.setVisibility(View.VISIBLE);
					progressbar1.setVisibility(View.GONE);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					i.setClass(getApplicationContext(), HomeActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
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
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			i.setClass(getApplicationContext(), CatagoriesActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		}
		else {
			lin_mailpasssubmit.setVisibility(View.VISIBLE);
			lin_adduser_foto.setVisibility(View.GONE);
			progressbar1.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PICK:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				path = _filePath.get((int)(0));
				img_pick.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(path, 1024, 1024));
				fotoprol.child(Uri.parse(path).getLastPathSegment()).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_fotoprol_failure_listener).addOnProgressListener(_fotoprol_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return fotoprol.child(Uri.parse(path).getLastPathSegment()).getDownloadUrl();
					}}).addOnCompleteListener(_fotoprol_upload_success_listener);
			}
			else {
				
			}
			break;
			default:
			break;
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
